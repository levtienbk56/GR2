/*
 * Copyright (C) 2015 Lev Tien
 * 1002 B1 building - HUST , Ta Quang Buu, HN
 */
package org.hedspi.tienlv.gr.utils;

import org.hedspi.tienlv.gr.utils.file.MyFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hedspi.tienlv.gr.model.GPSPoint;

/**
 *
 * @author Lev Tien
 */
public class GPSPointExtractor {

    /**
     * return list of GPSPoint, extract from file
     *
     * @param filePath path of File
     * @return arrayList arrayList of GPSPoint(.
     * @throws java.io.IOException
     *
     */
    public static ArrayList<GPSPoint> extractFromFile(String filePath) throws Exception {
        ArrayList<GPSPoint> arr = new ArrayList();

        String typeFile = MyFile.getTypeOfFile(filePath);
        switch (typeFile) {
            case "plt":
                arr = extractFromPltFile(filePath);
                break;
            case "txt":
                arr = extractFromTxtFile(filePath);
                break;
        }

        System.out.println("number line of file:" + arr.size());

        return arr;
    }

    private static ArrayList<GPSPoint> extractFromPltFile(String filePath) throws Exception {
        ArrayList<GPSPoint> arr = new ArrayList();
        BufferedReader reader = MyFile.readFile(filePath);
        if (reader == null) {
            return arr;
        }
        String line;
        int i = 0;

        while ((line = reader.readLine()) != null) {
            i++;
            if (i < 7) {
                continue;
            }
            List<String> items = Arrays.asList(line.split("\\s*,\\s*"));  // <any space>,<any space>
            GPSPoint co = new GPSPoint(new Double(items.get(0)), new Double(items.get(1)), items.get(5) + " " + items.get(6));
            arr.add(co);
        }
        return arr;
    }

    /**
     * file format <id>,<date time>,<longitude>,<latitude>
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static ArrayList<GPSPoint> extractFromTxtFile(String filePath) throws Exception {
        ArrayList<GPSPoint> arr = new ArrayList();
        BufferedReader reader = MyFile.readFile(filePath);
        if (reader == null) {
            return arr;
        }
        String line;
        int i = 0;

        // check first lind for header
        line = reader.readLine();
        if (line != null) {
            List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

            // remove header
            if (!items.get(0).equals("name") && !items.get(0).equals("id") ) {
                GPSPoint co = new GPSPoint(new Double(items.get(3)), new Double(items.get(2)), items.get(1));
                arr.add(co);
            }

            //continue with other line
            while ((line = reader.readLine()) != null) {
                i++;
                items = Arrays.asList(line.split("\\s*,\\s*"));
                GPSPoint co = new GPSPoint(new Double(items.get(3)), new Double(items.get(2)), items.get(1));
                arr.add(co);
            }
        }
        return arr;
    }

    /**
     * file format <id>,<date time>,<latitude>,<longitude>
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static ArrayList<GPSPoint> extractFromTxt2File(String filePath) throws Exception {
        ArrayList<GPSPoint> arr = new ArrayList();
        BufferedReader reader = MyFile.readFile(filePath);
        if (reader == null) {
            return arr;
        }
        String line;
        int i = 0;

        line = reader.readLine();
        if (line != null) {
            List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

            // remove header
            if (!items.get(0).equals("name")) {
                GPSPoint co = new GPSPoint(new Double(items.get(3)), new Double(items.get(2)), items.get(1));
                arr.add(co);
            }

            //continue with other line
            while ((line = reader.readLine()) != null) {
                i++;
                items = Arrays.asList(line.split("\\s*,\\s*"));
                GPSPoint co = new GPSPoint(new Double(items.get(2)), new Double(items.get(3)), items.get(1));
                arr.add(co);
            }
        }
        return arr;

    }

    public static void main(String[] args) {


        /*     
         * insert into database
         *
         ArrayList<GPSPoint> arr = extractFromFile("366.txt");
         // insert DB
         DBConnectHelper db = new DBConnectHelper();
         db.connect();
        
         for (GPSPoint p : arr) {
         TdrivePOJO pojo = new TdrivePOJO();
         pojo.userId = 366;
         pojo.coordinate.setLat(p.getLat());
         pojo.coordinate.setLng(p.getLng());
         pojo.time = p.getTime();

         db.insertTdrive(pojo);
         }
         */
    }
}
