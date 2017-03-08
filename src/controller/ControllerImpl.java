package controller;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
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
    // private static Optional<ControllerImpl> singleton = Optional.empty();

    /**
     * 
     * @param warehouse
     *            warehouse
     */
    public ControllerImpl(final Model warehouse) {
        this.model = warehouse;
        this.subject = new SubjectImpl();
    }

    /*
     * need to close the file (non-Javadoc)
     * 
     * @see controller.Controller#initialize(java.lang.String)
     */
    @Override
    public void initialize(final String filepath) throws IOException {
        final ObjectInputStream ostream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filepath)));
        this.model.initialize(Optional.of(ostream));
        ostream.close();
    }

    @Override
    public void saveFile(final String filepath) throws FileNotFoundException, IOException {
        final ObjectOutputStream ostream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filepath)));
        this.model.serializeModel(ostream);
        ostream.close();
    }

    /*
     * (non-Javadoc)
     * 
     * @see controller.Controller#getList()
     */
    @Override
    public List<Lot> getList(final ModifyList mfl) {
        return this.model.getList(mfl);
    }

    @Override
    public void addLotto(final Lot lotto) {
        this.model.addLotto(lotto);
    }

    @Override
    public void removeFromLotto(final int id, final int n) {
        this.model.removeFromLot(id, n);
    }

    @Override
    public Map<Lot, Integer> getDiscountable(final DiscountStrategy ds) {
        return this.model.getDiscountable(ds);
    }

    @Override
    public void setOnSale(final int id, final int discountAmount) {
        this.model.setOnSale(id, discountAmount);
    }

    @Override
    public void removeFromSale(final int id) {
        this.model.removeFromSale(id);
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
    public synchronized void startScan() {
        if (agent == null) {
            this.agent = new Agent();
            this.agent.start();
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public synchronized void stopScan() {
        if (agent == null) {
            throw new IllegalStateException();
        }
        this.agent.stopScanning();
        try {
            this.agent.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
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
                        // to delete
                        Thread.sleep(10000);
                    }
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException();
                }
            }
        }

        public void stopScanning() {
            this.stoppable = true;
        }
    }
}
