package controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
/**
 * Implementation of Subject interface.
 *
 */
public class SubjectImpl implements Subject {

    private final Collection<MyFakeView> views;
/**
 * 
 */
    public SubjectImpl() {
        this.views = Collections.synchronizedCollection(new LinkedList<>());
    }

    @Override
    public void attachView(final MyFakeView view) {
        this.views.add(view); 
    }

    @Override
    public void unattachView() {
        if (!this.views.isEmpty()) { // this method has been implementedd
                                     // considering that the maximum number of
                                     // observers is 1
            this.views.clear();
        }
    }

    @Override
    public void updateView() {
        views.forEach(v -> v.update());
    }

}
