/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.algorithm.prefixspan_string;

import function.algorithm.prefixspan_string.sequence_database_list_string.SequenceDatabase;
import java.io.IOException;

/**
 *
 * @author trungtran.vn
 */
public class TestMinsup {

    public static void main(String[] arg) throws IOException {
        // Load a sequence database
        SequenceDatabase sequenceDatabase = new SequenceDatabase();
        sequenceDatabase.loadFile(("contextPrefixSpanStrings.txt"));
		// print the database to console
//		sequenceDatabase.print();

        // Create an instance of the algorithm with minsup = 50 %
        AlgoPrefixSpan_with_Strings algo = new AlgoPrefixSpan_with_Strings();

        int minsup = 2; // we use a minimum support of 2 sequences.

        // execute the algorithm
        algo.runAlgorithm(sequenceDatabase, "C:\\Users\\trungtran.vn\\Desktop\\output.txt", minsup);
        algo.printStatistics(sequenceDatabase.size());
    }
}
