/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Dal.ProductDAO;
import java.util.List;

/**
 *
 * @author HP
 */
/*
[id]
      ,[ProductName]
      ,[Price]
      ,[image]
      ,[Description]
      ,[CreateDate]
      ,[UpdateDate]
      ,[cid]
      ,[quantity]
      ,[Color]
      ,[Material]
*/
public class Product {
    private int id;
    private String name;
    private int price;
    private String image,description,createDate,updateDate;
    private Category category;
    private int quantity;
    private String color;
    private String material;
    private int priceOriginal;
    private int quantitySold;

    public Product(int id, String name, int price, String image, String description, String createDate, String updateDate, Category category, int quantity, String color, String material, int priceOriginal, int quantitySold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.category = category;
        this.quantity = quantity;
        this.color = color;
        this.material = material;
        this.priceOriginal = priceOriginal;
        this.quantitySold = quantitySold;
    }
    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(int priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }
    
    public Sale getProductSale(){
        ProductDAO p = new ProductDAO();
        return p.getSaleProductByPid(id);
    }
    

    

    
}
