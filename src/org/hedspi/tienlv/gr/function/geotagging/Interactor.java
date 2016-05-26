/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.function.geotagging;

import java.util.ArrayList;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.model.StayPoint;
import org.hedspi.tienlv.gr.model.place_api.Result;

/**
 *
 * @author Lev Tien
 */
public interface Interactor {

    StayPoint extractPointsFromFile(String path) throws Exception;

    ArrayList<Result> findNearbyPlace(StayPoint sp);

    void writeOutFile(ArrayList<Result> rs, String inputPathFile) throws Exception;

}
