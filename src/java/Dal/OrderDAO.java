/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Cart;
import Model.Item;
import Model.Size;
import Model.UserData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class OrderDAO extends DBContext {

    public void addOrder(UserData c, Cart cart, String phoneReceive, boolean status, String nameReceive, String addressReceive, int totalMoney) {
        LocalDate curDate = LocalDate.now();
        String date = curDate.toString();
        try {
            //add order
            String sql = "INSERT INTO [dbo].[Orders]\n"
                    + "           ([phoneReceive]\n"
                    + "           ,[orderDate]\n"
                    + "           ,[status]\n"
                    + "           ,[cid]\n"
                    + "           ,[NameReceiver]\n"
                    + "           ,[AddressReceiver]\n"
                    + "           ,[totalMoney])\n"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phoneReceive);
            st.setString(2, date);
            st.setBoolean(3, status);
            st.setInt(4, c.getId());
            st.setString(5, nameReceive);
            st.setString(6, addressReceive);
            st.setInt(7, totalMoney);
            st.executeUpdate();
            //lay id cua order vua add
            String sql1 = "select top 1 id from [orders] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang OrderDetail
            ProductDAO d = new ProductDAO();
            if (rs.next()) {
                int oid = rs.getInt("id");
                for (Item i : cart.getItems()) {
                    Size size;
                    size = d.getProductByidAndSize(i.getProduct().getId(), i.getSize());
                    String sql2 = "insert into [orderdetail] values(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(4, size.getId());
                    st2.setInt(1, i.getProduct().getId());
                    st2.setInt(2, oid);
                    st2.setInt(3, i.getQuantity());
                    st2.executeUpdate();

                }
            }
            //cap nhat lai so luong san pham trong bảng size
            String sql3 = "update size set quantity=quantity-? where pid=? and name =? ";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.setString(3, i.getSize());
                st3.executeUpdate();
                System.out.println(i + " " + i.getSize());
            }
            ////cap nhat lai so luong san pham trong bảng product
            String sql4 = "update product set quantity = quantity-? where pid = ?";
            String sql5 = "update product set quantitySold = quantitySold-? where pid = ?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            PreparedStatement st5 = connection.prepareStatement(sql5);
            for (Item i : cart.getItems()) {
                int count = cart.getNumberItemByPid(i.getProduct().getId());
                st4.setInt(1, count);
                st4.setInt(2, i.getProduct().getId());
                st4.executeUpdate();
                st5.setInt(1, count);
                st5.setInt(2, i.getProduct().getId());
                st5.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
    }

    public static void main(String[] args) {
        OrderDAO d = new OrderDAO();
        ProductDAO p = new ProductDAO();
        UserData u = new UserData(1, "Hoàng Thu Phương", "htphoangphuong12@gmail.com", "0818165923", "187 Doãn Khuê, Thái Bình, tỉnh Thái Bình", "2003-01-07", "phuonght12", "Phuong01#", 1);
        Item t = new Item(p.getProductByid(8), 1, 10000, "S");
        Item t1 = new Item(p.getProductByid(8), 1, 10000, "M");
        Item t2 = new Item(p.getProductByid(8), 1, 10000, "L");
        Cart cart = new Cart();
        cart.addItem(t);
        cart.addItem(t1);
        cart.addItem(t2);
        d.addOrder(u, cart, "0818165923", true, "Hoang Thu Phuong", "Thai Binh", 100000);
        System.out.println(p.getProductByidAndSize(8, "S"));

    }
}
