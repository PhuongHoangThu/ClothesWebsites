/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Category;
import Model.Product;
import Model.Sale;
import Model.Size;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProductDAO extends DBContext {

    public List<Product> getAllProducts() {
        String sql = "select * from Product";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    

    public List<Product> getAllProductsByCategoryID(int cid) {
        String sql = "select * from Product";
        List<Product> list = new ArrayList<>();
        if (cid != 0) {
            sql += " where cid = " + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Size> getSizeByPid(int pid){
        String sql = "select * from Size where pid = ?";
        List<Size> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Size s = new Size();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setProduct(getProductByid(pid));
                s.setQuantity(rs.getInt("quantity"));
                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<Product> getNewestProduct() {
        String sql = "select top 10 percent * from Product order by createdate desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getBestSellerProduct() {
        String sql = "SELECT top 10 percent *\n"
                + "  FROM [PRJ_Project].[dbo].[Product]\n"
                + "  order by QuantitySold desc";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductByid(int id) {
        String sql = "select  * from Product where id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Size getProductByidAndSize(int pid, String size) {

        String sql = "select  * from Size where pid = ? and name = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            st.setString(2, size);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Size e = new Size();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("name"));
                e.setProduct(getProductByid(pid));
                e.setQuantity(rs.getInt("quantity"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Product> getProductByKey(String searchName, int price, String color, String material) {
        String sql = "select  * from Product where 1=1";
        List<Product> list = new ArrayList<>();
        if (searchName != "") {
            sql += " and ProductName like N'%" + searchName + "%'";
        }
        //price
        if (price == 1) {
            sql += " and price <= 100000 ";
        } else if (price == 2) {
            sql += " and price between 100000 and 300000 ";
        } else if (price == 3) {
            sql += " and price between 300000 and 500000 ";
        } else if (price == 4) {
            sql += " and price between 500000 and 1000000 ";
        } else if (price == 5) {
            sql += " and price >= 1000000 ";
        }
        //color
        if (color.equalsIgnoreCase("đen")) {
            sql += " and color = N'đen' ";
        } else if (color.equalsIgnoreCase("trắng")) {
            sql += " and color = N'trắng' ";
        } else if (color.equalsIgnoreCase("kem")) {
            sql += " and color = N'kem' ";
        } else if (color.equalsIgnoreCase("đỏ")) {
            sql += " and color = N'đỏ' ";
        } else if (color.equalsIgnoreCase("xanh")) {
            sql += " and color = N'xanh' ";
        } else if (color.equalsIgnoreCase("xám")) {
            sql += " and color = N'xám' ";
        } else if (color.equalsIgnoreCase("vàng")) {
            sql += " and color = N'vàng' ";
        } else if (color.equalsIgnoreCase("cam")) {
            sql += " and color = N'cam' ";
        }
        // material

        if (material.equalsIgnoreCase("đũi")) {
            sql += " and material = N'đũi' ";
        } else if (material.equalsIgnoreCase("cotton")) {
            sql += " and material = N'cotton' ";
        } else if (material.equalsIgnoreCase("len")) {
            sql += " and material = N'len' ";
        } else if (material.equalsIgnoreCase("Polyeste")) {
            sql += " and material = N'Polyeste' ";
        }
        System.out.println(sql);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Sale> getSaleProduct() {
        String sql = "select s.[id]as sid,p.id as pid,discount,startdate,finishdate,ProductName,Price,image,[Description],[CreateDate],[UpdateDate],[cid],[quantity],[Color],[Material],OriginalPrice,QuantitySold\n"
                + "from Sale s inner join Product p on p.id = s.pid \n"
                + "where getdate() between s.startdate and s.finishdate";
        List<Sale> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product e = new Product();
                Sale s = new Sale();
                e.setId(rs.getInt("pid"));
                e.setName(rs.getString("ProductName"));
                e.setPrice(rs.getInt("Price"));
                e.setImage(rs.getString("image"));
                e.setDescription(rs.getString("Description"));
                e.setCreateDate(rs.getString("CreateDate"));
                e.setUpdateDate(rs.getString("UpdateDate"));
                e.setCategory(getCategoryById(rs.getInt("cid")));
                e.setQuantity(rs.getInt("quantity"));
                e.setColor(rs.getString("Color"));
                e.setMaterial(rs.getString("Material"));
                e.setPriceOriginal(rs.getInt("OriginalPrice"));
                e.setQuantitySold(rs.getInt("QuantitySold"));
                s.setId(rs.getInt("sid"));
                s.setProduct(e);
                s.setDiscount(rs.getFloat("discount"));
                s.setStartdate(rs.getString("startdate"));
                s.setFinishdate(rs.getString("finishdate"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Category getCategoryById(int id) {
        String sql = "Select * from Category where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setId(id);
                c.setCategoryname(rs.getString("Categoryname"));
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Category> getAllCategory() {
        String sql = "Select * from Category ";
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setCategoryname(rs.getString("Categoryname"));
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Sale getSaleProductByPid(int pid) {
        String sql = " Select * from Sale s where getdate() between s.startdate and s.finishdate and pid = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Sale s = new Sale();
                s.setId(pid);
                s.setDiscount(rs.getFloat("discount"));
                s.setStartdate(rs.getString("startdate"));
                s.setFinishdate(rs.getString("finishdate"));
                s.setProduct(getProductByid(pid));
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Sale> getListSaleByPage(List<Sale> list, int start, int end) {
        ArrayList<Sale> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }


    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        //dao.getAllProductsByCategoryID(13);
        System.out.println(dao.getSizeByPid(6).size());
    }

}
