package com.javagroup.restaurantmenu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;

import com.javagroup.restaurantmenu.dao.ProductDAO;
import com.javagroup.restaurantmenu.dao.hibernate.HibernateUtil;
import com.javagroup.restaurantmenu.dao.hibernate.ProductDAOHibernate;
import com.javagroup.restaurantmenu.jspbeans.DefaultProduct;
import com.javagroup.restaurantmenu.model.Product;
import com.javagroup.restaurantmenu.util.DataBaseCreator;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
 
public class ProductServlet extends HttpServlet {
   
	private static final Logger logger = LogManager.getLogger(ProductServlet.class);
	private H2DBServer h2dbServer;
	
	public void init() {
		Properties props = System.getProperties();
		props.setProperty("org.jboss.logging.provider", "slf4j");
		h2dbServer = new H2DBServer();
		Connection connection = h2dbServer.getConnection();
		new DataBaseCreator().CreateDatabaseTables(connection);
		populateProducts();
	}
	
	public void destroy(){
		HibernateUtil.shutdown();
		h2dbServer.closeServer();
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	process(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        process(request, response);
    }
 
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setStatus(200);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String action = request.getParameter("action");
		
		if (action == null) {
			printProducts(out);
		} else if (action.equals("delete")) {
			deleteProduct(Long.parseLong(request.getParameter("id")));
			printProducts(out);	
		} else if (action.equals("tryadd")){
			DefaultProduct defaultProduct = new DefaultProduct("type name", 1.0);
			request.setAttribute("defaultProduct", defaultProduct);
			RequestDispatcher view = request.getRequestDispatcher("add_product.jsp");
			try {
				view.forward(request, response);
			} catch (ServletException e) {
				logger.error("can't forward to ADD PRODUCT page", e);
			}
		} else if (action.equals("add")){
			boolean available = (request.getParameter("product_available") == null) ? false : true;
			addProduct(request.getParameter("product_name"),
					Double.parseDouble(request.getParameter("product_price")), available);
			printProducts(out);
		}
				
	}
    
    private void addProduct(String name, double price, boolean available){
    	Product product = new Product(name, price, available);
    	Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		ProductDAO productDAO = new ProductDAOHibernate();
		productDAO.makePersistent(product);
		tx.commit();
    }
    
    private void deleteProduct(long id){
    	Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		ProductDAO productDAO = new ProductDAOHibernate();
		Product product = productDAO.findById(id); 
		productDAO.makeTransient(product);
		tx.commit();
    }
    
    private void printProducts(PrintWriter out){
    	Transaction tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		ProductDAO productDAO = new ProductDAOHibernate(); 
		List<Product> products = productDAO.findAll();
		tx.commit();
		
		printHeader(out);
		out.println("<h3>All our products for today:</h3>");
		out.println("<TABLE border=1 cellspacing=0 cellpadding=2>");
		out.println("<TR>" 
					+ "<TD><b>name</b></TD>"
					+ "<TD><b>price</b></TD>"
					+ "<TD><b>availability</b></TD>"
					+ "<TD>&nbsp</TD>"
					+"</TR>");
		for (Product product : products) {
			out.println("<TR>");
			out.println("<TD>" + product.getName() + "</TD>");
			out.println("<TD>" + product.getPrice() + "</TD>");
			out.println("<TD>" + product.getAvailable() + "</TD>");
			out.println("<TD><A HREF=menu?action=delete&id=" + product.getId() + ">delete</A></TD>");
			out.println("</TR>");
		}
		out.println("</TABLE>");
		out.println("<A HREF=menu?action=tryadd>add new product...</A>");
		printFooter(out);
		
    }
    
    private void printHeader(PrintWriter out) {
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<META http-equiv=Cache-Control content=private>");
		out.println("<TITLE>Products</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<CENTER>");
	}
    
	private void printFooter(PrintWriter out) {
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
	}
    
    private void populateProducts(){
    	ProductDAO productDAO = new ProductDAOHibernate();
		Transaction tx = HibernateUtil.getSessionFactory()
				.getCurrentSession().beginTransaction();
		Product product1 = new Product("honey", 5, true);
		Product product2 = new Product("vodka", 15, true);
		Product product3 = new Product("milk", 10, true);
		productDAO.makePersistent(product1);
		productDAO.makePersistent(product2);
		productDAO.makePersistent(product3);
		tx.commit();
    }
    
}
