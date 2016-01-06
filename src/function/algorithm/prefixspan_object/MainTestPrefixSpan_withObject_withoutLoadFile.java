/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.algorithm.prefixspan_object;

import function.algorithm.prefixspan_object.sequence_database_list_object.Sequence;
import function.algorithm.prefixspan_object.sequence_database_list_object.SequenceDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trungtran.vn
 */
public class MainTestPrefixSpan_withObject_withoutLoadFile {

    public static void main(String[] args) throws IOException {
        // Load a sequence database
        SequenceDatabase<ItemTest> sequenceDatabase = initDatabase();

        // Create an instance of the algorithm with minsup = 50 %
        AlgoPrefixSpan_with_Object<ItemTest> algo = new AlgoPrefixSpan_with_Object<>();

        int minsup = 2; // we use a minimum support of 2 sequences.

        // execute the algorithm
        algo.runAlgorithm(sequenceDatabase, "D:\\NetbeansProjects\\GR2\\ouputPrefixSpanObject.txt", minsup);
        algo.printStatistics(sequenceDatabase.size());

    }

    static SequenceDatabase<ItemTest> initDatabase() {
        SequenceDatabase<ItemTest> sequenceDatabase = new SequenceDatabase<>();
        Sequence<ItemTest> sequence1 = new Sequence<>(1);
        Sequence<ItemTest> sequence2 = new Sequence<>(2);
        Sequence<ItemTest> sequence3 = new Sequence<>(3);
        Sequence<ItemTest> sequence4 = new Sequence<>(4);

        List<ItemTest> i1 = new ArrayList<>();
        List<ItemTest> i2 = new ArrayList<>();
        List<ItemTest> i3 = new ArrayList<>();
        List<ItemTest> i4 = new ArrayList<>();
        List<ItemTest> i5 = new ArrayList<>();
        List<ItemTest> i6 = new ArrayList<>();
        List<ItemTest> i7 = new ArrayList<>();
        List<ItemTest> i8 = new ArrayList<>();
        List<ItemTest> i9 = new ArrayList<>();
        List<ItemTest> i10 = new ArrayList<>();
        List<ItemTest> i11 = new ArrayList<>();
        List<ItemTest> i12 = new ArrayList<>();
        List<ItemTest> i13 = new ArrayList<>();
        List<ItemTest> i14 = new ArrayList<>();
        List<ItemTest> i15 = new ArrayList<>();
        List<ItemTest> i16 = new ArrayList<>();
        List<ItemTest> i17 = new ArrayList<>();
        List<ItemTest> i18 = new ArrayList<>();
        List<ItemTest> i19 = new ArrayList<>();
        List<ItemTest> i20 = new ArrayList<>();

        i1.add(new ItemTest("a", 1));
        i2.add(new ItemTest("a", 1));
        i2.add(new ItemTest("b", 1));
        i2.add(new ItemTest("c", 1));
        i3.add(new ItemTest("a", 2));
        i3.add(new ItemTest("c", 2));
        i4.add(new ItemTest("d", 3));
        i5.add(new ItemTest("c", 4));
        i5.add(new ItemTest("f", 4));
        sequence1.addItemset(i1);
        sequence1.addItemset(i2);
        sequence1.addItemset(i3);
        sequence1.addItemset(i4);
        sequence1.addItemset(i5);

        i6.add(new ItemTest("a", 1));
        i6.add(new ItemTest("d", 1));
        i7.add(new ItemTest("c", 2));
        i8.add(new ItemTest("b", 3));
        i8.add(new ItemTest("c", 3));
        i9.add(new ItemTest("a", 3));
        i9.add(new ItemTest("e", 3));
        sequence2.addItemset(i6);
        sequence2.addItemset(i7);
        sequence2.addItemset(i8);
        sequence2.addItemset(i9);

        i10.add(new ItemTest("e", 3));
        i10.add(new ItemTest("f", 3));
        i11.add(new ItemTest("a", 3));
        i11.add(new ItemTest("b", 3));
        i12.add(new ItemTest("d", 4));
        i12.add(new ItemTest("f", 4));
        i13.add(new ItemTest("c", 4));
        i14.add(new ItemTest("b", 4));
        sequence3.addItemset(i10);
        sequence3.addItemset(i11);
        sequence3.addItemset(i12);
        sequence3.addItemset(i13);
        sequence3.addItemset(i14);

        i15.add(new ItemTest("e", 1));
        i16.add(new ItemTest("g", 1));
        i17.add(new ItemTest("a", 1));
        i17.add(new ItemTest("f", 1));
        i18.add(new ItemTest("c", 2));
        i19.add(new ItemTest("b", 2));
        i20.add(new ItemTest("c", 3));
        sequence4.addItemset(i15);
        sequence4.addItemset(i16);
        sequence4.addItemset(i17);
        sequence4.addItemset(i18);
        sequence4.addItemset(i19);
        sequence4.addItemset(i20);

        sequenceDatabase.addSequence(sequence1);
        sequenceDatabase.addSequence(sequence2);
        sequenceDatabase.addSequence(sequence3);
        sequenceDatabase.addSequence(sequence4);
        System.out.println(sequenceDatabase.getSequences().get(1).get(0));
        return sequenceDatabase;
    }
}
