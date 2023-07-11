/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.UserData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class UserDAO extends DBContext {

    public UserData checkAccount(String username, String password) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[UserData]\n"
                + "  where username = ? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new UserData(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("Dob"), rs.getString("username"), rs.getString("password"), rs.getInt("rid"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public UserData getAccount(int id) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[UserData]\n"
                + "  where id= ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new UserData(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("Dob"), rs.getString("username"), rs.getString("password"), rs.getInt("rid"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public UserData checkEmail(String email) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[UserData]\n"
                + "  where email = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new UserData(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("Dob"), rs.getString("username"), rs.getString("password"), rs.getInt("rid"));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int insertUser(String fullname, String email, String phone, String address, String dob, String username, String password, int rid) {

        String sql = "INSERT INTO [dbo].[UserData]\n"
                + "           ([fullname]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[Dob]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[rid])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, address);
            st.setString(5, dob);
            st.setString(6, username);
            st.setString(7, password);
            st.setInt(8, rid);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public int updateUser(String fullname, String email, String phone, String address, String dob, int id) {

        String sql = "UPDATE [dbo].[UserData]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[Dob] = ?\n"
                + " WHERE id = ?";
        System.out.println(sql);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullname);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4, address);
            st.setString(5, dob);
            st.setInt(6, id);
            return st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return 0;
    }
    public List<UserData> getAllUser() {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[UserData]\n"
                + "  where role = 1 ";
        List<UserData> list = new ArrayList<>();
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserData u = new UserData(rs.getInt("id"), rs.getString("fullname"), rs.getString("email"), rs.getString("phone"), rs.getString("address"), rs.getString("Dob"), rs.getString("username"), rs.getString("password"), rs.getInt("rid"));          
                list.add(u);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        UserDAO d = new UserDAO();
        System.out.println(d.updateUser("Hoang Thu Phuong", "htphoangphuong12@gmail.com", "0818165923", "Thai Binh", "01/07/2003", 1));
    }
}
