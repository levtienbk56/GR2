/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.main;

import org.hedspi.tienlv.gr.function.geotagging.NearbyPlaceAPI;
import org.hedspi.tienlv.gr.function.staypoint.StayPointCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.model.StayPoint;
import org.hedspi.tienlv.gr.model.place_api.Result;
import org.hedspi.tienlv.gr.utils.GPSPointExtractor;
import org.hedspi.tienlv.gr.utils.file.MyFile;

/**
 *
 * @author Lev Tien
 */
public class Main {

    public static final String PATH_IN = "C:\\Users\\trungtran.vn\\Desktop\\test\\0002_log.txt";
    public static final String PATH_OUT_INT = "C:\\Users\\trungtran.vn\\Desktop\\test\\0002_patterns_integer.txt";
    public static final String PATH_OUT_STRING = "C:\\Users\\trungtran.vn\\Desktop\\test\\0002_patterns_string.txt";

    public static void main(String[] args) throws Exception {
        ArrayList<GPSPoint> points = GPSPointExtractor.extractFromFile(PATH_IN);
        StayPointCalculator spc = new StayPointCalculator(30, 1200);

        List<StayPoint> staypoints = spc.extractStayPoints(points);
        System.out.println("staypoint:" + staypoints.size());
        String patternInt = "";
        String patternString = "";

        String curDate = "";
        for (StayPoint sp : staypoints) {
            // lấy ra ngày, mỗi ngày là 1 dòng
            String date = sp.getStartTime().split(" ")[0];
            if (curDate.equals("")) {
                curDate = date;
            }
            if (!date.equals(curDate)) {
                // each sequence split by "-2" Enter;
                patternInt += "-2\n";
                patternString += "-2\n";
                MyFile.writeToFile(PATH_OUT_INT, patternInt);
                MyFile.writeToFile(PATH_OUT_STRING, patternString);
                patternInt = "";
                patternString = "";
                curDate = date;
            }

            int radius = 20;
            List<Result> results = new ArrayList<>();
            while (radius < 100) {
                results = NearbyPlaceAPI.requestNearbyPlace(sp.getAvgCoordinate(), radius);
                if (results.size() > 0) {
                    break;
                } else {
                    radius += 20;
                }
            }

            // dùng để check những item đã có trong itemset
            List<Integer> intTypes = new ArrayList<>();
            List<String> stringTypes = new ArrayList<>();
            for (Result result : results) {
                List<String> types = result.getTypes();

                for (String type : types) {
                    System.out.print("\t" + type);
                    if (Result.listTypes.contains(type) && !type.equals("establishment") && !type.equals("atm")) {
                        int item = Result.listTypes.indexOf(type);

                        // chỉ add vào list nếu item đó chưa có
                        if (item >= 0 && !intTypes.contains(item)) {
                            intTypes.add(item);
                            stringTypes.add(type);
                        }
                    }
                }
                System.out.println("");
            }
            Collections.sort(intTypes);
            Collections.sort(stringTypes);
            for (Integer item : intTypes) {
                // each item split by Space " "
                patternInt += item + " ";
            }
            for (String item : stringTypes) {
                // each item split by Space " "
                patternString += item + " ";
            }
            if (intTypes.size() > 0) {
                // each itemset split by "-1"
                patternInt += "-1 ";
                patternString += "-1 ";
            }

        }
        MyFile.writeToFile(PATH_OUT_INT, patternInt + "-2");
        MyFile.writeToFile(PATH_OUT_STRING, patternString + "-2");

    }
}
