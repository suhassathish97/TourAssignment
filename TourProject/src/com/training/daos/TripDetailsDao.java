package com.training.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.training.entity.TourDetails;
import com.training.ifaces.Dao;
import com.training.utils.DbConnection;

public class TripDetailsDao implements Dao<TourDetails> {
private Connection con;
	
	public TripDetailsDao() {
		super();
		this.con = DbConnection.getOracleConnection();
	}

	@Override
	public int add(TourDetails t) throws SQLException {
		String sql = "insert into suhas_tour values(?,?,?,?,?,?)";
		PreparedStatement pstmt= con.prepareStatement(sql);
		pstmt.setLong(1, t.getCode());
		pstmt.setString(2, t.getTourName());
		Date startDate=Date.valueOf(t.getStartDate());
		pstmt.setDate(3,startDate);
		Date endDate=Date.valueOf(t.getEndDate());
		pstmt.setDate(4,endDate);
		pstmt.setString(5,t.getPlaces());
		pstmt.setLong(6, t.getNumberOfDays());	      

		
		return pstmt.executeUpdate();
	}

	@Override
	public List<TourDetails> findAll() throws SQLException {
		String sql = "select * from suhas_tour";
		PreparedStatement pstmt=  con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();

		List<TourDetails> tourDetailsList = new ArrayList<>();
		TourDetails tour = null;
//		while(rs.next()) {
//			System.out.println("Hai1");
//			 
//			tour = mapToRow(rs);
//			System.out.println("hi");
//			System.out.println(tour);
//			System.out.println("hi");
//			tourDetailsList.add(tour);
//		 }
//		System.out.println(rs.next());
		while(rs.next()) {
			System.out.println("In while loop");
			long code=rs.getLong("code");
			System.out.println("code");
			String tourName = rs.getString("tourname");
			System.out.println("name");
			Date sdate=rs.getDate("startdate");
			LocalDate startDate=sdate.toLocalDate();
			System.out.println("Date1");
			Date edate=rs.getDate("enddae");
			LocalDate endDate=edate.toLocalDate();
			System.out.println("Date2");
			String places = rs.getString("places");
			long numberOfDays=rs.getLong("numberofdays");
			System.out.println("days");
			System.out.println("end");
			tour =new TourDetails(code,tourName,startDate,endDate,places,numberOfDays);
			tourDetailsList.add(tour);
			System.out.println(tour);
		}
		
		//tour = mapToRow(rs);
		System.out.println(tourDetailsList.size());
		//System.out.println(tourDetailsList);
		return tourDetailsList;
	}

	@Override
	public TourDetails findByUser(String user) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(TourDetails t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(long id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public TourDetails findById(long id) throws SQLException {
		String sql = "select * from  suhas_tour where code=?";
		PreparedStatement pstmt=  con.prepareStatement(sql);
		TourDetails tour =null;
		pstmt.setLong(1,id);
		
		ResultSet rs = pstmt.executeQuery();
		
		  if(rs.next()) {
			  tour = mapToRow(rs);
			 
		  }
		  
		  return tour;
	}
public TourDetails mapToRow(ResultSet rs)  throws SQLException{
		
		long code=rs.getLong("code");
		String tourName = rs.getString("tourname");
		Date sdate=rs.getDate("startdate");
		LocalDate startDate=sdate.toLocalDate();
		Date edate=rs.getDate("enddate");
		LocalDate endDate=edate.toLocalDate();
		String places = rs.getString("places");
		long numberOfDays=rs.getLong("numberofdays");
		return new TourDetails(code,tourName,startDate,endDate,places,numberOfDays);
	}

}
