/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class YearMonthRevenue {

    private int year;
    private int month;

    private int quantity;
    private int totalmoney;

    public YearMonthRevenue(int year, int month, int quantity, int totalmoney) {
        this.year = year;
        this.month = month;

        this.quantity = quantity;
        this.totalmoney = totalmoney;
    }

    public YearMonthRevenue() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(int totalmoney) {
        this.totalmoney = totalmoney;
    }

    @Override
    public String toString() {
        return "YearMonthRevenue{" + "year=" + year + ", month=" + month + ", quantity=" + quantity + ", totalmoney=" + totalmoney + '}';
    }
    

}
