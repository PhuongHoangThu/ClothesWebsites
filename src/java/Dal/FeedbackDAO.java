/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedback() {
        List<Feedback> list = new ArrayList<>();
        String sql = "Select * from Feedback";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            ProductDAO d = new ProductDAO();
            UserDAO u = new UserDAO();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setProduct(d.getProductByid(rs.getInt("pid")));
                f.setUser(u.getAccount(rs.getInt("uid")));
                list.add(f);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void deleteFeedback(int fid){
        String sql = "Delete Feedback where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, fid);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        FeedbackDAO f = new FeedbackDAO();
        System.out.println(f.getAllFeedback().size());
    }
}
