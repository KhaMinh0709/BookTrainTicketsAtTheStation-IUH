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

import connectDB.ConnectDB;

public class DAO_VeTau {

	// đặt vé xong luưn datadabse
	public boolean TaoVeChoKhach(String maVe, String maLichTrinh, String maGhe, String maToa,String maTau) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into VETAU values(?,?,?,?,?) ";
		int k = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,maVe);
			preparedStatement.setString(2,maLichTrinh);
			preparedStatement.setString(3,maGhe);
			preparedStatement.setString(4,maToa);
			preparedStatement.setString(5,maTau);

			k = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k > 0;
		
	}
	//tạo mã vé tự động
	public String generateNewmaVE() throws SQLException{
		 ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT COUNT(*) AS total FROM VETAU";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        int count = 0;
	        if (rs.next()) {
	            count = rs.getInt("total");
	        }
	        rs.close();
	        ps.close();

	        // Tạo mã mới theo mẫu V001, V002, ...
	        String newMaVe = String.format("V%03d", count + 1);
	        return newMaVe;
	}

}
