/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function.algorithm.prefixspan_object;

import java.util.Comparator;

/**
 *
 * @author trungtran.vn
 */
public class ItemTest implements Comparator<ItemTest> {

    private String name;
    private int timeSlot;

    public ItemTest() {

    }

    public ItemTest(String name, int time) {
        this.name = name;
        this.timeSlot = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public int compare(ItemTest t, ItemTest t1) {
        int c = t.timeSlot - t1.timeSlot;
        if (c == 0) {
            return t.name.compareTo(t1.name);
        }
        return c;
    }

    @Override
    public String toString() {
        return "[" + name + "," + timeSlot + ']';
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ItemTest)) {
            return false;
        }

        ItemTest that = (ItemTest) other;

        // Custom equality check here.
        return this.name.equals(that.name) && (this.timeSlot == that.timeSlot);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        hashCode = hashCode * 37 + this.name.hashCode();
        hashCode = hashCode * 37 + this.timeSlot;

        return hashCode;
    }
    
    public static void main(String[] args) {
        ItemTest i1 = new ItemTest("a", 1);
        ItemTest i2= new ItemTest("a", 1);
        ItemTest i3= new ItemTest("a", 2);
        ItemTest i4= new ItemTest("b", 1);
        
    }

}
