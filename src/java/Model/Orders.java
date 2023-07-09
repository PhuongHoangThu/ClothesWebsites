/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Orders {

    private int id;
    private String phoneReceive, orderDate;
    private boolean status;
    private UserData user;
    private String nameReceive, addressReceive;
    private int totalMoney;

    public Orders() {
    }

    public Orders(int id, String phoneReceive, String orderDate, boolean status, UserData user, String nameReceive, String addressReceive, int totalMoney) {
        this.id = id;
        this.phoneReceive = phoneReceive;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
        this.nameReceive = nameReceive;
        this.addressReceive = addressReceive;
        this.totalMoney = totalMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneReceive() {
        return phoneReceive;
    }

    public void setPhoneReceive(String phoneReceive) {
        this.phoneReceive = phoneReceive;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public String getNameReceive() {
        return nameReceive;
    }

    public void setNameReceive(String nameReceive) {
        this.nameReceive = nameReceive;
    }

    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    

}
