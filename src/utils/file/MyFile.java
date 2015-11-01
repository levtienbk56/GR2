package utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lev Tien
 */
public class MyFile {

    public static void main(String[] args) {
        try {
            System.out.println(readFile("366.txt").readLine());
        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * read buffer content in a file
     *
     * @param pathFile path of file
     * @return buffer of content. you can extract: String line; while ((line =
     * reader.readLine()) != null){}
     */
    public static BufferedReader readFile(String pathFile) {
        System.out.println("begin read file...");
        InputStream in = null;
        BufferedReader reader = null;
        try {
            Path file = Paths.get(pathFile);
            in = Files.newInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            reader = new BufferedReader(inputStreamReader);
        } catch (IOException ex) {
            Logger.getLogger(MyFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reader;
    }

    /**
     * write string of content to the file
     *
     * @param pathFile path of file
     * @param content string of content
     */
    public static void writeToFile(String pathFile, String content) {
        try {
            File file = new File(pathFile);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(content);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static String getTypeOfFile(String path) {
        // check file type
        List<String> types = Arrays.asList(path.split("\\."));
        System.out.println(path);
        String typeFile = types.get(types.size() - 1);
        return typeFile;
    }
}
