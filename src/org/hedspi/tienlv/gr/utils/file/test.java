/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.hedspi.tienlv.gr.model.Coordinate;
import org.hedspi.tienlv.gr.model.StayPoint;

/**
 *
 * @author Lev Tien
 */
public class test {
    public static void main(String[] args) {
        StayPoint s = new StayPoint();
        Coordinate c = new Coordinate();
        c.setLat(12.2222);
        s.setAvgCoordinate(c);
        c.setLat(44.444);
        System.out.println(s.getAvgCoordinate().getLat());
        
    }
    
    public static void writeToFile(String pathFile, String content) throws IOException {
        
        File file = new File(pathFile);

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        try (BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
        }
    }
}
