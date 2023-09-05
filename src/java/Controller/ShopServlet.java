/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ProductDAO;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "ShopServlet", urlPatterns = {"/shop"})
public class ShopServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ProductDAO d = new ProductDAO();
        String cid_raw = request.getParameter("cid");

        int cid;
        // giá
        String[] pp = {"Dưới 200.000VNĐ",
            "Từ 200.000VNĐ - 500.000VNĐ",
            "Từ 500.000VNĐ - 700.000VNĐ",
            "Từ 700.000VNĐ - 1.000.000VNĐ",
            "Trên 1.000.000đ"};
        boolean[] pb = new boolean[pp.length + 1];
        pb[0] = true;
        // màu sắc
        String[] cp = {"Đen", "Trắng", "Kem", "Đỏ", "Xanh", "Xám", "Vàng", "Cam"};
        boolean[] cb = new boolean[cp.length + 1];
        cb[0] = true;
        // chất vải
        String[] mp = {"Đũi", "Cotton", "Len", "Polyeste"};
        boolean[] mb = new boolean[pp.length + 1];
        mb[0] = true;
        try {
            cid = Integer.parseInt(cid_raw);
            List<Product> products = d.getAllProductsByCategoryID(cid);
            session.setAttribute("products", products);
            session.setAttribute("pp", pp);
            session.setAttribute("pb", pb);
            session.setAttribute("cp", cp);
            session.setAttribute("cb", cb);
            session.setAttribute("mp", mp);
            session.setAttribute("mb", mb);
            List<Category> listCate = (List<Category>) session.getAttribute("listCate");
            session.setAttribute("listCate", listCate);
            out.print("Size in Shop get"+products.size());
            response.sendRedirect("page");
            //request.getRequestDispatcher("shop.jsp").forward(request, response);
        } catch (Exception e) {
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String sort = request.getParameter("sort");
        out.print(sort);
        String cid_raw = request.getParameter("cid");
        String[] price = request.getParameterValues("price");

        String[] material = request.getParameterValues("material");
        String[] color = request.getParameterValues("color");

        String key = request.getParameter("key");
        String[] pp = {"Dưới 200.000VNĐ",
            "Từ 200.000VNĐ - 500.000VNĐ",
            "Từ 500.000VNĐ - 700.000VNĐ",
            "Từ 700.000VNĐ - 1.000.000VNĐ",
            "Trên 1.000.000đ"};
        boolean[] pb = new boolean[pp.length + 1];
        // màu sắc
        String[] cp = {"Đen", "Trắng", "Kem", "Đỏ", "Xanh", "Xám", "Vàng", "Cam"};
        boolean[] cb = new boolean[cp.length + 1];
        // material
        String[] mp = {"Đũi", "Cotton", "Len", "Polyeste"};
        boolean[] mb = new boolean[mp.length + 1];

        ProductDAO d = new ProductDAO();
        List<Product> products = new ArrayList<>();
        int cid;
        try {
            if (cid_raw == null) {
                cid = 0;
            } else {
                cid = Integer.parseInt(cid_raw);
            }
            out.println(cid);
            if (key != null) {
                products = d.searchByKey(key);
            }

            if (cid_raw != null) {
                products = d.getAllProductsByCategoryID(cid);
            }
            if (price == null) {
                pb[0] = true;
                for (int i = 1; i < pb.length; i++) {
                    pb[i] = false;
                }
            }
            if (price != null) {
                int from = 0, to = 0;
                for (int i = 0; i < price.length; i++) {

                    List<Product> temp = new ArrayList<>();
                    if (price[i].equals("0")) {
                        from = 0;
                        to = 3000000;
                        products = d.getProductsByPrice(from, to);
                        pb[0] = true;
                        break;
                    } else {
                        if (price[i].equals("1")) {
                            from = 0;
                            to = 200000;
                            temp = d.getProductsByPrice(from, to);
                            products.addAll(temp);
                            pb[1] = true;
                        }
                        if (price[i].equals("2")) {
                            from = 200000;
                            to = 500000;
                            temp = d.getProductsByPrice(from, to);
                            products.addAll(temp);
                            pb[2] = true;
                        }
                        if (price[i].equals("3")) {
                            from = 500000;
                            to = 700000;
                            temp = d.getProductsByPrice(from, to);
                            products.addAll(temp);
                            pb[3] = true;
                        }
                        if (price[i].equals("4")) {
                            from = 700000;
                            to = 1000000;
                            temp = d.getProductsByPrice(from, to);
                            products.addAll(temp);
                            pb[4] = true;
                        }
                        if (price[i].equals("5")) {
                            from = 1000000;
                            to = 3000000;
                            temp = d.getProductsByPrice(from, to);
                            products.addAll(temp);
                            pb[5] = true;
                        }
                    }
                }
            }
            if (price == null) {
                pb[0] = true;
                for (int i = 1; i < pb.length; i++) {
                    pb[i] = false;
                }
            }
            //color
            if (color != null) {
                for (int i = 0; i < color.length; i++) {
                    List<Product> temp = new ArrayList<>();
                    if (color[i].equals("0")) {
                        String colorPro = "";
                        products = d.getProductsByColor(colorPro);
                        cb[0] = true;
                        break;
                    } else {
                        if (color[i].equals("1")) {
                            temp = d.getProductsByColor("Đen");
                            products.addAll(temp);
                            cb[1] = true;
                        }
                        if (color[i].equals("2")) {
                            temp = d.getProductsByColor("Trắng");
                            products.addAll(temp);
                            cb[2] = true;
                        }
                        if (color[i].equals("3")) {
                            temp = d.getProductsByColor("Kem");
                            products.addAll(temp);
                            cb[3] = true;
                        }
                        if (color[i].equals("4")) {
                            temp = d.getProductsByColor("Đỏ");
                            products.addAll(temp);
                            cb[4] = true;
                        }
                        if (color[i].equals("5")) {
                            temp = d.getProductsByColor("Xanh");
                            products.addAll(temp);
                            cb[5] = true;
                        }
                        if (color[i].equals("6")) {
                            temp = d.getProductsByColor("Xám");
                            products.addAll(temp);
                            cb[6] = true;
                        }
                        if (color[i].equals("7")) {
                            temp = d.getProductsByColor("Vàng");
                            products.addAll(temp);
                            cb[7] = true;
                        }
                        if (color[i].equals("8")) {
                            temp = d.getProductsByColor("Cam");
                            products.addAll(temp);
                            cb[8] = true;
                        }
                    }
                }
            }
            if (color == null) {
                cb[0] = true;
            }
            if (material != null) {

                for (int i = 0; i < material.length; i++) {
                    List<Product> temp = new ArrayList<>();
                    if (material[i].equals("0")) {
                        products = d.getProductsByMaterial("");
                        mb[0] = true;
                        break;
                    } else {
                        if (material[i].equals("1")) {
                            temp = d.getProductsByMaterial("Đũi");
                            products.addAll(temp);
                            mb[1] = true;
                        }
                        if (material[i].equals("2")) {
                            temp = d.getProductsByMaterial("Cotton");
                            products.addAll(temp);
                            mb[2] = true;
                        }
                        if (material[i].equals("3")) {
                            temp = d.getProductsByMaterial("Len");
                            products.addAll(temp);
                            mb[3] = true;
                        }
                        if (material[i].equals("4")) {
                            temp = d.getProductsByMaterial("Polyeste");
                            products.addAll(temp);
                            mb[4] = true;
                        }

                    }
                }
            }
            if (material == null) {
                mb[0] = true;
            }
            if (sort != null) {
                if (sort.equalsIgnoreCase("Mới nhất")) {
                    products = d.getProductsLatest();
                }
                if (sort.equalsIgnoreCase("Bán chạy nhất")) {
                    products = d.getProductsSold();
                }
                if (sort.equalsIgnoreCase("Giá tiền")) {
                    products = d.getProductsPrice();
                }

            }
            int page, numperpage = 12;
            int size = products.size();
            int num = (size % numperpage == 0 ? (size / numperpage) : (size / numperpage + 1));
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * numperpage;
            end = Math.min(page * numperpage, size);
            List<Product> listSub = d.getListByPage(products, start, end);
            request.setAttribute("list", listSub);
            request.setAttribute("page", page);
            request.setAttribute("num", num);
            session.setAttribute("products", products);
            session.setAttribute("cid", cid);
            session.setAttribute("key", key);
            session.setAttribute("pb", pb);
            session.setAttribute("pp", pp);
            session.setAttribute("cb", cb);
            session.setAttribute("cp", cp);
            session.setAttribute("mb", mb);
            session.setAttribute("mp", mp);
        } catch (Exception e) {
        }
        List<Category> listCate = (List<Category>) session.getAttribute("listCate");

        request.setAttribute("listCate", listCate);
        response.sendRedirect("page");
        //request.getRequestDispatcher("page").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
