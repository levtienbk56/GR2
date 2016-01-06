package function.algorithm.prefixspan_object;

import function.algorithm.prefixspan_object.sequence_database_list_object.SequenceDatabase;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * Example of how to use the PrefixSpan algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_withObject_saveToFile {

    public static void main(String[] arg) throws IOException {
        String itemDataType = "STRING";

        if (itemDataType.equals("STRING")) {
            // Load a sequence database
            SequenceDatabase<String> sequenceDatabase = new SequenceDatabase<>();
            sequenceDatabase.loadFileWithItemString(fileToPath("contextPrefixSpanStrings.txt"));
		// print the database to console
//		sequenceDatabase.print();

            // Create an instance of the algorithm with minsup = 50 %
            AlgoPrefixSpan_with_Object<String> algo = new AlgoPrefixSpan_with_Object<>();

            int minsup = 2; // we use a minimum support of 2 sequences.

            // execute the algorithm
            algo.runAlgorithm(sequenceDatabase, "D:\\NetbeansProjects\\GR2\\ouputPrefixSpanObject.txt", minsup);
            algo.printStatistics(sequenceDatabase.size());

        }

    }

    public static String fileToPath(String filename) throws UnsupportedEncodingException {
        URL url = MainTestPrefixSpan_withObject_saveToFile.class.getResource(filename);
        return java.net.URLDecoder.decode(url.getPath(), "UTF-8");
    }
}
