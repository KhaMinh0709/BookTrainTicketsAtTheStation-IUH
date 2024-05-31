package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entity.NhanVien;
import Entity.TaiKhoan;
import connectDB.ConnectDB;



public class DAO_TAIKHOAN {
	TaiKhoan tk;

	public TaiKhoan Login(String username, String password) throws SQLException {
		Connection con = ConnectDB.getConnection();
		String sql = "select * from TAIKHOAN where maNV= ? and matKhau = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				TaiKhoan tk = new TaiKhoan(new NhanVien(rs.getString(1), sql), rs.getString(2));
				return tk;
			}
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1);
			e1.printStackTrace();
		}
		return null;
	}

}
