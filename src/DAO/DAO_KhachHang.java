package DAO;

import java.sql.Connection;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Entity.KhachHang;
import connectDB.ConnectDB;

public class DAO_KhachHang {
	private int n;
	private ArrayList<String> listKhachHang;

	public DAO_KhachHang() {
		listKhachHang = new ArrayList<String>();
	}

	public boolean ThemKhachHang(KhachHang khachHang) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into KHACHHANG values(?,?,?,?,?,?,?,?) ";
		int k = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, khachHang.getMaKh());
			preparedStatement.setString(2, khachHang.getTenKH());
			preparedStatement.setString(3, khachHang.getGioiTinh());
			preparedStatement.setString(4, khachHang.getSoDienThoai());
			preparedStatement.setString(5, khachHang.getcCCD());
			preparedStatement.setString(6, khachHang.getDiaChi());
			preparedStatement.setInt(7, khachHang.getLoaiKH());
			preparedStatement.setDate(8, khachHang.getNgaySinh());

			k = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k > 0;
	}

	public DefaultTableModel getAllKH() throws SQLException {
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "SDT", "CCCD", "địa chỉ", "Loại Khách Hàng",
				"Ngày Sinh" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "Select * FROM KHACHHANG";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8) };
			tableModel.addRow(o);
		}
		return tableModel;
	}

	public boolean xoaKH(String maKH) throws SQLException {
		Connection a = connectDB.ConnectDB.getConnection();// Tao Ket Noi
		String sql = "delete KHACHHANG where maKH='" + maKH + "'";
		PreparedStatement pstm = a.prepareStatement(sql);
		if (pstm.executeUpdate() > 0) {
			JOptionPane.showMessageDialog(null, "Xóa thành công khách hàng " + maKH);
			return true;
		}
		return false;
	}

	public boolean update(KhachHang kh) throws SQLException {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"UPDATE KHACHHANG SET tenKH=?, gioiTinh=?, soDienThoai=?, cCCD=?, diaChi=?, loaiKH=?, ngaySinh=? WHERE maKH=?");
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getGioiTinh());
			stmt.setString(3, kh.getSoDienThoai());
			stmt.setString(4, kh.getcCCD());
			stmt.setString(5, kh.getDiaChi());
			stmt.setInt(6, kh.getLoaiKH());
			stmt.setDate(7, kh.getNgaySinh());
			stmt.setString(8, kh.getMaKh()); // Sử dụng chỉ mục 8 cho tham số maKH
			n = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return n > 0;
	}

	public DefaultTableModel timKiem(String ma) throws SQLException {
		String[] header = { "Mã khách hàng", "Tên khách hàng", "Giới tính", "SDT", "CCCD", "Địa chỉ", "Loại Khách Hàng",
				"Ngày Sinh" };
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();
		String sql = "SELECT * FROM KHACHHANG WHERE maKH LIKE ?";

		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, "%" + ma + "%");

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			Object[] o = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8) };
			tableModel.addRow(o);
		}
		return tableModel;
	}
	public String generateNewMaKH() throws SQLException{
		 ConnectDB.getInstance();
	        Connection con = ConnectDB.getConnection();
	        String sql = "SELECT COUNT(*) AS total FROM KHACHHANG";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        int count = 0;
	        if (rs.next()) {
	            count = rs.getInt("total");
	        }
	        rs.close();
	        ps.close();

	        // Tạo mã mới theo mẫu HD001, HD002, ...
	        String newMaKH = String.format("KH%03d", count + 1);
	        return newMaKH;
	}

}
