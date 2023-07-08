/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
@WebServlet(name="AddNewProduct", urlPatterns={"/add"})
public class AddNewProduct extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewProduct at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String image = request.getParameter("image");
        String description = request.getParameter("description");
        String createDate = request.getParameter("createDate");
        String category = request.getParameter("category");
        String quantity_raw = request.getParameter("quantity");
        String color = request.getParameter("color");
        String material = request.getParameter("material");
        String priceOriginal_raw = request.getParameter("priceOriginal");
        String quantitySold_raw = request.getParameter("quantitySold");
        String cid_raw = request.getParameter("cid");
        int price,quantity,priceOriginal,quantitySold, cid;
        try {
            price = Integer.parseInt("price_raw");
            quantity = Integer.parseInt("quantity_raw");
            priceOriginal = Integer.parseInt("priceOriginal_raw");
            quantitySold = Integer.parseInt("quantitySold_raw");            
            cid = Integer.parseInt("cid_raw");
        } catch (Exception e) {
        }
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
