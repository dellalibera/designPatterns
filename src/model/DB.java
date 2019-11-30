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
		try (Connection connection = DriverManager.getConnection(URL)) {
			if (connection != null) {
				DatabaseMetaData meta = connection.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database has been created.");
			}
		} catch (SQLException e) {
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
                + " bestFriend text, \n"
                + " FOREIGN KEY (address) REFERENCES %s (address), \n"
                + " FOREIGN KEY (id) REFERENCES %s (id)"
                + " );", USER_TABLE, ADDRESS_TABLE, USER_TABLE);
        
		sql += String.format("CREATE TABLE IF NOT EXISTS %s (name text PRIMARY KEY);", ADDRESS_TABLE);
		
		try (Connection connection = DriverManager.getConnection(URL);
		        Statement stmt = connection.createStatement()) {
			stmt.execute(sql);
			System.out.println(String.format("Tables %s and %s has been created", USER_TABLE, ADDRESS_TABLE));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public static DB getInstance(){
	      return DATABASE;
	}
}
