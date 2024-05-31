package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DAO_HoaDonVeTau;

public class QuanLyTraCuuVe extends JPanel implements ActionListener{
	private JTextField txtMa;
	private JTextField txtSDT;
	private JButton btnTim;
	private DefaultTableModel modelTV;
	private JTable tableTV;
	private DAO_HoaDonVeTau dao_ve;
	private JButton btnInVe;

	public QuanLyTraCuuVe() {
		// TODO Auto-generated constructor stub
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//dao
		dao_ve = new DAO_HoaDonVeTau();
		//pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("TRA CỨU VÉ - LỊCH TRÌNH ĐI");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pNorth.add(lblTieuDe);


		//pCenter
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
		pCenter.setLayout(new BorderLayout(0, 0));

		//
		JPanel p1 = new JPanel();
		pCenter.add(p1, BorderLayout.NORTH);
		Box b= Box.createVerticalBox();
		p1.add(b);
		pCenter.setBorder(new EmptyBorder(5, 15, 15, 15));
		Box b0, b1, b2, b3,b4;
		
		b.add(b0 = Box.createHorizontalBox());
		JLabel lblThongTin;
		b0.add(lblThongTin= new JLabel("Tra cứu vé đặt"));
		lblThongTin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThongTin.setForeground(Color.BLUE);
		b.add(Box.createVerticalStrut(10));
		
		b.add(b1 = Box.createHorizontalBox());
		JLabel lblMa;
		b1.add(lblMa = new JLabel("Tên KH"));
		b.add(Box.createVerticalStrut(30));
		b1.add(txtMa= new JTextField(15));
		b.add(Box.createVerticalStrut(5));
		
		b.add(b2 = Box.createHorizontalBox());
		JLabel lblSDT;
		b2.add(lblSDT= new JLabel("Số điện thoại: "));
		b2.add(txtSDT= new JTextField(15));
		b.add(Box.createVerticalStrut(5));
		
		b.add(Box.createVerticalStrut(5));
		b.add(b3 = Box.createHorizontalBox());
		
		b3.add(btnTim = new JButton("Tìm kiếm"));
		btnTim.setBackground(Color.cyan);
		
		b.add(Box.createVerticalStrut(5));
		b.add(b4 = Box.createHorizontalBox());
		b4.add(btnInVe = new JButton("In Vé"));
		
		lblMa.setPreferredSize(lblSDT.getPreferredSize());
		//txtMa.setPreferredSize(txtSDT.getPreferredSize());
		
		//
		JPanel p2 = new JPanel();
		pCenter.add(p2, BorderLayout.CENTER);
		p2.setLayout(new BorderLayout(0, 0));
		p2.setBorder(BorderFactory.createTitledBorder(" Các vé đã đặt: "));
		
		Box bC= Box.createVerticalBox();
		p2.add(bC);
		Box bC1, bC2;
		
		bC.add(bC1= Box.createHorizontalBox());
		String[] header = {"Mã hóa đơn","Khách Hàng","Mã vé","Nhân viên lập vé","nơi đi","nơi đến"};
		modelTV= new DefaultTableModel(header, 0);
		tableTV = new JTable(modelTV);
		tableTV.setBackground(Color.WHITE);
		bC1.add(tableTV);
		JScrollPane veScroll = new JScrollPane(tableTV, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		veScroll.setPreferredSize(new Dimension(1300, tableTV.getRowHeight() * 45));
		bC1.add(veScroll,BorderLayout.CENTER);
		
		btnInVe.setBackground(Color.cyan);
		btnInVe.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableTV.getSelectedRow();
		        if (selectedRow != -1) { // Ensure a row is selected
		            // Extract information from the selected row
		            String maHoaDon = tableTV.getValueAt(selectedRow, 0).toString();
		            String khachHang = tableTV.getValueAt(selectedRow, 1).toString();
		            String maVe = tableTV.getValueAt(selectedRow, 2).toString();
		            String nhanVien = tableTV.getValueAt(selectedRow, 3).toString();
		            String noiDi = tableTV.getValueAt(selectedRow, 4).toString();
		            String noiDen = tableTV.getValueAt(selectedRow, 5).toString();

		            JFrame infoFrame = new JFrame("In vé");
		            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		            infoFrame.setSize(400, 300);
		            infoFrame.setLocationRelativeTo(null);

		            JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: " + maHoaDon);
		            lblMaHoaDon.setForeground(Color.RED);
		            JLabel lblKhachHang = new JLabel("Khách hàng: " + khachHang);
		            lblKhachHang.setForeground(Color.RED);
		            JLabel lblMaVe = new JLabel("Mã vé: " + maVe);
		            lblMaVe.setForeground(Color.RED);
		            JLabel lblNhanVien = new JLabel("Nhân viên lập vé: " + nhanVien);
		            lblNhanVien.setForeground(Color.RED);
		            JLabel lblNoiDi = new JLabel("Nơi đi: " + noiDi);
		            lblNoiDi.setForeground(Color.RED);
		            JLabel lblNoiDen = new JLabel("Nơi đến: " + noiDen);
		            lblNoiDen.setForeground(Color.RED);

		            JPanel infoPanel = new JPanel(new GridLayout(6, 1));
		            infoPanel.add(lblMaHoaDon);
		            infoPanel.add(lblKhachHang);
		            infoPanel.add(lblMaVe);
		            infoPanel.add(lblNhanVien);
		            infoPanel.add(lblNoiDi);
		            infoPanel.add(lblNoiDen);

		            // Add the panel to the frame
		            
		            infoFrame.add(infoPanel);
		            infoFrame.setVisible(true);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng từ bảng để in vé", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});

		//dky acction
		btnTim.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == btnTim) {
			String tenKH = txtMa.getText().trim();
			String sdt = txtSDT.getText().trim();
			try {
				modelTV = dao_ve.TraCuuVe(tenKH, sdt);
		        if (modelTV.getRowCount() != 0) {
		        	tableTV.setModel(modelTV);
		            JOptionPane.showMessageDialog(this, "Đã tìm thấy Hóa đơn của khách hàng: " + tenKH);
		        } else {
		            JOptionPane.showMessageDialog(this, "Không tìm thấy");
		        }
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
			
			
		}
	}

}
