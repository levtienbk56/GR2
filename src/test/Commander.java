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
public class Commander<T> {
    T item;
    public Commander(){
        
    }
    
    @Override
    public String toString(){
        return item.toString();
    }
    
    public void setItem(T item){
        this.item = item;
    }
    
    public static void main(String[] args) {
        Commander<Item> commander = new Commander<>();
        commander.setItem(new Item(1, "itemxxxx"));
        System.out.println(commander.toString());
    }
    
}
