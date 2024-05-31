package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import connectDB.ConnectDB;

public class DAO_Ghe {
	
	  public Map<String, Boolean> getTinhTrangGhe(String maTau, String maToa, String maLichTrinh) throws SQLException {
	        ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT maGhe, tinhTrang FROM Ghe WHERE maTau = ? AND maToa = ? AND maLichTrinh = ?";
	        Map<String, Boolean> gheTinhTrang = new HashMap<>();

	        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	            preparedStatement.setString(1, maTau);
	            preparedStatement.setString(2, maToa);
	            preparedStatement.setString(3, maLichTrinh);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String maGhe = rs.getString("maGhe");
	                boolean tinhTrang = rs.getInt("tinhTrang") == 1;
	                gheTinhTrang.put(maGhe, tinhTrang);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw e;
	        }

	        return gheTinhTrang;
	    }
	  public void chinhsuaTrangThaiDatGhe(String maGhe, String maToa, String maTau, String maLichTrinh) throws SQLException {
		    ConnectDB.getInstance();
		    Connection con = ConnectDB.getConnection();

		    String sql = "INSERT INTO Ghe (maGhe, maToa, maTau, maLichTrinh, loaiGhe, tinhTrang) VALUES (?, ?, ?, ?, ?, ?)";

		    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
		        preparedStatement.setString(1, maGhe);
		        preparedStatement.setString(2, maToa);
		        preparedStatement.setString(3, maTau);
		        preparedStatement.setString(4, maLichTrinh);
		        preparedStatement.setString(5, "");
		        preparedStatement.setInt(6, 1); // 1 là trạng thái đã đặt

		        preparedStatement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		}




}
