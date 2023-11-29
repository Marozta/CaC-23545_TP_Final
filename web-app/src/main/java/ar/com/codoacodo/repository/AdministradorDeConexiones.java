package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
	public static Connection getConnection( ) {
		String host = "localhost";
		String username ="root";
		String password ="admin";
		String port = "3306";
		String dbName = "db-23545";
		String dbUrl = "jdbc:mysql://"+host+":"+port+"/"+dbName+"?serverTimeZone=UTC&useSSL=false";
		
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(dbUrl, username, password);
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo obtener conexion a: " + dbUrl + " - " + driver);
		}
		
	}

}

