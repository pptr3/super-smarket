package controller;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {

    private List<MyFakeView> list;
    
    public SubjectImpl() {
        this.list = new ArrayList<>();
    }
    
    @Override
    public void register(MyFakeView newObserver) {
        this.list.add(newObserver);
    }

    @Override
    public void unregister(MyFakeView deleteObserver) {
        this.list.remove(this.list.indexOf(deleteObserver));
    }

    @Override
    public void notifyObserver() {
        this.list.forEach(ob -> ob.update());
    }

}
