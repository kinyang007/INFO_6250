/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.model.*;
import org.apache.poi.hssf.usermodel.*;
/**
 *
 * @author Administrator
 */
public class DisplayExcel extends HttpServlet {

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
        String fileName = request.getParameter("file");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Display Excel</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align=\"center\">" + fileName + "</h1>");

        File file = new File(request.getRealPath("/files/") + fileName);
        FileInputStream fileInputStream = new FileInputStream(file);
        
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        for (int p = 0; p < workbook.getNumberOfSheets(); p++) {
            HSSFSheet sheet = workbook.getSheetAt(p);
            out.println("<p>" + String.valueOf(p+1) + ". " + sheet.getSheetName() + "</p>");
            out.println("<table border=1>");
            for (int i = 0; i < sheet.getLastRowNum()+1; i++) {
                HSSFRow row = sheet.getRow(i);
                out.println("<tr>");
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    HSSFCell cell = row.getCell(j);
                    if (i == 0) {
                        out.println("<th>" + cell.toString());
                    } else {
                        out.println("<td>" + cell.toString());
                    }
                }
            }
            out.println("</table>");
        }
        out.println("</body>");
        out.println("</html>");
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
        processRequest(request, response);
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
