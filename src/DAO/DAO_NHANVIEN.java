package DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Entity.NhanVien;
import connectDB.ConnectDB;

public class DAO_NHANVIEN {
	public boolean ThemNhanVien(NhanVien nhanVien) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into NHANVIEN values(?,?,?,?,?,?,?,?,?,?,?) ";
		int k =0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, nhanVien.getMaNV());
			preparedStatement.setString(2,nhanVien.getHoTen());
			preparedStatement.setDate(3, nhanVien.getNgaySinh());
			preparedStatement.setString(4, nhanVien.getcCCD());
			preparedStatement.setString(5, nhanVien.getDiaChi());
			preparedStatement.setString(6, nhanVien.getSoDienThoai());
			preparedStatement.setString(7, nhanVien.getEmail());
			preparedStatement.setString(8, nhanVien.getCaLam());
			preparedStatement.setString(9, nhanVien.getTrangThai());
			preparedStatement.setDate(10, nhanVien.getNgayVaoLam());
			preparedStatement.setString(11, nhanVien.getGioiTinh());
			k = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return k>0;
	}
	
	public DefaultTableModel getAllNV() throws SQLException {
		String[] header = "Mã Nhân Viên;Tên;Ngày Sinh;CCCD;Địa Chỉ;SDT;Email;Ca Làm;Trạng Thái;Ngày vào Làm;Giới tinh".split(";");
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "Select *FROM NHANVIEN"; 
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
				Object[] o = {rs.getString(1), rs.getString(2), rs.getDate(3),
							rs.getString(4), rs.getString(5), rs.getString(6),
							rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getDate(10), rs.getString(11)};
				tableModel.addRow(o);	
		}
		return tableModel;
	}
	
	public boolean xoaNV(String maNV) throws SQLException {
		Connection a = connectDB.ConnectDB.getConnection();// Tao Ket Noi
		String sql = "delete NHANVIEN where maNV='" + maNV + "'";
		PreparedStatement pstm = a.prepareStatement(sql);
		if (pstm.executeUpdate()> 0) {
			JOptionPane.showMessageDialog(null, "Xóa thành công nhân viên " + maNV);
			return true;
		}
		return false;
	}

	public boolean update(NhanVien nhanVien) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "UPDATE NHANVIEN SET hoTen=?, ngaySinh=?, cCCD=?, diaChi=?, soDienThoai=?, email=?, caLam=?, trangThai=?, ngayVaoLam=?, gioiTinh=? WHERE maNV=?";

		int n= 0;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);				
			stmt.setString(1,nhanVien.getHoTen());
			stmt.setDate(2, nhanVien.getNgaySinh());
			stmt.setString(3, nhanVien.getcCCD());
			stmt.setString(4, nhanVien.getDiaChi());
			stmt.setString(5, nhanVien.getSoDienThoai());
			stmt.setString(6, nhanVien.getEmail());
			stmt.setString(7, nhanVien.getCaLam());
			stmt.setString(8, nhanVien.getTrangThai());
			stmt.setDate(9, nhanVien.getNgayVaoLam());
			stmt.setString(10, nhanVien.getGioiTinh());
			stmt.setString(11, nhanVien.getMaNV());	//
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	

	public NhanVien LayNhanVienTheoMa(String mnv) {
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select maNV, hoTen from NHANVIEN where maNV = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, mnv);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maNhanVien = rs.getString(1);
				String tenNhanVien = rs.getString(2);

				
				NhanVien nv = new NhanVien(maNhanVien, tenNhanVien);
				
				
				return nv;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public DefaultTableModel timKiem(String ma) throws SQLException {
		String[] header = "Mã Nhân Viên;Tên;Ngày Sinh;CCCD;Địa Chỉ;SDT;Email;Ca Làm;Trạng Thái;Ngày vào Làm;Giới tinh".split(";");
	    DefaultTableModel tableModel = new DefaultTableModel(header, 0);
	    connectDB.ConnectDB.getInstance();
	    Connection con = connectDB.ConnectDB.getConnection();
	    String sql = "SELECT * FROM NHANVIEN WHERE maNV LIKE ?"; 

	    PreparedStatement preparedStatement = con.prepareStatement(sql);
	    preparedStatement.setString(1, "%" + ma + "%");

	    ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			Object[] o = {rs.getString(1), rs.getString(2), rs.getDate(3),
						rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getDate(10), rs.getString(11)};
			tableModel.addRow(o);	
	}
	return tableModel;
	}
}
