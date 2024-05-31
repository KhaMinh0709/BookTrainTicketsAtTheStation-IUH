package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;

public class DAO_HoaDonVeTau {
	public String laymaLichTrinh(String maVe,String maGhe, String maToa, String maTau) throws SQLException {
	    connectDB.ConnectDB.getInstance();
	    Connection con = connectDB.ConnectDB.getConnection();
	    
	    String sql = "SELECT maLichTrinh FROM VETAU WHERE maVe = ? AND maGhe = ? AND maToa = ? AND maTau = ?;";

	    
	    try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
	        preparedStatement.setString(1, maVe);
	        preparedStatement.setString(2, maGhe);
	        preparedStatement.setString(3, maToa);
	        preparedStatement.setString(4, maTau);

	        
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
	public DefaultTableModel TraCuuVe(String tenKH, String sdt) throws SQLException {
		String[] header = {"Mã hóa đơn","Khách Hàng","Mã vé","Nhân viên lập vé","Ngày đi","Ga đi","Ga đến"};
		DefaultTableModel tableModel = new DefaultTableModel(header, 0);

		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();

		String sql = "SELECT HOADON.maHD, KHACHHANG.tenKH, VETAU.maVe, NHANVIEN.hoTen, LichTrinh.ngayDi, LichTrinh.gaDi, LichTrinh.gaDen " +
		             "FROM HOADON " +
		             "JOIN KHACHHANG ON HOADON.khachHang = KHACHHANG.maKH " +
		             "JOIN CHITIETHOADONVETAU ON HOADON.maHD = CHITIETHOADONVETAU.hoaDon " +
		             "JOIN VETAU ON CHITIETHOADONVETAU.veTau = VETAU.maVe " +
		             "JOIN NHANVIEN ON HOADON.nhanVien = NHANVIEN.maNV " +
		             "JOIN LICHTRINH ON LICHTRINH.maLichTrinh = VETAU.maLichTrinh " +
		             "WHERE KHACHHANG.tenKH = ? AND KHACHHANG.soDienThoai = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, tenKH); // WHERE KHACHHANG.maKH = maKH
		statement.setString(2, sdt);

		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
		    Object[] o = {rs.getString(1), rs.getString(2), rs.getString(3),
		                  rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)};
		    tableModel.addRow(o);    
		}
		return tableModel;

	}
	public String laygiaveTheoMaHD(String maHD) throws SQLException {
	    connectDB.ConnectDB.getInstance();
	    Connection con = connectDB.ConnectDB.getConnection();

	    String sql = "SELECT "
	            + "    LICHTRINH.giaChuyen AS GIAVE "
	            + "FROM "
	            + "    HOADON "
	            + "JOIN "
	            + "    CHITIETHOADONVETAU ON HOADON.maHD = CHITIETHOADONVETAU.hoaDon "
	            + "JOIN "
	            + "    VETAU ON CHITIETHOADONVETAU.veTau = VETAU.maVe "
	            + "JOIN "
	            + "    LICHTRINH ON LICHTRINH.maLichTrinh = VETAU.maLichTrinh "
	            + "WHERE "
	            + "    HOADON.maHD = ?";

	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.setString(1, maHD);

	    ResultSet rs = statement.executeQuery();
	    String giaVe = null;
	    if (rs.next()) {
	        giaVe = rs.getString("GIAVE");
	    }
	    
	    con.close();
	    return giaVe;
	}
	public boolean HuyVeTheoMaHD(String maHD, String maVe, String maToa, String maTau, String maGhe, String maLichTrinh) throws SQLException {
	    Connection con = connectDB.ConnectDB.getConnection();
	    try {
	        // Delete from CHITIETHOADONVETAU table
	        String sqlDeleteChiTiet = "DELETE FROM CHITIETHOADONVETAU WHERE hoaDon = ? AND veTau = ?";
	        try (PreparedStatement pstmDeleteChiTiet = con.prepareStatement(sqlDeleteChiTiet)) {
	            pstmDeleteChiTiet.setString(1, maHD);
	            pstmDeleteChiTiet.setString(2, maVe);
	            pstmDeleteChiTiet.executeUpdate();
	        }

	        // Delete from Ghe table
	        String sqlDeleteGhe = "DELETE FROM Ghe WHERE maGhe = ? AND maToa = ? AND maTau = ? AND maLichTrinh = ?";
	        try (PreparedStatement pstmDeleteGhe = con.prepareStatement(sqlDeleteGhe)) {
	            pstmDeleteGhe.setString(1, maGhe);
	            pstmDeleteGhe.setString(2, maToa);
	            pstmDeleteGhe.setString(3, maTau);
	            pstmDeleteGhe.setString(4, maLichTrinh);
	            pstmDeleteGhe.executeUpdate();
	        }


	        // Delete from VETAU table
	        String sqlDeleteveTau = "DELETE FROM VETAU WHERE maVe = ?";
	        try (PreparedStatement pstmDelevetau = con.prepareStatement(sqlDeleteveTau)) {
	            pstmDelevetau.setString(1, maVe);
	            pstmDelevetau.executeUpdate();
	        }

	        JOptionPane.showMessageDialog(null, "Hủy thành công vé: " + maVe);
	        return true;

	    } catch (SQLException e) {
	        // Handle SQL exception
	        JOptionPane.showMessageDialog(null, "Error while deleting: " + e.getMessage());
	        return false;

	    } finally {
	        con.close();
	    }
	}

	
	public String getNgayDi(String maVe) throws SQLException {
		connectDB.ConnectDB.getInstance();
		Connection con = connectDB.ConnectDB.getConnection();

		String sql = "SELECT LichTrinh.ngayDi " +
		             "FROM LichTrinh " +
		             "JOIN VETAU ON VETAU.maLichTrinh = LichTrinh.maLichTrinh " +
		             "JOIN CHITIETHOADONVETAU ON VETAU.maVe = CHITIETHOADONVETAU.veTau " +
		             "JOIN HOADON ON CHITIETHOADONVETAU.hoaDon= HOADON.maHD " +
		             "WHERE CHITIETHOADONVETAU.veTau = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, maVe);
		ResultSet rs = statement.executeQuery();
		String ngayDi=null;
		if (rs.next()) {
            ngayDi = rs.getString(1);
        }
		con.close();
		return ngayDi;

	}


	public boolean TaoHoaDonMoi(String maHD, String nhanVien, String khachHang,Date ngayLapHoaDon, float tongTienHD) throws SQLException{
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		String sql = "insert into HOADON values(?,?,?,?,?) ";
		int k = 0;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,maHD);
			preparedStatement.setString(2,nhanVien);
			preparedStatement.setString(3,khachHang);
			preparedStatement.setDate(4,ngayLapHoaDon);
			preparedStatement.setFloat(5,tongTienHD);

			k = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k > 0;
		
	}


	public String generateNewMaHD() throws SQLException {
	    SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");;
	    String currentDate = sdf.format(new java.util.Date());
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection();

	    String sql = "SELECT COUNT(*) AS total FROM HOADON";
	    PreparedStatement ps = con.prepareStatement(sql);
	    ResultSet rs = ps.executeQuery();

	    int count = 0;
	    if (rs.next()) {
	        count = rs.getInt("total");
	    }
	    rs.close();
	    ps.close();

	    // Create the new invoice ID in the format HDMMddyyXXX
	    String newMaHD = String.format("HD%s%03d", currentDate, count + 1);
	    return newMaHD;
	}
	
	
	public String[] LayThongTinGheTuMaVe(String maVe) throws SQLException {
	    Connection con = connectDB.ConnectDB.getConnection();
	    String[] thongTinGhe = new String[3]; // Mảng lưu thông tin về mã ghế, mã toa và mã tàu
	    try {
	        String sql = "SELECT maGhe, maToa, maTau FROM VETAU WHERE maVe = ?";
	        PreparedStatement statement = con.prepareStatement(sql);
	        statement.setString(1, maVe); 
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            thongTinGhe[0] = rs.getString("maGhe");
	            thongTinGhe[1] = rs.getString("maToa"); 
	            thongTinGhe[2] = rs.getString("maTau"); 
	        }
	    } finally {
	        con.close();
	    }
	    return thongTinGhe;
	}
}
