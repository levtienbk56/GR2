/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.staypoint;

import java.io.IOException;
import java.util.ArrayList;
import model.GPSPoint;
import model.StayPoint;
import utils.GPSPointExtractor;
import utils.MyFile;

/**
 *
 * @author trungtran.vn
 */
public class Main {

    public static void main(String[] args) {
        try {
            StayPointCalculator spc = new StayPointCalculator(30, 300);
            //get points
            ArrayList<GPSPoint> pointArray = GPSPointExtractor.extractFromFile("./resource/366_02.txt");
            //calculate
            ArrayList<StayPoint> stayPointArray = spc.extractStayPoints(pointArray);

            System.out.println("--- result ---");
            String path = "output/366_02_30_300_p.txt";
            String s = "name,date time,longitude,latitude\n";
            MyFile.writeToFile(path, s);
            for (StayPoint sp : stayPointArray) {
                for (GPSPoint p : sp.getArr()) {
                    s = "366,"
                            + p.getTime() + ","
                            + p.getLng() + ","
                            + p.getLat() + "\n";
                    MyFile.writeToFile(path, s);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("read file error");
        }
    }

}
