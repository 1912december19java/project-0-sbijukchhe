package com.revature.repository;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.model.BankAccount;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserRepositoryJdbc  implements UserRepository {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryJdbc.class);

	@Override
	public boolean createUserAccount(User user) {
				
		LOGGER.trace ("Entering create method with parameter:" + user);
		try(Connection connection = ConnectionUtil.getConnection()){
				
			String sql = "INSERT INTO USER_DETAILS VALUES (?, ?, ?, ?)";	
						
			PreparedStatement ps = connection.prepareStatement(sql);
						
						ps.setString (1, user.getUsername());
						ps.setString (2, user.getPassword());
						ps.setString (3, user.getFname());
						ps.setString (4, user.getLname());
						
						if (ps.executeUpdate() >0){
							return true;
						}
		}
		
		
	 catch (SQLException e) {
		LOGGER.error ("Could not create user", e);
		}
		return false;
	}


	@Override
	public int updateAccount(User user) {
		int usersUpdated = 0;

		String sql = "UPDATE USER_DETAILS" + " SET U_FIRST_NAME = ?," + " U_LAST_NAME = ?," + " WHERE U_USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, user.getFname());
			ps.setString(2, user.getLname());
			ps.setString(3, user.getUsername());
			usersUpdated = ps.executeUpdate();

		} catch (SQLException  e) {
			LOGGER.error(e.getMessage());
		}
		return usersUpdated;
	}

	@Override
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>();
		String sql = "SELECT * FROM USER_DETAILS";

		try (Connection con = ConnectionUtil.getConnection();
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery(sql)) {

			while (rs.next()) {
				User u = new User();
				String userId = rs.getString("U_USER_NAME");
				u.setUsername(userId);

				String password = rs.getString("U_PASSWORD");
				u.setPassword(password);

				String fname = rs.getString("U_FIRST_NAME");
				u.setFname(fname);

				String lname = rs.getString("U_LAST_NAME");
				u.setLname(lname);

				userList.add(u);
			}
		} catch (SQLException  e) {
			LOGGER.error(e.getMessage());
		}

		return userList;
	}

	public boolean isMatchUsername(String inputUser) {
		boolean match = false;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER_DETAILS WHERE U_USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, inputUser);

			rs = ps.executeQuery();

			if (rs.next()) {
				match = true;

			} else {
				match = false;
			}

		} catch (SQLException  e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return match;
	}

	public boolean isMatchPassword(String inputPassword, String inputUser) {
		boolean match = false;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER_DETAILS WHERE U_PASSWORD = ?" 
				+ " AND U_USER_NAME = ?";

		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, inputPassword);
			ps.setString(2, inputUser);

			rs = ps.executeQuery();

			while (rs.next()) {
				if ((inputPassword.equals(rs.getString("U_PASSWORD")) && inputUser.equals(rs.getString("U_USER_NAME")))) {
					match = true;
				} else {
					match = false;
				}
			}
		} catch (SQLException  e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				if(rs != null) {
				rs.close();
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return match;
	}
}
