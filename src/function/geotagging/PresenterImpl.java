/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import com.google.gson.Gson;
import java.util.ArrayList;
import model.StayPoint;
import model.place_api.Result;

/**
 *
 * @author trungtran.vn
 */
public class PresenterImpl implements Presenter {

    private final MyView view;
    private final InteractorImpl interactor;

    public PresenterImpl(MyView view) {
        this.view = view;
        interactor = new InteractorImpl();
    }

    @Override
    public void doStart() throws Exception {
        String inputPathFile = view.getInputFilePath();
        Gson gson = new Gson();
        
        // extract point from file
        view.appendOutputProcess("start extract file...");
        StayPoint sp = interactor.extractPointsFromFile(inputPathFile);
        view.appendOutputProcess("extract from file: " + sp.getArr().size() +" point(s)");
        
        // find nearby place
        view.appendOutputProcess("start request nearby place...");
        ArrayList<Result> results = interactor.findNearbyPlace(sp);
        view.appendOutputProcess("requested: " + results.size() + " place(s)");
        
        // writeout file
        view.appendOutputProcess("write to file...");
        interactor.writeOutFile(results, inputPathFile);
        view.appendOutputProcess("SUCCESS!");
    }

}
