package com.training.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.training.entity.SignUp;
import com.training.ifaces.Dao;
import com.training.utils.DbConnection;

public class SignUpDao implements Dao<SignUp> {
	private Connection con;
	
	public SignUpDao() {
		super();
		this.con = DbConnection.getOracleConnection();
	}

	@Override
	public int add(SignUp t) throws SQLException {
		String sql = "insert into suhas_customer values(?,?,?,?,?)";
		PreparedStatement pstmt= createStatement(sql);
		
		pstmt.setString(1, t.getFirstName());
		pstmt.setString(2, t.getLastName());
		pstmt.setString(3, t.getUserName());
		pstmt.setString(4, t.getPassword());
		pstmt.setString(5, t.getType());
		
		return pstmt.executeUpdate();
	}

public PreparedStatement createStatement(String sql) throws SQLException {
		
		
		return  con.prepareCall(sql);
		 
	}

@Override
public List<SignUp> findAll() throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int update(SignUp t) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int remove(long id) throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public SignUp findByUser(String user) throws SQLException {
	String sql = "select * from  suhas_customer where username =?";
	PreparedStatement pstmt= createStatement(sql);
	SignUp logger =null;
	pstmt.setString(1,user);
	
	ResultSet rs = pstmt.executeQuery();
	
	  if(rs.next()) {
		  logger = mapToRow(rs);
		 
	  }
	  
	  return logger;
}
public SignUp mapToRow(ResultSet rs)  throws SQLException{
	
	
	String firstName = rs.getString("firstname");
	String lastName = rs.getString("lastname");
	String userName=rs.getString("username");
	String password = rs.getString("password");
	String typeOfUser = rs.getString("type");

	return new SignUp(firstName,lastName,userName,password,typeOfUser);
}
}
