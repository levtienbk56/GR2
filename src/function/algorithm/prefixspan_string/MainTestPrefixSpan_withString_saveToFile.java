package function.algorithm.prefixspan_string;


import function.algorithm.prefixspan_string.sequence_database_list_string.SequenceDatabase;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;


/**
 *  Example of how to use the PrefixSpan algorithm in source code.
 * @author Philippe Fournier-Viger
 */
public class MainTestPrefixSpan_withString_saveToFile {

	public static void main(String [] arg) throws IOException{    
		// Load a sequence database
		SequenceDatabase sequenceDatabase = new SequenceDatabase(); 
		sequenceDatabase.loadFile(fileToPath("contextPrefixSpanStrings.txt"));
		// print the database to console
//		sequenceDatabase.print();
		
		// Create an instance of the algorithm with minsup = 50 %
		AlgoPrefixSpan_with_Strings algo = new AlgoPrefixSpan_with_Strings(); 
		
		int minsup = 2; // we use a minimum support of 2 sequences.
		
		// execute the algorithm
		algo.runAlgorithm(sequenceDatabase, "D:\\NetbeansProjects\\GR2\\ouputPrefixSpanStrings.txt", minsup);    
		algo.printStatistics(sequenceDatabase.size());
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestPrefixSpan_withString_saveToFile.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}