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
    private String NameReceive, AddressReceive;
    private int totalMoney;

    public Orders() {
    }

    public Orders(int id, String phoneReceive, String orderDate, boolean status, UserData user, String NameReceive, String AddressReceive, int totalMoney) {
        this.id = id;
        this.phoneReceive = phoneReceive;
        this.orderDate = orderDate;
        this.status = status;
        this.user = user;
        this.NameReceive = NameReceive;
        this.AddressReceive = AddressReceive;
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
        return NameReceive;
    }

    public void setNameReceive(String NameReceive) {
        this.NameReceive = NameReceive;
    }

    public String getAddressReceive() {
        return AddressReceive;
    }

    public void setAddressReceive(String AddressReceive) {
        this.AddressReceive = AddressReceive;
    }
    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

}
