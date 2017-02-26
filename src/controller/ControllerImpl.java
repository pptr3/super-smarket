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
import model.Warehouse;


public class ControllerImpl implements Controller {

    private Model model;
    private MyFakeView view;
    private Subject subject;
    private Agent ag = new Agent();
    private Thread th;
    
    public ControllerImpl() {
        this.model = new Warehouse();
        this.view = new ViewImpl();
        this.subject = new SubjectImpl();
        this.th = new Thread(this.ag);
    }
    
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
    public void startScan() {
//        this.subject.register(view);
        th.start();
    }

    @Override
    public void stopScan() {
       this.ag.stopScanning();
       this.subject.unregister(view);
    }

    private class Agent implements Runnable {

        private volatile boolean stoppable;
        private Random rand = new Random();
        public Agent() {
            this.stoppable = false;
        }

        public void run() {
            while (!this.stoppable) {
//                if(!ControllerImpl.this.getDiscountable(null).isEmpty()) {
//                    ControllerImpl.this.subject.notifyObserver();
//                } 
                if((this.rand.nextInt(5)) == 1) {
                    ControllerImpl.this.subject.notifyObserver();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public  void stopScanning() {
            this.stoppable = true;
        }
    }

}
