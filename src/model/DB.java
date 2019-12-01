package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// Useful links
// https://www.sqlitetutorial.net/sqlite-java/create-database/
// https://www.sqlitetutorial.net/sqlite-java/create-table/
// https://www.sqlitetutorial.net/sqlite-foreign-key/
// https://www.sqlitetutorial.net/sqlite-java/insert/

public class DB {
	
	private static final DB DATABASE = new DB();
	public static final String URL = "jdbc:sqlite:database.db";
	public static final String USER_TABLE = "USERS";
	public static final String ADDRESS_TABLE = "ADDRESS";

	private DB() {
		createDatabase();
		createTables();
	}
	
	private static void createDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(URL);
					
			if (connection != null) {
				DatabaseMetaData meta = connection.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException | ClassNotFoundException e) {
		    System.out.println(e.getMessage());
		}
	}
	
	private  static void createTables() {
		String sql = String.format(
				"CREATE TABLE IF NOT EXISTS %s (\n"
				+ "	id text PRIMARY KEY,\n"
				+ "	pwd text NOT NULL,\n"
				+ " name text NOT NULL,\n"
				+ " address text NOT NULL,\n"
				+ " bestFriend text NOT NULL, \n"
				+ " FOREIGN KEY (address) REFERENCES %s (address) ON UPDATE SET NULL \n ON DELETE SET NULL, \n"
				+ " FOREIGN KEY (id) REFERENCES %s (id) ON UPDATE SET NULL \n ON DELETE SET NULL"
				+ " );", USER_TABLE, ADDRESS_TABLE, USER_TABLE);
        
		String sql2 = String.format("CREATE TABLE IF NOT EXISTS %s (address text PRIMARY KEY);", ADDRESS_TABLE);
		
		try (Connection connection = DriverManager.getConnection(URL)) {
			Statement stmt = connection.createStatement();
			stmt.execute("PRAGMA foreign_keys = ON");
			stmt.execute(sql);
			stmt.execute(sql2);
			
			System.out.println(String.format("Tables %s and %s has been created", USER_TABLE, ADDRESS_TABLE));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public static DB getInstance(){
	      return DATABASE;
	}
	
//	public static void main(String[] args) {
//		getInstance();
//	}
}
