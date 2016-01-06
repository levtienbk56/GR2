/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author trungtran.vn
 */
public class Item {

    int id;
    String name;

    public Item() {

    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + id + "," + name + "]";
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Item) o).name);
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = hashCode * 37 + this.name.hashCode();
        hashCode = hashCode * 37 + this.name.hashCode();
        return hashCode;
    }

}
