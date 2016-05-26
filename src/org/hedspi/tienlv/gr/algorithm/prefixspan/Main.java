/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.algorithm.prefixspan;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoPrefixSpan;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import java.io.IOException;

/**
 *
 * @author Lev Tien
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String inputFile = "";
        String outputFile = "";
        final String parameters[] = new String[6];
        parameters[0] = "0.5";  // minsup
        parameters[1] = "5";    // max-length of pattern
        parameters[2] = "false";// show sid?

        SequenceDatabase sequenceDatabase = new SequenceDatabase();
        sequenceDatabase.loadFile(inputFile);
        // sequenceDatabase.print();
        int minsup = (int) Math
                .ceil((getParamAsDouble(parameters[0]) * sequenceDatabase
                        .size())); // we use a minimum support of 2
        // sequences.

        AlgoPrefixSpan algo = new AlgoPrefixSpan();
        if (parameters.length >= 2 && "".equals(parameters[1]) == false) {
            algo.setMaximumPatternLength(getParamAsInteger(parameters[1]));
        }

        boolean outputSeqIdentifiers = false;
        if (parameters.length >= 3 && "".equals(parameters[2]) == false) {
            outputSeqIdentifiers = getParamAsBoolean(parameters[2]);
        }

        algo.setShowSequenceIdentifiers(outputSeqIdentifiers);
        algo.runAlgorithm(sequenceDatabase, outputFile, minsup);
        algo.printStatistics(sequenceDatabase.size());
    }

    /**
     * Method to convert a parameter given as a string to a double. For example,
     * convert something like "50%" to 0.5.
     *
     * @param value a string
     * @return a double
     */
    private static double getParamAsDouble(String value) {
        if (value.contains("%")) {
            value = value.substring(0, value.length() - 1);
            return Double.parseDouble(value) / 100d;
        }
        return Double.parseDouble(value);
    }

    /**
     * Method to transform a string to an integer
     *
     * @param value a string
     * @return an integer
     */
    private static int getParamAsInteger(String value) {
        return Integer.parseInt(value);
    }

    /**
     * Method to transform a string to an boolean
     *
     * @param value a string
     * @return a boolean
     */
    private static boolean getParamAsBoolean(String value) {
        return Boolean.parseBoolean(value);
    }

    /**
     * Method to get a parameter as a string. Note: this method just return the
     * string taken as parameter.
     *
     * @param value a string
     * @return a string
     */
    private static String getParamAsString(String value) {
        return value;
    }
}
