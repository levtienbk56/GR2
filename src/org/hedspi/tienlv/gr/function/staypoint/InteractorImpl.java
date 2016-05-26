/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.function.staypoint;

import java.util.ArrayList;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.model.StayPoint;
import org.hedspi.tienlv.gr.utils.GPSPointExtractor;
import org.hedspi.tienlv.gr.utils.file.MyFile;

public class InteractorImpl implements Interactor {

    /**
     * read file and extract point
     *
     * @param path path of file
     * @return
     * @throws java.io.IOException
     */
    @Override
    public ArrayList<GPSPoint> extractPointsFromFile(String path) throws Exception {
        ArrayList<GPSPoint> pointArray = GPSPointExtractor.extractFromFile(path);
        return pointArray;
    }

    @Override
    public ArrayList<StayPoint> computeStayPoints(ArrayList<GPSPoint> points, int disThresh, int timeThresh) {
        StayPointCalculator spc = new StayPointCalculator(disThresh, timeThresh);

        ArrayList<StayPoint> spArr = spc.extractStayPoints(points);
        return spArr;
    }

    /**
     * the result has construct: |folder[name of input] *|file[centroids] //
     * list centroid |folder[lists] *|file[sp1] //list point of sp1 **|file[sp2]
     *
     * @param spArray
     */
    @Override
    public void writeOutFile(ArrayList<StayPoint> spArray, String path, int disThres, int timeThresh) throws Exception {
        String folderName = MyFile.getFileName(path) + "_"
                + disThres + "_"
                + timeThresh;
        MyFile.createFolder("output");
        MyFile.createFolder("output/" + folderName);
        folderName = "output/" + folderName;
        String fileName = folderName + "/centroids.txt";
        String s = "name,date time,longitude,latitude\n";

        // write to centroid file
        MyFile.writeToFile(fileName, s);
        for (int i = 0; i < spArray.size(); i++) {
            StayPoint sp = spArray.get(i);
            s = i + ","
                    + sp.getStartTime() + ","
                    + sp.getAvgCoordinate().getLng() + ","
                    + sp.getAvgCoordinate().getLat() + "\n";
            MyFile.writeToFile(fileName, s);
        }

        // write to list 
        folderName += "/lists";
        MyFile.createFolder(folderName);
        for (int i = 0; i < spArray.size(); i++) {
            StayPoint sp = spArray.get(i);
            fileName = folderName + "/" + i + ".txt";

            s = "name,date time,longitude,latitude\n";
            MyFile.writeToFile(fileName, s);
            for (GPSPoint p : sp.getArr()) {
                s = i + ","
                        + p.getTime() + ","
                        + p.getLng() + ","
                        + p.getLat() + "\n";
                MyFile.writeToFile(fileName, s);
            }
        }
    }

    public void writeOutFile(ArrayList<StayPoint> spArray, String path) throws Exception {
        String folderName = MyFile.getFileName(path);
        MyFile.createFolder("output");
        MyFile.createFolder("output/" + folderName);
        folderName = "output/" + folderName;
        String fileName = folderName + "/centroids.txt";

        String s;
        for (int i = 0; i < spArray.size(); i++) {
            StayPoint sp = spArray.get(i);
            s = i + ","
                    + sp.getStartTime() + ","
                    + sp.getAvgCoordinate().getLng() + ","
                    + sp.getAvgCoordinate().getLat() + "\n";
            MyFile.writeToFile(fileName, s);
        }
    }
}
