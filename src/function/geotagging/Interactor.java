/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.geotagging;

import java.util.ArrayList;
import model.GPSPoint;
import model.StayPoint;
import model.place_api.Result;

/**
 *
 * @author trungtran.vn
 */
public interface Interactor {

    StayPoint extractPointsFromFile(String path) throws Exception;

    ArrayList<Result> findNearbyPlace(StayPoint sp);

    void writeOutFile(ArrayList<Result> rs, String inputPathFile) throws Exception;

}
