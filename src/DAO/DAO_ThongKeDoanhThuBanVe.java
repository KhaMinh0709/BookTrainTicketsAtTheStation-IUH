package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO_ThongKeDoanhThuBanVe {
	public List<String> getDoanhThuBanVetheoNgay() throws SQLException {
	    Connection con = connectDB.ConnectDB.getConnection();
	    String sql = "SELECT ngayLapHD, SUM(tongTienHD) AS doanhThu "
	            + "FROM HOADON "
	            + "GROUP BY ngayLapHD;";
	    List<String> list = new ArrayList<>();
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String ngayLapHD = rs.getString("ngayLapHD");
	            String doanhThu = rs.getString("doanhThu"); 
	            String result = "Ngày lập hóa đơn: " + ngayLapHD + ", Doanh thu: " + doanhThu;
	            list.add(result);
	        }
	        return list;
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        con.close(); 
	    }
	    return null;
	}

	    public List<String> getDoanhThuBanVeTheoHD() throws SQLException {
	        Connection con = connectDB.ConnectDB.getConnection();
	        String sql = "SELECT maHD, tongTienHD FROM HOADON;";
	        List<String> list = new ArrayList<>();
	        try {
	            PreparedStatement ps = con.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                String maHoaDon = rs.getString("maHD");
	                String doanhThu = rs.getString("tongTienHD");
	                String result = "Mã hóa đơn: " + maHoaDon + ", Doanh thu: " + doanhThu;
	                list.add(result);
	            }
	            return list;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            con.close(); 
	        }
	        return null;
	    }

	    
}

