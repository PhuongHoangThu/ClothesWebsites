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
        //processRequest(request, response);
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String cid_raw = request.getParameter("cid");
        String[] pp = {"Dưới 200.000VNĐ",
            "Từ 200.000VNĐ - 500.000VNĐ",
            "Từ 500.000VNĐ - 700.000VNĐ",
            "Từ Từ 700.000VNĐ - 1.000.000VNĐ",
            "Trên 1.000.000đ"};
        boolean[] pb = new boolean[pp.length + 1];
        pb[0] = true;
        ProductDAO d = new ProductDAO();
        int cid;

        try {
            cid = Integer.parseInt(cid_raw);
            List<Product> listProduct = d.getAllProductsByCategoryID(cid);
            int pageProduct, numperpage = 12;
            int sizeProduct = listProduct.size();
            int numProduct = (sizeProduct % numperpage == 0 ? (sizeProduct / numperpage) : (sizeProduct / numperpage + 1));
            String xpageProduct = request.getParameter("pageProduct");
            if (xpageProduct == null) {
                pageProduct = 1;
            } else {
                pageProduct = Integer.parseInt(xpageProduct);
            }
            int startProduct, endProduct;
            startProduct = (pageProduct - 1) * numperpage;
            endProduct = Math.min(pageProduct * numperpage, sizeProduct);
            List<Product> listSubProduct = d.getListByPage(listProduct, startProduct, endProduct);
            session.setAttribute("cid", cid);
            session.setAttribute("pp", pp);
            session.setAttribute("pb", pb);
            session.setAttribute("pageProduct", pageProduct);
            session.setAttribute("numProduct", numProduct);
            session.setAttribute("listSubProduct", listSubProduct);

        } catch (Exception e) {
        }
        List<Category> listCate = (List<Category>) session.getAttribute("listCate");
        request.setAttribute("listCate", listCate);
        request.getRequestDispatcher("shop.jsp").forward(request, response);

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
        String cid_raw = request.getParameter("cid");
        String[] pp = {"Dưới 200.000VNĐ",
            "Từ 200.000VNĐ - 500.000VNĐ",
            "Từ 500.000VNĐ - 700.000VNĐ",
            "Từ Từ 700.000VNĐ - 1.000.000VNĐ",
            "Trên 1.000.000đ"};
        boolean[] pb = new boolean[pp.length + 1];
        pb[0] = true;
        ProductDAO d = new ProductDAO();
        int cid;

        try {
            cid = Integer.parseInt(cid_raw);
            List<Product> listProduct = d.getAllProductsByCategoryID(cid);
            int pageProduct, numperpage = 12;
            int sizeProduct = listProduct.size();
            int numProduct = (sizeProduct % numperpage == 0 ? (sizeProduct / numperpage) : (sizeProduct / numperpage + 1));
            String xpageProduct = request.getParameter("pageProduct");
            if (xpageProduct == null) {
                pageProduct = 1;
            } else {
                pageProduct = Integer.parseInt(xpageProduct);
            }
            int startProduct, endProduct;
            startProduct = (pageProduct - 1) * numperpage;
            endProduct = Math.min(pageProduct * numperpage, sizeProduct);
            List<Product> listSubProduct = d.getListByPage(listProduct, startProduct, endProduct);
            session.setAttribute("cid", cid);
            session.setAttribute("pageProduct", pageProduct);
            session.setAttribute("numProduct", numProduct);
            session.setAttribute("listSubProduct", listSubProduct);

        } catch (Exception e) {
        }
        List<Category> listCate = (List<Category>) session.getAttribute("listCate");
        request.setAttribute("listCate", listCate);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
