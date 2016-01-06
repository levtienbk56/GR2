/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.algorithm.prefixspan_object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author trungtran.vn
 */
public class MyComparator<T> implements Comparator<T> {

    @Override
    public int compare(T t, T t1) {
        return t.hashCode() - t1.hashCode();
    }

    public static void main(String[] args) {
        MyComparator<String> m = new MyComparator<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("3");
        list.add("2");

        Collections.sort(list);

        System.out.println(list);
    }

}
