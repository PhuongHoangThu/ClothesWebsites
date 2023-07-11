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
@WebServlet(name = "UpdateSizeServlet", urlPatterns = {"/updateSizeDashboard"})
public class UpdateSizeServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateSizeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateSizeServlet at " + request.getContextPath() + "</h1>");
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
        String quantityS_raw = request.getParameter("quantityS");
        String quantityM_raw = request.getParameter("quantityM");
        String quantityL_raw = request.getParameter("quantityL");
        String quantityXL_raw = request.getParameter("quantityXL");
        String pid_raw = request.getParameter("pid");
        List<SizeNameAndQuantity> listQuantity = new ArrayList<>();

        int pid, quantity, quantityS, quantityM, quantityL, quantityXL;
        try {
            pid = Integer.parseInt(pid_raw);
            quantityS = Integer.parseInt(quantityS_raw);
            quantityM = Integer.parseInt(quantityM_raw);
            quantityL = Integer.parseInt(quantityL_raw);
            quantityXL = Integer.parseInt(quantityXL_raw);
            listQuantity.add(new SizeNameAndQuantity("S", quantityS));
            listQuantity.add(new SizeNameAndQuantity("M", quantityM));
            listQuantity.add(new SizeNameAndQuantity("L", quantityL));
            listQuantity.add(new SizeNameAndQuantity("XL", quantityXL));
            quantity = quantityS + quantityL + quantityXL + quantityM;     
            ProductDAO d = new ProductDAO();
            d.updateSize(pid, quantity, listQuantity);
            response.sendRedirect("crudproduct");
        } catch (Exception e) {
            out.print(e);
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
