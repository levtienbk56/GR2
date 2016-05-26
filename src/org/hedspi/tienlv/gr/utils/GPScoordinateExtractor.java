package org.hedspi.tienlv.gr.utils;

import org.hedspi.tienlv.gr.utils.file.MyFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hedspi.tienlv.gr.model.Coordinate;

/**
 *
 * @author Lev Tien
 */
public class GPScoordinateExtractor {

    public static void main(String[] args) {
        try {
            GPScoordinateExtractor a = new GPScoordinateExtractor();
            
            ArrayList<Coordinate> arr = a.extractFromFile("366.txt");
            System.out.println(arr.get(0).getLat() + "," + arr.get(0).getLng());
        } catch (Exception ex) {
            Logger.getLogger(GPScoordinateExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param pathFile path of file
     * @return arrayList of Coordinate(latitude, longitude)
     */
    public ArrayList<Coordinate> extractFromFile(String pathFile) throws Exception{
        ArrayList<Coordinate> arr = new ArrayList();

        BufferedReader reader = MyFile.readFile(pathFile);
        if (reader == null) {
            return arr;
        }
        String line;
        int i = 0;

        try {
            while ((line = reader.readLine()) != null) {
                i++;
                List<String> items = Arrays.asList(line.split("\\s*,\\s*"));

                //get coordinate
                Coordinate co = new Coordinate(new Double(items.get(2)), new Double(items.get(3)));

                //add to list
                arr.add(co);
            }
        } catch (IOException ex) {
        }

        System.out.println("size of list:" + arr.size());

        return arr;
    }

}
