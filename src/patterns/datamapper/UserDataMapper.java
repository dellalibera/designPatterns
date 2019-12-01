package patterns.datamapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DB;
import model.User;

public class UserDataMapper {
	
	
	public static User login(String id, String pwd) {
		String sql = String.format("SELECT * FROM %s WHERE id=? and pwd=?", DB.USER_TABLE);
		User user = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(DB.URL);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				user = new User(id, pwd, rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
				System.out.println(user.toString());
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return user;
	}

	public static int register(User user) {
		String sql_insert_user = String.format("INSERT INTO %s(id, pwd, name, address, bestFriend) VALUES(?, ?, ?, ?, ?);", DB.USER_TABLE);
		String sql_insert_addr = String.format("INSERT INTO %s(address) VALUES(?);", DB.ADDRESS_TABLE);
		
		int rows = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(DB.URL);
			
			// Check if the user already exists
			if(!userExists(user.getId())) {
	        	pstmt = connection.prepareStatement(sql_insert_user);
	        	pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPwd());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getAddress());
				pstmt.setString(5, user.getBestFriend());
				
				// CHECK the resulting queiry
				rows = pstmt.executeUpdate();

				if(rows > 0) {
        			System.out.println("User registered");
        			
        			if(!addressExists(user.getAddress())) {
        				pstmt = connection.prepareStatement(sql_insert_addr);
	                	pstmt.setString(1, user.getAddress());
	        			rows = pstmt.executeUpdate();
	        			System.out.println("Address added");
        			} else {
        				System.out.println("Address already exists");
        			}
    			}
			} else {
				System.out.println("User already exists");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return rows;
	}
	
    // https://stackoverflow.com/questions/29640246/how-to-check-if-a-row-exist-in-the-sqlite-table-with-a-condition
	public static boolean userExists(String id) {
		String sql = String.format("SELECT (count(*) > 0) as found FROM %s WHERE id=? ;", DB.USER_TABLE);
		
		boolean user_found = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(DB.URL);
			PreparedStatement pstmt = connection.prepareStatement(sql);
		    pstmt.setString(1, id);
		    
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
                user_found = rs.getBoolean(1);
		    }
		    connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return user_found;		
	}
	
	public static boolean addressExists(String address) {
		String sql = String.format("SELECT (count(*) > 0) as found FROM %s WHERE address=? ;", DB.ADDRESS_TABLE);
		
		boolean address_found = false;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection(DB.URL);
			PreparedStatement pstmt = connection.prepareStatement(sql);
		    pstmt.setString(1, address);
		    
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
		    	address_found = rs.getBoolean(1);
		    }
		    connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		return address_found;		
	}
	
	public static void getAllUsers(){
		String sql = String.format("SELECT * FROM %s", DB.USER_TABLE);
		
		try {
			Class.forName("org.sqlite.JDBC");
		    Connection connection = DriverManager.getConnection(DB.URL);
		    PreparedStatement pstmt = connection.prepareStatement(sql);
		    
			ResultSet rs = pstmt.executeQuery();
			System.out.println("List of Users:");
			while(rs.next()) {
				User user = new User(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
				System.out.println(user.toString());
			}
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	public static void getAllAddress(){
		String sql = String.format("SELECT * FROM %s", DB.ADDRESS_TABLE);
		
		try {
			
			Class.forName("org.sqlite.JDBC");
		    Connection connection = DriverManager.getConnection(DB.URL);
		    PreparedStatement pstmt = connection.prepareStatement(sql);
		    
			ResultSet rs = pstmt.executeQuery();
			System.out.println("List of Addresses:");
			while(rs.next()) {
				System.out.println(rs.getString("address"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	
//	public static void main(String[] args) {
//		User user1 = new User("8", "aaa", "nome", "Addr, dasd, asda1", "aaa");
////		User user2 = new User("5", "aaa", "nome", "Addr2", "aaa");
////		User user3 = new User("4", "aaa", "nome", "Addr2", "aaa");
////
//////		String id = "123";
//////		String pwd = "aaa";
//////		
////		DB.getInstance();
//////		
////		register(user1);
////		register(user2);
////		register(user3);
////
//////		login(id, pwd);
//////		getAll();
//		getAllAddress();
//		getAllUsers();
//	}
}
