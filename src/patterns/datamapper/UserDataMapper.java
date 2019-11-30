package patterns.datamapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.DB;
import model.User;

public class UserDataMapper {
	
	
	public static User login(String id, String pwd) {
		String sql = String.format("SELECT * FROM %s WHERE id=? and pwd=?", DB.USER_TABLE);
		User user = null;
		try (Connection connection = DriverManager.getConnection(DB.URL);
		        PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Query executed");
			
			while(rs.next()) {
				user = new User(id, pwd, rs.getString("name"), rs.getString("address"), rs.getString("bestFriend"));
				break;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
		
		return user;
	}
	
	public static int register(User user) {
		int rows = 0;
		return rows;
	}
	
	public static void main(String[] args) {
		String id = "1234";
		String pwd = "AAA";
		
		DB.getInstance();
		
		login(id, pwd);
	}
}
