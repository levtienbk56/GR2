/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author trungtran.vn
 */
public class test {
    public static void main(String[] args) {
       try{
           
           File parent = new File("C:\\Users\\trungtran.vn\\Desktop\\abc\\xyz");
           parent.mkdir();
           writeToFile("C:\\Users\\trungtran.vn\\Desktop\\abc\\xyz\\123.dat", "sfafshbcj");
       }catch(Exception ex){
           ex.printStackTrace();
       }
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
