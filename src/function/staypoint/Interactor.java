/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.staypoint;

import java.util.ArrayList;
import model.GPSPoint;
import model.StayPoint;

/**
 *
 * @author trungtran.vn
 */
public interface Interactor {
    ArrayList<GPSPoint> extractPointsFromFile(String path) throws Exception;

    ArrayList<StayPoint> computeStayPoints(ArrayList<GPSPoint> points, int disThresh, int timeThresh);

    void writeOutFile(ArrayList<StayPoint> spArray, String path, int disThres, int timeThresh) throws Exception;
}
