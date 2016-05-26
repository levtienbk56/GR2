/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.hedspi.tienlv.gr.model.GPSPoint;
import org.hedspi.tienlv.gr.utils.file.MyFile;

/**
 * reformat lại gps log App Android
 * @author Lev Tien
 */
public class RefineFileData {

    private String in;
    private String out;

    public RefineFileData(String in, String out) {
        this.in = in;
        this.out = out;
    }

    public void refine() throws Exception {
        ArrayList<GPSPoint> arr = GPSPointExtractor.extractFromFile(in);
        ArrayList<GPSPoint> arrResult = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        GPSPoint pre = null;
        for (GPSPoint cur : arr) {
            // loai bo 0.0,0.0
            if (cur.getLat() == 0.0 || cur.getLng() == 0.0) {
                continue;
            }

            String str = "0002," + cur.getTime() + "," + cur.getLng() + "," + cur.getLat() + "\n";
            // cat tia theo thoi gian
            if (pre == null) {
                pre = cur;
                arrResult.add(cur);

                // write to file out
                MyFile.writeToFile(out, str);
                continue;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = dateFormat.parse(cur.getTime());
            Date preDate = dateFormat.parse(pre.getTime());

            if (curDate.getTime() - preDate.getTime() > 1000 * 50) {
                arrResult.add(cur);
                pre = cur;

                // write to file out
                MyFile.writeToFile(out, str);
            }
        }

        System.out.println("total size: " + arrResult.size());
    }
    
    public static void main(String[] args) throws Exception {
        RefineFileData refineFileDate = new RefineFileData("D:\\GR\\GR3\\17-3\\thanglog_gps.txt", "D:\\GR\\GR3\\17-3\\0002_log.txt");
        refineFileDate.refine();
    }

}
