package patterns.datamapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DB;
import model.User;

public class UserDataMapper {
	
	
	public static User login(String id, String pwd) {
		String sql = String.format("SELECT * FROM %s WHERE id=? and pwd=?", DB.USER_TABLE);
		User user = null;
		
		try {
			PreparedStatement pstmt = DB.connection.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				user = new User(id, pwd, rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
				System.out.println(user.toString());
				break;
			}
		} catch (SQLException e) {
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
			if(!userExists(user.getId())) {
				
				System.out.println("User registered");
    			
    			if(!addressExists(user.getAddress())) {
    				pstmt = DB.connection.prepareStatement(sql_insert_addr);
                	pstmt.setString(1, user.getAddress());
        			rows = pstmt.executeUpdate();
        			System.out.println("Address added");
    			} else {
    				System.out.println("Address already exists");
    			}
    			
	        	pstmt = DB.connection.prepareStatement(sql_insert_user);
	        	pstmt.setString(1, user.getId());
				pstmt.setString(2, user.getPwd());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getAddress());
				pstmt.setString(5, user.getBestFriend());
				
				rows = pstmt.executeUpdate();

				if(rows == 0) {
					System.out.println("Rollback");
        			DB.connection.rollback();
    			}
			} else {
				System.out.println("User already exists");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rows;
	}
	
	public static int delete(String id) {
		String sql = String.format("DELETE FROM %s WHERE id=?", DB.USER_TABLE);
		int rows = 0;
		PreparedStatement pstmt = null;
		try {			
			if(userExists(id)) {
	        	pstmt = DB.connection.prepareStatement(sql);
	        	pstmt.setString(1, id);
	        	rows = pstmt.executeUpdate();
			} else {
				System.out.println("User not exists");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rows;
	}
	
	public static int update(User user) {
		String sql = String.format(
				"UPDATE %s SET "
				+ "pwd=?, name=?, address=?, bestFriend=? "
				+ "WHERE id=?", DB.USER_TABLE);
		
		int rows = 0;
		PreparedStatement pstmt = null;
		try {			
			getAllUsers();
			if(userExists(user.getId())) {
	        	pstmt = DB.connection.prepareStatement(sql);
				pstmt.setString(1, user.getPwd());
				pstmt.setString(2, user.getName());
				pstmt.setString(3, user.getAddress());
				pstmt.setString(4, user.getBestFriend());
	        	pstmt.setString(5, user.getId());
	        	rows = pstmt.executeUpdate();
			} else {
				System.out.println("User not exists");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rows;
	}
	
	public static ArrayList<User> search(String name, String address, String bestFriend) {
		ArrayList<User> users = new ArrayList<User>();
		String sql = String.format("SELECT * FROM %s WHERE ", DB.USER_TABLE);
		String param = "";
		
		if(name != null && name != "" && address != null && address != "" && bestFriend != null && bestFriend != "") {
			System.out.println("---> 1111");
			try {
				if(name != null && name != "") {
					System.out.println("---> 2222");

					sql += "name = ?";
					param = name;
				} else if (address != null && address != "") {
					System.out.println("---> 33333");

					sql += "address = ?";
					param = address;
				} else if (bestFriend != null && bestFriend != "") {
					System.out.println("---> 44444");

					sql += "bestFriend = ?";
					param = bestFriend;
				}
				PreparedStatement pstmt = DB.connection.prepareStatement(sql);
				pstmt.setString(1, param);
			    
				ResultSet rs = pstmt.executeQuery();
			    if (rs.next()) {
					System.out.println("---> 123123");

					User user = new User(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
					users.add(user);
			    }
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return users;		
	}
	
    // https://stackoverflow.com/questions/29640246/how-to-check-if-a-row-exist-in-the-sqlite-table-with-a-condition
	public static boolean userExists(String id) {
		String sql = String.format("SELECT (count(*) > 0) as found FROM %s WHERE id=? ;", DB.USER_TABLE);
		
		boolean user_found = false;
		try {
			PreparedStatement pstmt = DB.connection.prepareStatement(sql);
		    pstmt.setString(1, id);
		    
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
                user_found = rs.getBoolean(1);
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user_found;		
	}
	
	public static boolean addressExists(String address) {
		String sql = String.format("SELECT (count(*) > 0) as found FROM %s WHERE address=? ;", DB.ADDRESS_TABLE);
		
		boolean address_found = false;
		try {
			PreparedStatement pstmt = DB.connection.prepareStatement(sql);
		    pstmt.setString(1, address);
		    
		    ResultSet rs = pstmt.executeQuery();
		    if (rs.next()) {
		    	address_found = rs.getBoolean(1);
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return address_found;		
	}
	
	public static void getAllUsers(){
		String sql = String.format("SELECT * FROM %s", DB.USER_TABLE);
		
		try {
		    PreparedStatement pstmt = DB.connection.prepareStatement(sql);
		    
			ResultSet rs = pstmt.executeQuery();
			System.out.println("List of Users:");
			while(rs.next()) {
				User user = new User(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	public static void getAllAddress(){
		String sql = String.format("SELECT * FROM %s", DB.ADDRESS_TABLE);
		
		try {
		    PreparedStatement pstmt = DB.connection.prepareStatement(sql);
		    
			ResultSet rs = pstmt.executeQuery();
			System.out.println("List of Addresses:");
			while(rs.next()) {
				System.out.println(rs.getString("address"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
	}
	
	
	public static void main(String[] args) {
		User user1 = new User("8", "aaa", "nome", "Addr, dasd, asda1", "1");
		User user2 = new User("5", "aaa", "nome", "Addr2", "1");
		User user3 = new User("4", "aaa", "nome", "Addr2", "1");
//
////		String id = "123";
////		String pwd = "aaa";
////		
		DB.getInstance();
////		
		register(user1);
//		register(user2);
//		register(user3);
//
////		login(id, pwd);
////		getAll();
		getAllAddress();
		getAllUsers();
	}
}
