/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hedspi.tienlv.gr.function.geotagging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Lev Tien
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<String> a1 = new ArrayList<>();
        ArrayList<String> a2 = new ArrayList<>();
        a1.add("aaaa");
        Set<String> s = new HashSet<>(a1);
        s.retainAll(a2);
        System.out.println(s.size());
    }
}
