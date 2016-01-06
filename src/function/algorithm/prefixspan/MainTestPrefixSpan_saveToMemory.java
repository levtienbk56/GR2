package function.algorithm.prefixspan;

import function.algorithm.prefixspan.sequence_database_list_integer.SequenceDatabase;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * Example of how to use the PrefixSpan algorithm in source code.
 *
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_saveToMemory {

    public static void main(String[] arg) throws IOException {
        // Load a sequence database
        SequenceDatabase sequenceDatabase = new SequenceDatabase();
        sequenceDatabase.loadFile(fileToPath("contextPrefixSpan.txt"));
        // print the database to console
        sequenceDatabase.print();

        // Create an instance of the algorithm 
        AlgoPrefixSpan algo = new AlgoPrefixSpan();
//		algo.setMaximumPatternLength(3);

        // if you set the following parameter to true, the sequence ids of the sequences where
        // each pattern appears will be shown in the result
        algo.setShowSequenceIdentifiers(true);

        // execute the algorithm with minsup = 50 %
        SequentialPatterns patterns = algo.runAlgorithm(sequenceDatabase, 0.4, null);
        algo.printStatistics(sequenceDatabase.size());
//		System.out.println(" == PATTERNS ==");
//		for(List<SequentialPattern> level : patterns.levels) {
//			for(SequentialPattern pattern : level){
//				System.out.println(pattern + " support : " + pattern.getAbsoluteSupport());
//			}
//		}
    }

    public static String fileToPath(String filename) throws UnsupportedEncodingException {
        URL url = MainTestPrefixSpan_saveToMemory.class.getResource(filename);
        return java.net.URLDecoder.decode(url.getPath(), "UTF-8");
    }
}
