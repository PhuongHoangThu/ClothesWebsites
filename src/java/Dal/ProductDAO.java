/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Category;
import Model.Orders;
import Model.Product;
import Model.Sale;
import Model.Size;
import Model.SizeNameAndQuantity;
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

    public void insertNewProduct(String name, int price, String image, String description, String createDate, String updateDate, int quantity, String color, String material, int priceOriginal, int quantitySold, Category c, List<SizeNameAndQuantity> listQuantity) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([ProductName],[Price],[image] ,[Description],[CreateDate],[UpdateDate],[cid],[quantity],[Color],[Material],[QuantitySold],[OriginalPrice])\n"
                + "VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, price);
            st.setString(3, image);
            st.setString(4, description);
            st.setString(5, createDate);
            st.setString(6, updateDate);
            st.setInt(7, c.getId());
            st.setInt(8, quantity);
            st.setString(9, color);
            st.setString(10, material);
            st.setInt(11, quantitySold);
            st.setInt(12, priceOriginal);
            st.executeUpdate();
            // lấy pid của sản phẩm vừa thêm vào
            String sql1 = "select top 1 id from [product] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //add bang Size
            Size s = new Size();
            if (rs.next()) {
                int oid = rs.getInt("id");
                for (SizeNameAndQuantity i : listQuantity) {
                    String sql2 = "INSERT INTO [dbo].[Size]\n"
                            + "           ([name]\n"
                            + "           ,[pid]\n"
                            + "           ,[quantity])\n"
                            + "VALUES (?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setString(1, i.getName());
                    st2.setInt(2, oid);
                    st2.setInt(3, i.getQuantity());
                    st2.executeUpdate();
                }
            }
        } catch (Exception e) {
        }
    }

    public void updateProduct(int pid, String name, int price, String image, String description, String createDate, String updateDate, int quantity, String color, String material, int priceOriginal, int quantitySold, Category c, List<SizeNameAndQuantity> listQuantity) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[Description] =?\n"
                + "      ,[CreateDate] = ?\n"
                + "      ,[UpdateDate] = ?\n"
                + "      ,[cid] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[Color] = ?\n"
                + "      ,[Material] = ?\n"
                + "      ,[QuantitySold] =?\n"
                + "      ,[OriginalPrice] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, price);
            st.setString(3, image);
            st.setString(4, description);
            st.setString(5, createDate);
            st.setString(6, updateDate);
            st.setInt(7, c.getId());
            st.setInt(8, quantity);
            st.setString(9, color);
            st.setString(10, material);
            st.setInt(11, quantitySold);
            st.setInt(12, priceOriginal);
            st.setInt(13, pid);
            st.executeUpdate();
            //add bang Size
            Size s = new Size();
            int oid = pid;
            for (SizeNameAndQuantity i : listQuantity) {
                String sql2 = "UPDATE [dbo].[Size]\n"
                        + "   SET [name] = ?\n"
                        + "      ,[pid] = ?\n"
                        + "      ,[quantity] = ?\n"
                        + " WHERE id = ?";
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setString(1, i.getName());
                st2.setInt(2, oid);
                st2.setInt(3, i.getQuantity());
                st2.setInt(4, getProductByidAndSize(oid, i.getName()).getId());
                System.out.println("1" + getProductByidAndSize(oid, i.getName()).getId());
                st2.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteteCategoryByID(int pid) {
        try {
            // xóa sản phẩm có d = ? ở trong bảng size
            String sql = "DELETE FROM [dbo].[Size]\n"
                    + "      WHERE pid=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            st.executeUpdate();
            // xóa sản phẩm có d = ? ở trong bảng Sale
            String sql1 = "DELETE FROM [dbo].[Sale]\n"
                    + "      WHERE pid=?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st.setInt(1, pid);
            st.executeUpdate();
            //xóa sản phẩm trong bảng OrderDetail
            String sql2 = "DELETE FROM [dbo].[OrderDetail]\n"
                    + "      WHERE pid=?";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st.setInt(1, pid);
            st.executeUpdate();
            // xóa sản phẩm trong bảng Feedback
            String sql3 = "DELETE FROM [dbo].[Feedback]\n"
                    + "      WHERE pid=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st.setInt(1, pid);
            st.executeUpdate();
            // Xóa trong bảng product
            String sql4 = "delete from Product where id=?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            st4.setInt(1, pid);
            st4.executeUpdate();
        } catch (Exception e) {

        }

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

    public List<Size> getSizeByPid(int pid) {
        String sql = "select * from Size where pid = ?";
        List<Size> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
    public List<Orders> getListOrderByPage(List<Orders> list, int start, int end) {
        ArrayList<Orders> arr = new ArrayList<>();
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
//        ProductDAO dao = new ProductDAO();
//        String name = "Áo sơ mi";
//        String image = "img/aosomi.png";
//        String description = "Đep";
//        String createDate = "2023/07/08";
//        String updateDate = "2023/07/08";
//        String color = "Xanh";
//        String material = "Cotton";
//        List<SizeNameAndQuantity> listQuantity = new ArrayList<>();
//        int price = 300000;
//        int quantityS = 4;
//        int quantityM = 5;
//        int quantityL = 6;
//        int quantityXL = 7;
//        listQuantity.add(new SizeNameAndQuantity("S", quantityS));
//        listQuantity.add(new SizeNameAndQuantity("M", quantityM));
//        listQuantity.add(new SizeNameAndQuantity("L", quantityL));
//        listQuantity.add(new SizeNameAndQuantity("XL", quantityXL));
//        int quantity = quantityS + quantityL + quantityXL + quantityM;
//        int priceOriginal = 100000;
//        int quantitySold = 0;
//        int cid = 15;
//        Category c = new Category();
//        c = dao.getCategoryById(cid);
//        System.out.println(dao.getAllProducts().size());
//        dao.updateProduct(94, name, price, image, description, createDate, updateDate, quantity, color, material, priceOriginal, quantitySold, c, listQuantity);
//        System.out.println(dao.getAllProducts().size());
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(1);
//        list.add(1);
//        System.out.println("trước : " + list.size());
//        list.removeAll(list);
//        System.out.println("sau : " + list.size());

    }

}
