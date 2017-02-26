package controller;

public interface Subject {

    /**
     * Attach a viewer Observer
     * @param view
     */
    public void attachView(MyFakeView view);
    
    /**
     * Unattach a viewer Observer
     */
    public void unattachView();
    
    /**
     * update the observers
     */
    public void updateViews();
}
