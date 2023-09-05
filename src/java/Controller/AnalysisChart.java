/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ProductDAO;
import Model.QuantityMonth;
import Model.YearMonthRevenue;
import Model.YearRevenue;
import com.google.gson.Gson;
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
@WebServlet(name = "AnalysisChart", urlPatterns = {"/analysischart"})
public class AnalysisChart extends HttpServlet {

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
            out.println("<title>Servlet AnalysisChart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AnalysisChart at " + request.getContextPath() + "</h1>");
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
        ProductDAO d = new ProductDAO();
        PrintWriter out = response.getWriter();
        List<YearMonthRevenue> listRevenueMonth2022 = d.getRevenueYearMonth(2022);
        List<YearMonthRevenue> listRevenueMonth2023 = d.getRevenueYearMonth(2023);

        // insert cho đủ 12 tháng năm 2022
        List<YearRevenue> listchart = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            YearRevenue year = new YearRevenue();
            year.setMonth(i);
            for (int j = 0; j < listRevenueMonth2022.size(); j++) {
                if (i == listRevenueMonth2022.get(j).getMonth()) {
                    year.setRevenue2022(listRevenueMonth2022.get(j).getTotalmoney());
                    break;
                } else {
                    year.setRevenue2022(0);
                }
            }
            for (int k = 0; k < listRevenueMonth2023.size(); k++) {
                if (i == listRevenueMonth2023.get(k).getMonth()) {
                    year.setRevenue2023(listRevenueMonth2023.get(k).getTotalmoney());
                    break;
                } else {
                    year.setRevenue2023(0);
                }
            }
            listchart.add(year);
        }

        // chuyen sang json
        Gson gson = new Gson();
        String listchartJson = gson.toJson(listchart);
        
        // gui
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(listchartJson);

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
