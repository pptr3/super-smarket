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

/**
 * Implementation of Controller interface.
 *
 */

public class ControllerImpl implements Controller {

    private final Model model;
    private Agent agent;
    private final Subject subject;
    //private static Optional<ControllerImpl> singleton = Optional.empty();

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
    public void initialize(final String filepath) throws IOException {
        this.model
                .initialize(Optional.of(new ObjectInputStream(new BufferedInputStream(new FileInputStream(filepath)))));
    }

    @Override
    public void saveFile(final String filepath) throws FileNotFoundException, IOException {
        this.model.serializeModel(new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filepath))));
    }

    @Override
    public List<Lot> getList() {
        return this.model.getList(null);
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
        return null;
    }

    @Override
    public void setOnSale(final int id, final int discountAmount) {
        this.model.setOnSale(id, discountAmount);
    }

    @Override
    public void registerView(final MyFakeView view) {
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
        private final Random rand = new Random();
        private final Integer sleepTime = 500;
        /*
         * to delete, i used it to test
         */
        private final Integer maxInteger = 20;

/*
 * package visible
 */
        Agent() {
            this.stoppable = false;
        }

        public void run() {
            while (!this.stoppable) {
                try {
                    // if(!getDiscountable(null).isEmpty()) {
                    // ControllerImpl.this.subject.updateViews();
                    // }
                    if (rand.nextInt(maxInteger) == 1) {
                        ControllerImpl.this.subject.updateView();
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
