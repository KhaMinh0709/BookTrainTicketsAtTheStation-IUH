package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;

public class DAO_CTHoaDonVeTau {

	public boolean TaoCTHoaDonMoi(String maHD, String maVeTau) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into CHITIETHOADONVETAU values(?,?) ";
		int k = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,maHD);
			preparedStatement.setString(2,maVeTau);
			k = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k > 0;
		
	}
}
