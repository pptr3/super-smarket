package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.JButton;
import org.junit.rules.Stopwatch;
import model.Lot;
import model.Model;
import model.discountstrategies.DiscountStrategy;
import model.discountstrategies.DiscountStrategyFactoryImpl;
import model.modifylists.ModifyList;
import view.View;
/**
 * Implementation of Controller interface.
 *
 */

public class ControllerImpl implements Controller {

    private final Model model;
    private Agent agent;
    private final Subject subject;

    /**
     * 
     * @param warehouse
     *            warehouse
     */
    public ControllerImpl(final Model warehouse) {
        this.model = warehouse;
        this.subject = new SubjectImpl();
    }

    @Override
    public void initialize(final String filepath) throws FileNotFoundException, IOException {
        final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filepath)));
        this.model.initialize(Optional.of(ostream));
        ostream.close();
    }

    @Override
    public void saveFile(final String filepath) throws FileNotFoundException, IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(filepath)));
        try {
            this.model.serializeModel(ostream);
        } catch (Exception e) {
            this.subject.showMessageErrorView(e.getMessage());
        }
        ostream.close();
    }

    @Override
    public List<Lot> getList(final ModifyList mfl) {
        return this.model.getList(mfl);
    }

    @Override
    public void addLot(final Lot lotto) {
        this.model.addLotto(lotto);
    }

    @Override
    public void removeFromLot(final int id, final int n) {
        try {
            this.model.removeFromLot(id, n);
        } catch (Exception e) {
            this.subject.showMessageErrorView(e.getMessage());
        }
    }

    @Override
    public Map<Lot, Integer> getDiscountable(final DiscountStrategy ds) {
        return this.model.getDiscountable(ds);
    }

    @Override
    public void setOnSale(final int id, final int discountAmount) {
        try {
            this.model.setOnSale(id, discountAmount);
        } catch (Exception e) {
            this.subject.showMessageErrorView(e.getMessage());
        }
    }

    @Override
    public void removeFromSale(final int id) {
        try {
            this.model.removeFromSale(id);
        } catch (IllegalArgumentException e) {
            this.subject.showMessageErrorView(e.getMessage());
        } catch (Exception e) {
            this.subject.showMessageErrorView(e.getMessage());
        }
    }

    @Override
    public void dontSuggestAnymore(final Lot l) {
        this.model.dontSuggestAnymore(l);
    }

    @Override
    public void resetSuggestions() {
        this.model.resetSuggestions();
    }

    @Override
    public void registerView(final View view) {
        this.subject.attachView(view);
    }

    @Override
    public Subject getSubject() {
        return this.subject;
    }

    @Override
    public synchronized void startScan() {
        try {
            if (agent == null) {
                this.agent = new Agent();
                this.agent.start();
            } else {
                throw new IllegalStateException();
            }
        } catch (Exception e) {
            this.subject.showMessageErrorView(e.getMessage());
        }

    }

    @Override
    public synchronized void stopScan() {
        try {
            if (agent == null) {
                throw new IllegalStateException();
            }
            this.agent.stopScanning();
            this.agent.join();
        } catch (InterruptedException e) {
            this.subject.showMessageErrorView(e.getMessage());
        }
        agent = null;
    }

    private class Agent extends Thread {

        private volatile boolean stoppable;
        private final Integer sleepTime = 500;

        /*
         * package visible
         */
        Agent() {
            this.stoppable = false;
        }

        public void run() {
            while (!this.stoppable) {
                try {
                    if (!getDiscountable(new DiscountStrategyFactoryImpl().expiresWithinOneDay()).isEmpty()) {
                        ControllerImpl.this.subject.updateView();
                        stopScanning();
                    }
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    ControllerImpl.this.subject.showMessageErrorView(ex.getMessage());
                }
            }
        }

        public void stopScanning() {
            this.stoppable = true;
        }
    }
}
