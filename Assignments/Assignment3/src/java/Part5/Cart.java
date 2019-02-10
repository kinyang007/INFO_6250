/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class Cart {
    private List<Item> cart;
    
    public Cart() {
        cart = new ArrayList<>();
    }
    
    public List<Item> getCart() {
        return cart;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }
    
    public Item findItem(String name) {
        for (Item item : cart) {
            if (name.equals(item.getName())) {
                return item;
            }
        }
        return null;
    }
    
    public void addItem(Item item) {
        cart.add(item);
    }
    
    public void deleteItem(Item item) {
        cart.remove(item);
    }
    
    public void deleteItem(String name) {
        Item item = findItem(name);
        if (item != null)
            deleteItem(item);
    }
    
    public void modifyItemCount(String name, int count) {
        for (Item tmp : cart) {
            if (name.equals(tmp.getName())) {
                tmp.setCount(count);
            }
        }
    }
}
