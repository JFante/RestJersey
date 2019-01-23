package com.mx.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static Connection conectar() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/ganjadb", "ganjaman", "Reneg@des99");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
