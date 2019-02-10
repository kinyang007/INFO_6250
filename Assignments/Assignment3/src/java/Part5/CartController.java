/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


/**
 *
 * @author Administrator
 */
public class CartController extends HttpServlet {

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
        HttpSession session = request.getSession();
	Cart cart = (Cart)session.getAttribute("cart");
	if (cart == null) {
            cart = new Cart();
        }
        Enumeration<String> items = request.getParameterNames();
        while (items.hasMoreElements()) {
            String name = items.nextElement();
            if (name.equals("Submit")) continue;
            String amount = request.getParameter(name);
            int count = Integer.parseInt(amount);
            if (count == 0) {
                cart.deleteItem(name);
            } else {
                cart.modifyItemCount(name, count);
            }
        }
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("Part5/ViewCart.jsp").forward(request, response);
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
	String[] items = request.getParameterValues("books");
	if (items == null)
            items = request.getParameterValues("music");
	if (items == null)
            items = request.getParameterValues("computers");
        
        HttpSession session = request.getSession();
	Cart cart = (Cart)session.getAttribute("cart");
	if (cart == null) {
            cart = new Cart();
	}
        
        if (items == null) {
            session.setAttribute("items", items);
            session.setAttribute("cart", cart);
            request.getRequestDispatcher("Part5/AddSuccessful.jsp").forward(request, response);
            return;
        }
        
	for (String name : items) {
            Item item = cart.findItem(name);
            if (item != null) {
                int amount = item.getCount();
                cart.modifyItemCount(name, ++amount);
            } else {
                cart.addItem(new Item(name, 1));
            }
        }
        session.setAttribute("items", items);
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("Part5/AddSuccessful.jsp").forward(request, response);
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
