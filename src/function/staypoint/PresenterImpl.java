/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.staypoint;

/**
 *
 * @author trungtran.vn
 */
public class PresenterImpl implements Presenter{
    private MyView view;
    private InteractorImpl interactor;
    
    public PresenterImpl(MyView view){
        this.view = view;
    }
    
}
