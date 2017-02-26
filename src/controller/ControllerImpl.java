package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.swing.SwingUtilities;

import org.omg.Messaging.SyncScopeHelper;

import model.Lot;
import model.Model;
import model.Warehouse;


public class ControllerImpl implements Controller {

    private Model model;
    private MyFakeView view;
    private Agent agent;
    
    public ControllerImpl(Model model, MyFakeView view) {
        this.model = model;
        this.view = view;
    }
    
    public ControllerImpl() {}
    
    /**
     * If the file indicated by filepath exist, pass an Optional of ObjectInputStream, else pass an Optional.empty.
     * @throws IOException 
     * @param filepath
     */
    
    @Override
    public void initialize(String filepath) throws IOException {
        this.model.initialize(Optional.of( new ObjectInputStream( new BufferedInputStream( new FileInputStream(filepath)))));
    }
   
    /**
     * Saves the file to the given path
     * @param filepath
     *            ObjectOutputStream
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    
    @Override
    public void saveFile(String filepath) throws FileNotFoundException, IOException {
        this.model.serializeModel(new ObjectOutputStream( new BufferedOutputStream( new FileOutputStream(filepath))));
    }

    /**
     * @return the List of Lot
     */

    @Override
    public List<Lot> getList() {
        return this.model.getList(null);
    }

    /**
     * @param lotto adds lotto
     */
    
    @Override
    public void addLotto(Lot lotto) {
        this.model.addLotto(lotto);
    }

    /**
     * Removes n products from the lot with the specified id
     * @param id
     * @param n
     */
    
    @Override
    public void removeFromLotto(int id, int n) {
        this.model.removeFromLot(id, n);
    }

    @Override
    public Map<Lot, Integer> getDiscountable(String s) {
        return null;
    }

    /**
     * Set on sale the lot with specified id of discoutAmount amount
     * @param id
     * @param discountAmount
     */
    
    @Override
    public void setOnSale(int id, int discountAmount) {
        this.model.setOnSale(id, discountAmount);
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
       // private Random rand = new Random();
        
        public Agent() {
            this.stoppable = false;
           
            //ControllerImpl.this.subject.attach(view);
        }

        public void run() {
            while (!this.stoppable) {
                try {
                   System.out.println("run");
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new IllegalStateException("Counting has been interrupted", ex);
                }
            }
        }

        public void stopScanning() {
            this.stoppable = true;
        }
    }

    @Override
    public void registerView(MyFakeView view) {
        // TODO Auto-generated method stub
        
    }

}
