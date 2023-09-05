/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Category;
import Model.ChartYear;
import Model.MonthAnalysis;
import Model.Orders;
import Model.Product;
import Model.QuantityMonth;
import Model.Sale;
import Model.Size;
import Model.SizeNameAndQuantity;
import Model.YearMonthRevenue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProductDAO extends DBContext {

    public List<ChartYear> getQuantityByYear() {
        List<ChartYear> list = new ArrayList<>();
        String sql = "select year(createdate) as years , sum(quantitySold) as quantities\n"
                + "from Product\n"
                + "group by Year(createdate)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ChartYear c = new ChartYear();
                c.setQuantity(rs.getInt("quantities"));
                c.setYear(rs.getInt("years"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<QuantityMonth> getQuantityCateByMonthAndYear(int year, int month) {
        List<QuantityMonth> list = new ArrayList<>();
        String sql = "select year(createdate) as years , sum(quantitySold) as quantities,Month(createdate) as months, cid\n"
                + "from Product\n"
                + "where year(createdate) = ? and Month(createdate) = ? \n"
                + "group by Year(createdate), Month(createdate), cid";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            st.setInt(2, month);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                QuantityMonth c = new QuantityMonth();
                c.setQuantity(rs.getInt("quantities"));
                c.setYear(rs.getInt("years"));
                c.setCategory(getCategoryById(rs.getInt("cid")));
                c.setMonth(rs.getInt("months"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<YearMonthRevenue> getRevenueYearMonth(int year) {
        List<YearMonthRevenue> list = new ArrayList<>();
        String sql = "select year(createdate) as years , sum(quantitySold) as quantities,Month(createdate) as months,sum(quantitySold*price) as Revenue\n"
                + "from Product\n"
                + "where year(createdate) = ? \n"
                + "group by Year(createdate), Month(createdate)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                YearMonthRevenue c = new YearMonthRevenue();
                c.setQuantity(rs.getInt("quantities"));
                c.setYear(rs.getInt("years"));
                c.setMonth(rs.getInt("months"));
                c.setTotalmoney(rs.getInt("Revenue"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<MonthAnalysis> getQuantityByMonth(int year) {
        List<MonthAnalysis> list = new ArrayList<>();
        String sql = "select year(CreateDate) as years , sum(quantitySold) as quantities,Month(CreateDate) as months\n"
                + "from Product\n"
                + "where Year(CreateDate) = ? "
                + "group by Year(CreateDate), Month(CreateDate)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MonthAnalysis c = new MonthAnalysis();
                c.setMonth(rs.getInt("months"));
                c.setQuantity(rs.getInt("quantities"));
                c.setYear(rs.getInt("years"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getRevenue() {
        String sql = "select sum(price*quantitySold) as revenue from product";
        int revenue = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                revenue = rs.getInt("revenue");
            }
        } catch (Exception e) {
        }

        return revenue;
    }

    public int getProfit() {
        String sql = "select sum(price*quantitySold - (OriginalPrice*quantitySold)) as profit from product";
        int profit = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                profit = rs.getInt("profit");
            }
        } catch (Exception e) {
        }

        return profit;
    }

    public int getQuantitySold() {
        String sql = "select sum(quantitySold) as quantitySold from Product";
        int quantitySold = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                quantitySold = rs.getInt("quantitySold");
            }
        } catch (Exception e) {
        }

        return quantitySold;
    }

    public int getQuantityInventory() {
        String sql = "select sum(quantity) as quantity from Product";
        int quantity = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (Exception e) {
        }

        return quantity;
    }

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

    public List<Product> getProductsLatest() {
        String sql = "select * from Product order by CreateDate desc ";
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

    public List<Product> getProductsSold() {
        String sql = "select * from Product order by QuantitySold desc ";
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

    public List<Product> getProductsPrice() {
        String sql = "select * from Product order by Price desc ";
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

    public void updateSize(int pid, int quantity, List<SizeNameAndQuantity> listQuantity) {
        try {
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
                // thay đổi số lượng bảng product
                String sql = "UPDATE [dbo].[Product]\n"
                        + " SET [quantity] = ?\n"
                        + " WHERE id = ?";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, quantity);
                st.setInt(2, pid);
                st.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteteCategoryByID(int pid) {
        try {
            // xóa size trong orderdetail
            String sql0 = "Select * from Size where pid =? ";
            PreparedStatement st0 = connection.prepareStatement(sql0);
            st0.setInt(1, pid);
            ResultSet rs = st0.executeQuery();
            while (rs.next()) {
                String sql01 = "DELETE FROM [dbo].[OrderDetail]\n"
                        + "      WHERE sid = ?";
                PreparedStatement st01 = connection.prepareStatement(sql01);
                st01.setInt(1, rs.getInt("sid"));
                st01.executeUpdate();
            }

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
            st1.setInt(1, pid);
            st1.executeUpdate();
            //xóa sản phẩm trong bảng OrderDetail
            String sql2 = "DELETE FROM [dbo].[OrderDetail]\n"
                    + "      WHERE pid=?";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, pid);
            st2.executeUpdate();
            // xóa sản phẩm trong bảng Feedback
            String sql3 = "DELETE FROM [dbo].[Feedback]\n"
                    + "      WHERE pid=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, pid);
            st3.executeUpdate();
            // Xóa trong bảng product
            String sql4 = "delete from Product where id=?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            st4.setInt(1, pid);
            st4.executeUpdate();
        } catch (Exception e) {

        }

    }

    public void deleteCategory(int id) {
        String sql = "DELETE FROM [dbo].[Category]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Product> searchByKey(String key) {
        String sql = "select * from Product";
        List<Product> list = new ArrayList<>();
        if (key != "") {
            sql += " where ProductName like N'%" + key + "%'";
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

    public List<Product> getProductsByPrice(int from, int to) {
        String sql = "select * from Product where price between ? and ?";
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, from);
            st.setInt(2, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String sql1 = "select * from Sale where pid = ?";
                PreparedStatement st1 = connection.prepareStatement(sql1);
                st1.setInt(1, rs.getInt("id"));
                ResultSet rs1 = st1.executeQuery();
                if (rs1.next()) {
                    if ((rs.getInt("Price") - rs.getInt("Price") * rs1.getInt("discount")) >= from && (rs.getInt("Price") - rs.getInt("Price") * rs1.getInt("discount") <= to)) {
                        System.out.println((rs.getInt("Price") - rs.getInt("Price") * rs1.getInt("discount")));
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
                    continue;
                } else {
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
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProductsByColor(String color) {
        String sql = "select * from Product where color like N'%" + color + "%'";
        System.out.println(sql);
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

    public List<Product> getProductsByMaterial(String material) {
        String sql = "select * from Product where material like N'%" + material + "%'";
        System.out.println(sql);
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

    public void insertCategory(String name) {
        String sql = "INSERT INTO [dbo].[Category]\n"
                + "([Categoryname])\n"
                + "VALUES\n"
                + "(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (Exception e) {
        }
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

    public void updateCategory(int id, String name) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [Categoryname] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        ProductDAO d = new ProductDAO();
        System.out.println(d.getRevenueYearMonth(2022).size());
    }

}
