package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_ThongKeDoanhThuBanVe;

public class QuanLyThongKe extends JPanel implements ActionListener {

	private JTextField txtTenDV;
	private JTextField txtMaDV;
	private DefaultTableModel modelDV;
	private JTable tableDV;
	private JLabel lblThongKe;
	private JButton btnLamMoi;
	private JButton btnDT;
	private JButton btnDV;
	private JLabel lbThang;
	private JLabel lbNam;
	private JTextField txtThang;
	private JTextField txtNam;
	private JLabel lblTenMatHang;
	private JLabel lblMaDV;
	private JButton btnTim;
	private JButton btnThoatTim;
	private DAO_ThongKeDoanhThuBanVe dao_DS;
	private DefaultTableModel modelHDBV;
	private JTable tableHDBV;
	private ChartPanel chartPanel2;
	private DefaultCategoryDataset dataset2;
	private DefaultCategoryDataset dataset;
	private ChartPanel chartPanel;
	private DefaultTableModel tableModel;
	private JTable table;

	public QuanLyThongKe() throws SQLException {
		// TODO Auto-generated constructor stub
		setVisible(true);
		setLayout(new BorderLayout(0, 0));

		//
		dao_DS = new DAO_ThongKeDoanhThuBanVe();
		// pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("THỐNG KÊ ");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pNorth.add(lblTieuDe);

		// pnCenter

		// pThongKeDoanhThu
		JPanel pnTKDS = new JPanel(new BorderLayout());
		JPanel pSouthỈTKDS = new JPanel();
		pSouthỈTKDS.add(btnLamMoi = new JButton("Làm Mới"));
		btnLamMoi.setBackground(Color.cyan);
		pnTKDS.add(pSouthỈTKDS, BorderLayout.NORTH);
		add(pnTKDS, BorderLayout.CENTER);

		// doanh thu theo ngày
		dataset = new DefaultCategoryDataset();
		List<String> doanhThuList = dao_DS.getDoanhThuBanVetheoNgay();
		if (doanhThuList != null) {
			for (String item : doanhThuList) {
				String[] parts = item.split(",");
				String ngayLapHD = parts[0].split(": ")[1];
				String doanhThu = parts[1].split(": ")[1];
				dataset.addValue(Double.parseDouble(doanhThu), "Doanh thu", ngayLapHD);
			}
		}

		JFreeChart barChart = ChartFactory.createBarChart("Doanh thu bán vé theo ngày", "Ngày", "Doanh thu", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(640, 300));
		JScrollPane scrollPane1 = new JScrollPane(chartPanel);
		scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTKDS.add(scrollPane1, BorderLayout.WEST);
		// theo hóa đơn
		dataset2 = new DefaultCategoryDataset();
		List<String> doanhThuList2 = dao_DS.getDoanhThuBanVeTheoHD();
		if (doanhThuList2 != null) {
			for (String item : doanhThuList2) {
				String[] parts = item.split(", ");
				String hoaDon = parts[0].split(": ")[1].trim();
				String doanhThu = parts[1].split(": ")[1].trim();
				dataset2.addValue(Double.parseDouble(doanhThu), "Doanh thu", hoaDon);
			}
		}

		JFreeChart barChart2 = ChartFactory.createBarChart("Doanh thu bán vé theo hóa đơn", "Hóa đơn", "Doanh thu",
				dataset2, PlotOrientation.VERTICAL, true, true, false);

		chartPanel2 = new ChartPanel(barChart2);
		chartPanel2.setPreferredSize(new Dimension(640, 300));
		JScrollPane scrollPane2 = new JScrollPane(chartPanel2);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTKDS.add(scrollPane2, BorderLayout.EAST);

		// Nỏth
		JPanel psouth = new JPanel();
		pnTKDS.add(psouth, BorderLayout.SOUTH);
		String[] header = { "Mã HĐ", "Nhân viên", "Khách hàng", "Ngày lập", "Tổng tiền" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		JScrollPane sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		sc.setPreferredSize(new Dimension(1300, table.getRowHeight() * 20));
		psouth.add(sc);

		try {
			Connection con = connectDB.ConnectDB.getConnection();
			String sql = "SELECT maHD, nhanVien, khachHang, ngayLapHD, tongTienHD FROM HOADON";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("maHD");
				String nhanVien = rs.getString("nhanVien");
				String khachHang = rs.getString("khachHang");
				String ngayLapHD = rs.getString("ngayLapHD");
				String tongTienHD = rs.getString("tongTienHD");

				Object[] row = { maHD, nhanVien, khachHang, ngayLapHD, tongTienHD };
				tableModel.addRow(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// dky su kien
		btnLamMoi.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == btnLamMoi) {
			// Clear the existing dataset
			dataset.clear();
			dataset2.clear();

			// Reload data for both charts
			List<String> doanhThuList = null;
			try {
				doanhThuList = dao_DS.getDoanhThuBanVetheoNgay();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (doanhThuList != null) {
				for (String item : doanhThuList) {
					String[] parts = item.split(",");
					String ngayLapHD = parts[0].split(": ")[1];
					String doanhThu = parts[1].split(": ")[1];
					dataset.addValue(Double.parseDouble(doanhThu), "Doanh thu", ngayLapHD);
				}
			}

			// Load data for the second chart
			List<String> doanhThuList2 = null;
			try {
				doanhThuList2 = dao_DS.getDoanhThuBanVeTheoHD();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (doanhThuList2 != null) {
				for (String item : doanhThuList2) {
					String[] parts = item.split(", ");
					String hoaDon = parts[0].split(": ")[1].trim();
					String doanhThu = parts[1].split(": ")[1].trim();
					dataset2.addValue(Double.parseDouble(doanhThu), "Doanh thu", hoaDon);
				}
			}

			// Repaint the charts
			chartPanel.repaint();
			chartPanel2.repaint();
		}
	}

}
