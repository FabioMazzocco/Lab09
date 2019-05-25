package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {
		final String sql = "SELECT DISTINCT state1no, state2no, year " + 
				"FROM contiguity " + 
				"WHERE conttype=1 AND year <= ? " + 
				"ORDER BY year ASC ";
		final String sql2 = "SELECT DISTINCT state1no, state2no, year " + 
				"FROM contiguity2006 " + 
				"WHERE conttype=1 AND year <= ? " + 
				"ORDER BY year ASC ";
		
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Border(rs.getInt("state1no"), rs.getInt("state2no"), rs.getInt("year")));
			}
			
			st = conn.prepareStatement(sql2);
			st.setInt(1, anno);
			rs = st.executeQuery();
			
			while (rs.next()) {
				result.add(new Border(rs.getInt("state1no"), rs.getInt("state2no"), rs.getInt("year")));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
