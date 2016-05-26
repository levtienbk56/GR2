/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.function.staypoint;

import java.util.ArrayList;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.model.StayPoint;
import org.hedspi.tienlv.gr.utils.counter.TimeCounter;

/**
 *
 * @author Lev Tien
 */
public class PresenterImpl implements Presenter {

    private final MyView view;
    private final InteractorImpl interactor;
    private TimeCounter timeCounter;

    public PresenterImpl(MyView view) {
        this.view = view;
        interactor = new InteractorImpl();
        timeCounter = new TimeCounter();
    }

    @Override
    public void doStart() throws Exception {
        String pathFile = view.getInputFilePath();
        int disThres = view.getDisThresh();
        int timeThresh = view.getTimeThresh();

        view.setOutputProcess("GPS point extracting...");
        timeCounter.getTimeGap();
        ArrayList<GPSPoint> pointArray = interactor.extractPointsFromFile(pathFile);
        view.appendOutputProcess(pointArray.size() + " point extracted in "
                + timeCounter.getTimeGap() + "ms");

        view.appendOutputProcess("StayPoint extracting...");
        timeCounter.getTimeGap();
        ArrayList<StayPoint> spArray 
                = interactor.computeStayPoints(pointArray, disThres, timeThresh);
        view.appendOutputProcess(spArray.size() + " stayPoint extracted in "
                + timeCounter.getTimeGap() + "ms");

        view.appendOutputProcess("file writing...");
        timeCounter.getTimeGap();
        interactor.writeOutFile(spArray, pathFile, disThres, timeThresh);
        view.appendOutputProcess("write file in " + timeCounter.getTimeGap() +"ms");
        view.appendOutputProcess("SUCCESS");

    }
}
