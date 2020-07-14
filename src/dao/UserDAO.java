package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;
import java.sql.Connection;

public class UserDAO implements UserDaoInterface {

	@Override
	public int signUp(User user) throws ClassNotFoundException, SQLException, IOException {
		
		 String email = user.getEmail();
		 String password = user.getPassword();
		 int result = 0;
		 String sql = "insert int USERDB(EMAIL,PASSWORD)VALUES(?,?)";
		
		 PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
		 
		 st.setString(1, email);
		 st.setString(2, password);
		 System.out.println(st);
		 result = st.executeUpdate();
		
			return result; 
	}
	
	@Override
	public boolean loginUser(User user) throws ClassNotFoundException, IOException, SQLException  {
		boolean status = false;
		
			Connection connection = ConnectionManager.getConnection();

		PreparedStatement st = connection.prepareStatement("select * from users where email = ? and password = ? ");
		
			st.setString(1, user.getEmail());
			st.setString(2, user.getPassword());

			System.out.println(st);
			ResultSet rs = st.executeQuery();
			status = rs.next();

		return status;
	}
}
