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
public class Main2 {

    public static void main(String[] args) {
        try {
            StayPointCalculator spc = new StayPointCalculator(30, 300);
            //get points
            ArrayList<GPSPoint> pointArray = GPSPointExtractor.extractFromFile("./resource/366_02.txt");
            //calculate
            ArrayList<StayPoint> stayPointArray = spc.extractStayPoints(pointArray);

            System.out.println("--- result ---");
            // moi stay point in ra 1 file chua tat ca cac point trong sp do
            for (int i = 0; i < stayPointArray.size(); i++) {
                String path = "output/listpoint/366_02_30_300_p" + i + ".txt";
                String s = "name,date time,longitude,latitude\n";
                MyFile.writeToFile(path, s);
                StayPoint sp = stayPointArray.get(i);
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
