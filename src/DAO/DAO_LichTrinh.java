package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class DAO_LichTrinh {
	public String[] timKiemLichTrinh(String gaDi, String gaDen, Date ngayDi) throws SQLException {
	    connectDB.ConnectDB.getInstance();
	    Connection con = connectDB.ConnectDB.getConnection();
	    String sql = "SELECT * FROM LichTrinh WHERE gaDi = ? AND gaDen = ? AND ngayDi = ?";
	    
	    PreparedStatement preparedStatement = con.prepareStatement(sql);
	    preparedStatement.setString(1, gaDi);
	    preparedStatement.setString(2, gaDen);
	    preparedStatement.setDate(3, ngayDi);
	    
	    ResultSet rs = preparedStatement.executeQuery();

	    List<String> veTauList = new ArrayList<>();
	    while (rs.next()) {
	        String maVeTau = rs.getString("maTau");
	        veTauList.add(maVeTau);
	    }
	    
	    return veTauList.toArray(new String[0]);
	}
		public String layGiaLichTrinh(String maTau) throws SQLException {
		    connectDB.ConnectDB.getInstance();
		    Connection con = connectDB.ConnectDB.getConnection();
		    
		    String sql = "SELECT giaChuyen FROM LichTrinh WHERE maTau = ?";
		    
		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, maTau);
		        
		        try (ResultSet rs = preparedStatement.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("giaChuyen");
		            } else {
		                return null; 
		            }
		        }
		    } finally {
		        if (con != null) {
		            con.close();
		        }
		    }
		}
		public String laymaLichTrinh(String maTau, String gaDi, String gaDen, java.util.Date ngayDi) throws SQLException {
		    connectDB.ConnectDB.getInstance();
		    Connection con = connectDB.ConnectDB.getConnection();

		    // Convert java.util.Date to java.sql.Date
		    java.sql.Date sqlDate = new java.sql.Date(ngayDi.getTime());

		    String sql = "SELECT maLichTrinh FROM LichTrinh WHERE maTau = ? AND gaDi = ? AND gaDen = ? AND ngayDi = ?";

		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, maTau);
		        preparedStatement.setString(2, gaDi);
		        preparedStatement.setString(3, gaDen);
		        preparedStatement.setDate(4, sqlDate);



		        try (ResultSet rs = preparedStatement.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("maLichTrinh");
		            } else {
		                return null; 
		            }
		        }
		    } finally {
		        if (con != null) {
		            con.close();
		        }
		    }
		}
		public String layTGDen(String maTau, String gaDi, String gaDen) throws SQLException {
		    connectDB.ConnectDB.getInstance();
		    Connection con = connectDB.ConnectDB.getConnection();
		    
		    String sql = "SELECT ngayDen FROM LichTrinh WHERE maTau = ? AND gaDi = ? AND gaDen = ?";
		    
		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, maTau);
		        preparedStatement.setString(2, gaDi);
		        preparedStatement.setString(3, gaDen);
		        
		        try (ResultSet rs = preparedStatement.executeQuery()) {
		            if (rs.next()) {
		                return rs.getString("ngayDen");
		            } else {
		                return null; 
		            }
		        }
		    } finally {
		        if (con != null) {
		            con.close();
		        }
		    }
		}
		public void taoLichTrinhMoi(String maLichTrinh, String maTau, Date ngayDi, Date ngayDen, String gaDi, String gaDen, float giaChuyen) throws SQLException {
		    connectDB.ConnectDB.getInstance();
		    Connection con = connectDB.ConnectDB.getConnection();
		    
		    String sql = "INSERT INTO LichTrinh (maLichTrinh, maTau, ngayDi, ngayDen, gaDi, gaDen, giaChuyen) VALUES (?, ?, ?, ?, ?, ?, ?)";

		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, maLichTrinh);
		        preparedStatement.setString(2, maTau);
		        preparedStatement.setDate(3, ngayDi);
		        preparedStatement.setDate(4, ngayDen);
		        preparedStatement.setString(5, gaDi);
		        preparedStatement.setString(6, gaDen);
		        preparedStatement.setFloat(7, giaChuyen);

		        int rowsAffected = preparedStatement.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("New itinerary created successfully.");
		        } else {
		            System.out.println("Failed to create new itinerary.");
		        }
		    } catch (SQLException e) {
		        // Handle SQL exception
		        System.err.println("SQL error: " + e.getMessage());
		        throw e;
		    } finally {
		        if (con != null) {
		            con.close();
		        }
		    }
		}

		public DefaultTableModel getAllLichTrinh() throws SQLException {
			String[] header = {"Mã lịch trình","Mã tàu","Ngày đi","Ngày đến","Ga đi","Ga đến","Giá chuyến"};
			DefaultTableModel tableModel = new DefaultTableModel(header, 0);
			connectDB.ConnectDB.getInstance();
			Connection con = connectDB.ConnectDB.getConnection();
			String sql = "Select * FROM LICHTRINH";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7)};
				tableModel.addRow(o);
			}
			return tableModel;
		}



}
