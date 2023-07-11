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
        String sql = "Select * from feedback";
        try {
            Feedback f = new Feedback();
            ProductDAO dao = new ProductDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                f.setId(rs.getInt("id"));
                f.setContent(rs.getString("content"));
                f.setProduct(dao.getProductByid(rs.getInt("pid")));
                list.add(f);
            }
        } catch (Exception e) {
        }

        return list;
    }
}
