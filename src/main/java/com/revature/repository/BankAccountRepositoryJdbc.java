package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.revature.model.BankAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountRepositoryJdbc implements BankAccountRepository {

	private static final Logger LOGGER = Logger.getLogger(BankAccountRepositoryJdbc.class);

	@Override
	public int createBankAccount(BankAccount account) {
		int accountsCreated = 0;

		
		String sql = "INSERT INTO BANK_ACCOUNT(BA_USER_NAME, BA_BALANCE) VALUES (?, ?)";

		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, account.getUsername());
			ps.setDouble(2, account.getBalance());
			accountsCreated = ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return accountsCreated;

	}

	@Override
	public double getBalance(String username) {
		double balance = 0;
		ResultSet rs = null;

		String sql = "SELECT BA_BALANCE FROM BANK_ACCOUNT WHERE BA_USER_NAME = ?";
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getDouble("BA_BALANCE");
			}
		} catch (SQLException e) {
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

		return balance;
	}

		
	@Override
	public void updateAccount(BankAccount account) {
		int accountsUpdated = 0;
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		String sql = "INSERT INTO BANK_ACCOUNT(BA_USER_NAME, BA_BALANCE, BA_DATE) VALUES (?, ?,?) ON CONFLICT (BA_USER_NAME)" 
		 + "DO UPDATE SET BA_BALANCE = ?";
		
		try (Connection con = ConnectionUtil.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, account.getUsername());
			ps.setDouble(2, account.getBalance() );
			ps.setObject(3, localDateTime);
			ps.setDouble(4, account.getBalance());
			
			accountsUpdated = ps.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		
		}
	
	}

}