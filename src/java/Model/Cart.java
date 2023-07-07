/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Dal.ProductDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getQuantityById(int id,String size) {
        return getItemById(id, size).getQuantity();
    }

    private Item getItemById(int id, String size) {
        for (Item i : items) {
            if (i.getProduct().getId() == id && i.getSize().equalsIgnoreCase(size)) {
                return i;
            }
        }
        return null;
    }

    public void addItem(Item t) {
        int id = t.getProduct().getId();
        String size = t.getSize();
        if (getItemById(id,size) != null) {
            Item m = getItemById(id,size);
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id, String size) {
        if (getItemById(id,size) != null) {
            items.remove(getItemById(id,size));
        }
    }

    public int getTotalMoney() {
        int t = 0;
        for (Item i : items) {
            t += (i.getQuantity() * i.getProduct().getPrice());
        }
        return t;
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        Cart c = new Cart();
        c.addItem(new Item(d.getProductByid(13), 2, 2, "S"));
        c.addItem(new Item(d.getProductByid(13), 2, 2, "S"));
        c.addItem(new Item(d.getProductByid(13), 2, 2, "L"));
        System.out.println(c.items.size());
        c.removeItem(13,"S");
        System.out.println(c.items.size());
    }

}
