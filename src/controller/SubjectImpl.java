package controller;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import view.View;
/**
 * Implementation of Subject interface.
 *
 */
public class SubjectImpl implements Subject {

    private final Collection<View> views = Collections.synchronizedCollection(new LinkedList<>());

    @Override
    public void attachView(final View view) {
        this.views.add(view); 
    }

    @Override
    public void updateView() {
        views.forEach(v -> v.update());
    }
    @Override
    public void showMessageErrorView(final String message) {
        views.forEach(v -> v.errorMessage(message));
    }

}
