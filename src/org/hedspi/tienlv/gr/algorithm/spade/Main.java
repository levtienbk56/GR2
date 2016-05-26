/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.algorithm.spade;

import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.AlgoSPADE;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.candidatePatternsGeneration.CandidateGenerator_Qualitative;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator;
import ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.idLists.creators.IdListCreator_FatBitmap;
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
        parameters[1] = "false";// show sid?

        ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator abstractionCreator = ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.creators.AbstractionCreator_Qualitative
                .getInstance();
        IdListCreator idListCreator = IdListCreator_FatBitmap.getInstance();
        CandidateGenerator candidateGenerator = CandidateGenerator_Qualitative
                .getInstance();

        double minSupport = getParamAsDouble(parameters[0]);
        boolean outputSeqIdentifiers = false;
        if (parameters.length >= 2 && "".equals(parameters[1]) == false) {
            outputSeqIdentifiers = getParamAsBoolean(parameters[1]);
        }

        AlgoSPADE algo = new AlgoSPADE(minSupport, true, abstractionCreator);

        /*
         * if("".equals(parameters[1]) == false){
         * algo.setMaximumPatternLength(getParamAsInteger(parameters[1])); }
         */
        ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.database.SequenceDatabase sd = new ca.pfv.spmf.algorithms.sequentialpatterns.spade_spam_AGP.dataStructures.database.SequenceDatabase(
                abstractionCreator, idListCreator);
        sd.loadFile(inputFile, minSupport);

        algo.runAlgorithm(sd, candidateGenerator, true, false, outputFile, outputSeqIdentifiers);
        System.out.println(algo.printStatistics());
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
