/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class YearRevenue {
    private int month;
    private int revenue2022;
    private int revenue2023;

    public YearRevenue() {
    }

    public YearRevenue(int month, int revenue2022, int revenue2023) {
        this.month = month;
        this.revenue2022 = revenue2022;
        this.revenue2023 = revenue2023;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getRevenue2022() {
        return revenue2022;
    }

    public void setRevenue2022(int revenue2022) {
        this.revenue2022 = revenue2022;
    }

    public int getRevenue2023() {
        return revenue2023;
    }

    public void setRevenue2023(int revenue2023) {
        this.revenue2023 = revenue2023;
    }

    @Override
    public String toString() {
        return "YearRevenue{" + "month=" + month + ", revenue2022=" + revenue2022 + ", revenue2023=" + revenue2023 + '}';
    }
    
}
