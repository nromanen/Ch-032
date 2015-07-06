package com.javagroup.restaurantmenu;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

public class H2DBServer {
	private Connection connection;
	private Server server;
	
	public H2DBServer(){
       try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

       try {
			connection = DriverManager.getConnection("jdbc:h2:mem:test");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    try {
			server = Server.createTcpServer().start();
			System.out.println("Server started and connection is open.");
			System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");
	    } catch (SQLException e) {
			e.printStackTrace();
		}

//	    try {
//			RunScript.execute(connection, new InputStreamReader(getClass()
//					.getResourceAsStream("/sql/create_all_tables.sql")));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void closeServer() {
		server.stop();
	    try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void populateDatabaseWithSampleData(){
		try {
			RunScript.execute(connection, new InputStreamReader(getClass()
					.getResourceAsStream("/sql/populate_tables.sql")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
