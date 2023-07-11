/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ProductDAO;
import Model.Category;
import Model.SizeNameAndQuantity;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddNewProduct", urlPatterns = {"/add"})
public class AddNewProduct extends HttpServlet {

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
            out.println("<title>Servlet AddNewProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewProduct at " + request.getContextPath() + "</h1>");
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
        // thêm sản phẩm mới vào db thì sản phẩm mới sẽ có so lượng các size từ tính tổng các size để nhập vào các 
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        String createDate = request.getParameter("createDate");
        String updateDate = request.getParameter("updateDate");
        String quantityS_raw = request.getParameter("quantityS");
        String quantityM_raw = request.getParameter("quantityM");
        String quantityL_raw = request.getParameter("quantityL");
        String quantityXL_raw = request.getParameter("quantityXL");
        String color = request.getParameter("color");
        String material = request.getParameter("material");
        String priceOriginal_raw = request.getParameter("priceOriginal");
        String quantitySold_raw = request.getParameter("quantitySold");
        String cid_raw = request.getParameter("cid");
        List<SizeNameAndQuantity> listQuantity = new ArrayList<>();
        int price, quantity, quantityS, quantityM, quantityL, quantityXL, priceOriginal, quantitySold, cid;
        try {
            price = Integer.parseInt(price_raw);
            quantityS = Integer.parseInt(quantityS_raw);
            quantityM = Integer.parseInt(quantityM_raw);
            quantityL = Integer.parseInt(quantityL_raw);
            quantityXL = Integer.parseInt(quantityXL_raw);
            priceOriginal = Integer.parseInt(priceOriginal_raw);
            listQuantity.add(new SizeNameAndQuantity("S", quantityS));
            listQuantity.add(new SizeNameAndQuantity("M", quantityM));
            listQuantity.add(new SizeNameAndQuantity("L", quantityL));
            listQuantity.add(new SizeNameAndQuantity("XL", quantityXL));
            quantity = quantityS + quantityL + quantityXL + quantityM;
            priceOriginal = Integer.parseInt(priceOriginal_raw);
            quantitySold = Integer.parseInt(quantitySold_raw);
            cid = Integer.parseInt(cid_raw);
            Category c = new Category();
            ProductDAO d = new ProductDAO();
            c = d.getCategoryById(cid);
            d.insertNewProduct(name, price, image, description, createDate, updateDate, quantity, color, material, priceOriginal, quantitySold, c, listQuantity);
            response.sendRedirect("crudproduct");
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
        processRequest(request, response);
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
