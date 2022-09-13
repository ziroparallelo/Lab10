package it.polito.tdp.rivers.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Stampa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}

	public Stampa getStampa(River r) {
		
		String sql ="select river, Min(day) as min, Max(day) as max, (select count(*) from flow "
				+ "where river = ?) as n, SUM(flow) /(select count(*) from flow "
				+ "where river = ?) as media "
				+ "from flow "
				+ "where river = ?";
		
		Stampa s;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			st.setInt(2, r.getId());
			st.setInt(3, r.getId());
			ResultSet res = st.executeQuery();

			res.first();
			
			s = new Stampa(r, res.getDate("min").toLocalDate(),
					res.getDate("max").toLocalDate(), res.getInt("n"), 
					res.getFloat("media"));

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return s;
	}
	
	public List<Flow> getAllFlowByRiver(River r) {
		
		String sql = "select day, flow "
				+ "from flow "
				+ "where river = ?";
		
		List<Flow> flow = new ArrayList<Flow>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();

			res.first();
			
			flow.add(new Flow(res.getDate("day").toLocalDate(),
					res.getFloat("flow") , r));

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return flow;
	}

}
