package com.menu.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.menu.model.HibernateCRUD;
import com.menu.model.Product;

/**
 * Servlet implementation class UpdateProduct
 */
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HibernateCRUD hbr = new HibernateCRUD();
		hbr.initEntityManager();
		hbr.updateProduct(new Product(request.getParameter("productTitle"),
				Double.parseDouble(request.getParameter("productPrice")), 
				request.getParameter("productDimension"), request.getParameter("productAvailibility")), request.getParameter("productName"));
		response.getWriter().write("Product has been updated");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
