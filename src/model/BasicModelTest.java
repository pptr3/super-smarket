package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.discountstrategies.DiscountStrategyFactory;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import model.modifylists.ModifyListFactory;
import model.modifylists.ModifyListFactoryImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicModelTest {

    Lot l = null;
    Lot p = null;
    Lot o = null;
    Lot c = null;
    Lot s = null;
    Lot b = null;

    /**
     * Tests the Builder, creating two lots and checking the ids and content.
     */
    @Test
    public void A_lotBuilderTest() {

        buildMilk();
        buildPasta();

        assertEquals(0, l.getId());
        assertEquals(1, p.getId());
        assertFalse(p.isOnSale());
        assertEquals(new MyCustomDateImpl(2017, 2, 8), p.getCheckInDate());
        assertEquals(Optional.empty(), p.getExpirationDate());
        assertEquals(60, p.getPricePerSingleItem());
        assertEquals(72, p.getInitialQuantity());
        assertEquals(72, p.getCurrentQuantity());
    }

    /**
     * Checks that removing all the items from a lot causes that lot to be removed from the warehouse.
     */
    @Test
    public void B_removeLotTest() {
        buildMilk();

        final Model m = new Warehouse();
        m.addLotto(l);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 20);
        assertEquals(1, m.getList(null).size());
        m.removeFromLot(l.getId(), 16);
        assertEquals(0, m.getList(null).size());
    }

    /**
     * Just tests that the lots correctly gets put on sale.
     */
    @Test
    public void C_setOnSaleTest() {
        buildMilk();

        final Model m = new Warehouse();
        m.addLotto(l);
        assertFalse(l.isOnSale());
        m.setOnSale(l.getId(), 10);
        assertTrue(m.getList(null).get(0).isOnSale());
    }

    /**
     * Tests the OverFiftyDiscount strategy.
     */
    @Test
    public void D_getDiscountableTest() {
        buildMilk();
        buildPasta();
        buildOnion();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(l);
        m.addLotto(o);

        DiscountStrategyFactory factory = new DiscountStrategyFactoryImpl();

        Map<Lot, Integer> map = m.getDiscountable(factory.overFiftyDiscount());

        assertEquals(2, map.size());

        m.removeFromLot(l.getId(), l.getInitialQuantity() - 1);
        map = m.getDiscountable(factory.overFiftyDiscount());

        assertEquals(1, map.size());
    }

    /**
     * Tests the AlpbabeticalSorting modifyList.
     */
    @Test
    public void E_modifyListTest() {
        buildMilk();
        buildPasta();
        buildOnion();

        final Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(l);
        m.addLotto(o);

        final List<Lot> x = m.getList(null);
        assertEquals(p.getId(), x.get(0).getId());
        assertEquals(l.getId(), x.get(1).getId());
        assertEquals(o.getId(), x.get(2).getId());

        ModifyListFactory factory = new ModifyListFactoryImpl();

        final List<Lot> x2 = m.getList(factory.alphabeticalSorting());
        assertEquals(l.getId(), x2.get(0).getId());
        assertEquals(o.getId(), x2.get(1).getId());
        assertEquals(p.getId(), x2.get(2).getId());

    }

    /**
     * Tests the OnlyExpiring modifyList.
     */
    @Test
    public void F_modifyListOnlyExpiringTest() {
        buildMilk();
        buildPasta();
        buildOnion();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(l);
        m.addLotto(o);

        ModifyListFactory factory = new ModifyListFactoryImpl();

        List<Lot> x = m.getList(null);
        assertEquals(3, x.size());

        List<Lot> x2 = m.getList(factory.onlyExpiring());
        assertEquals(2, x2.size());

    }

    /**
     * Tests that modifyList objects can also be retrieved from the enum.
     */
    @Test
    public void G_creatingStrategiesWithFactoryTest() {
        buildMilk();
        buildPasta();
        buildOnion();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(l);
        m.addLotto(o);

        ModifyListFactory factory = new ModifyListFactoryImpl();

        List<Lot> x = m.getList(null);
        assertEquals(3, x.size());

        List<Lot> x2 = m.getList(factory.onlyExpiring());
        assertEquals(2, x2.size());
    }

    /**
     * Tests both the expires within the week and the expires within tomorrow strategies.
     */
    @Test 
    public void H_expiresWithinNDaysTest() {
        buildPasta();
        buildCarrot();
        buildSalad();
        buildBread();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(c);
        m.addLotto(s);
        m.addLotto(b);

        DiscountStrategyFactory factory = new DiscountStrategyFactoryImpl();

        Map<Lot, Integer> map = m.getDiscountable(factory.expiresWithinAWeek());
        assertEquals(2, map.size());

        map = m.getDiscountable(factory.expiresWithinOneDay());
        assertEquals(1, map.size());
    }

    /**
     * Basically the same test as above, but we will try preventing some lots from being suggested again. 
     */
    @Test
    public void I_expiresWithinNDaysStopSuggestingTest() {
        buildPasta();
        buildCarrot();
        buildSalad();
        buildBread();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(c);
        m.addLotto(s);
        m.addLotto(b);

        DiscountStrategyFactory factory = new DiscountStrategyFactoryImpl();

        Map<Lot, Integer> map = m.getDiscountable(factory.expiresWithinAWeek());
        assertEquals(2, map.size());

        map = m.getDiscountable(factory.expiresWithinOneDay());
        assertEquals(1, map.size());

        m.dontSuggestAnymore(c);

        map = m.getDiscountable(factory.expiresWithinAWeek());
        assertEquals(1, map.size());

        map = m.getDiscountable(factory.expiresWithinOneDay());
        assertEquals(1, map.size());
    }

    /**
     * Tests how the notSuggestingLots are handled.
     */
    @Test
    public void E_getNotSuggestingListTest() {
        buildPasta();
        buildCarrot();
        buildSalad();
        buildBread();

        Model m = new Warehouse();
        m.addLotto(p);
        m.addLotto(c);
        m.addLotto(s);
        m.addLotto(b);

        m.dontSuggestAnymore(p);
        m.dontSuggestAnymore(b);

        assertTrue(m.getNotSuggestingList().stream().filter(lot -> lot.getId() == p.getId()).
                collect(Collectors.toList()).size() == 1);
        assertTrue(m.getNotSuggestingList().stream().filter(lot -> lot.getId() == b.getId()).
                collect(Collectors.toList()).size() == 1);

        m.resetSuggestions();

        assertEquals(m.getNotSuggestingList().size(), 0);

    }

    private void buildBread() {
        b = new LotBuilder()
                .name("Bread - brand 2")
                .expirationDate(new MyCustomDateImpl(LocalDate.now(), 10))
                .quantity(20)
                .pricePerSingleItem(100)
                .build();
    }

    private void buildSalad() {

        s = new LotBuilder()
                .name("Salad - brand 4")
                .expirationDate(new MyCustomDateImpl(LocalDate.now(), 1))
                .quantity(20)
                .pricePerSingleItem(100)
                .build();

    }

    private void buildCarrot() {

        c = new LotBuilder()
                .name("Carrots - brand 4")
                .expirationDate(new MyCustomDateImpl(LocalDate.now(), 4))
                .quantity(20)
                .pricePerSingleItem(100)
                .build();
    }

    private void buildOnion() {
        o = new LotBuilder()
                .name("Onions - brand 3")
                .checkInDate(new MyCustomDateImpl(2016,1,1))
                .expirationDate(new MyCustomDateImpl(2017,3,3))
                .quantity(10)
                .pricePerSingleItem(10)
                .build();

    }

    private void buildMilk() {
        l = new LotBuilder()
                .name("Milk - brand")
                .checkInDate(new MyCustomDateImpl(2017,2,3))
                .expirationDate(new MyCustomDateImpl(2017,2,13))
                .quantity(36)
                .pricePerSingleItem(50)
                .build();
    }

    private void buildPasta() {
        p = new LotBuilder()
                .name("Pasta - brand2")
                .checkInDate(new MyCustomDateImpl(2017,2,8))
                .quantity(72)
                .pricePerSingleItem(60)
                .build();
    }

}
