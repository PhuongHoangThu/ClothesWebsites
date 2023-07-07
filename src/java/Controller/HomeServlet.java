/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ProductDAO;
import Model.Category;
import Model.Product;
import Model.Sale;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "NewestProductServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            /* TODO output your pageNewest here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewestProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewestProductServlet at " + request.getContextPath() + "</h1>");
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
        ProductDAO d = new ProductDAO();

        // Sản phẩm mới nhất
        List<Product> listNewest = d.getNewestProduct();
        int pageNewest, numperpage = 4;
        int sizeNewest = listNewest.size();
        int numNewest = (sizeNewest % numperpage == 0 ? (sizeNewest / numperpage) : (sizeNewest / numperpage + 1));
        String xpage = request.getParameter("pageNewest");
        if (xpage == null) {
            pageNewest = 1;
        } else {
            pageNewest = Integer.parseInt(xpage);
        }
        int start, end;
        start = (pageNewest - 1) * numperpage;
        end = Math.min(pageNewest * numperpage, sizeNewest);
        List<Product> listSubNewest = d.getListByPage(listNewest, start, end);
        request.setAttribute("listNewest", listSubNewest);
        request.setAttribute("pageNewest", pageNewest);
        request.setAttribute("numNewest", numNewest);
        // sản phẩm đang sale

        List<Sale> listSale = d.getSaleProduct();
        int pageSale;
        int sizeSale = listSale.size();
        int numSale = (sizeSale % numperpage == 0 ? (sizeSale / numperpage) : (sizeSale / numperpage + 1));
        String xpageSale = request.getParameter("pageSale");
        if (xpageSale == null) {
            pageSale = 1;
        } else {
            pageSale = Integer.parseInt(xpageSale);
        }
        int startSale, endSale;
        startSale = (pageSale - 1) * numperpage;
        endSale = Math.min(pageSale * numperpage, sizeSale);
        List<Sale> listSubSale = d.getListSaleByPage(listSale, startSale, endSale);
        request.setAttribute("listSale", listSubSale);
        request.setAttribute("pageSale", pageSale);
        request.setAttribute("numSale", numSale);
        // Lấy tất cả các Danh mục
        HttpSession session = request.getSession();           
        List<Category> listCate = d.getAllCategory();
        session.setAttribute("listCate", listCate);
        // Lấy các sản phẩm Best Seller
        List<Product> listBestSeller = d.getBestSellerProduct();
        int pageBestSeller;
        int sizeBestSeller = listBestSeller.size();
        int numBestSeller = (sizeBestSeller % numperpage == 0 ? (sizeBestSeller / numperpage) : (sizeBestSeller / numperpage + 1));
        String xpageBestSeller = request.getParameter("pageBestSeller");
        if (xpageBestSeller == null) {
            pageBestSeller = 1;
        } else {
            pageBestSeller = Integer.parseInt(xpageBestSeller);
        }
        int startBestSeller, endBestSeller;
        startBestSeller = (pageBestSeller - 1) * numperpage;
        endBestSeller = Math.min(pageBestSeller * numperpage, sizeBestSeller);
        List<Product> listSubBestSeller = d.getListByPage(listBestSeller, startBestSeller, endBestSeller);
        request.setAttribute("listBestSeller", listSubBestSeller);
        request.setAttribute("pageBestSeller", pageBestSeller);
        request.setAttribute("numBestSeller", numBestSeller);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
