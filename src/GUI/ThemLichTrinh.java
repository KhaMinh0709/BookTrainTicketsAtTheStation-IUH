package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_LichTrinh;
import Entity.KhachHang;
public class ThemLichTrinh extends JPanel implements ActionListener {
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblTau;
	private JTextField txtTau;
	private JLabel lblDi;
	private JDateChooser txtDi;
	private JLabel lblDen;
	private JDateChooser txtDen;
	private String[] tinhThanh = { "Ha Noi", "Sai Gon", "Hai Phong", "Da Nang", "Hai Duong", "Hai Nam", "Bac Giang",
			"Bac Ninh", "Thai Binh", "Phu Tho", "Vinh Phuc", "Bac Kan", "Lang Son", "Tuyen Quang", "Cao Bang", "Son La",
			"Yen Bai", "Lao Cai", "Dien Bien", "Lai Chau", "Ha Giang", "Dak Lak", "Dak Nong", "Gia Lai", "Kon Tum",
			"An Giang", "Dong Thap", "Ben Tre", "Vinh Long", "Tra Vinh", "Hau Giang", "Kien Giang", "Ca Mau",
			"Soc Trang", "Bac Lieu", "Ninh Thuan", "Binh Thuan", "Quang Binh", "Quang Tri", "Thua Thien Hue",
			"Quang Nam", "Quang Ngai", "Binh Dinh", "Phu Yen", "Khanh Hoa", "Ninh Binh", "Thanh Hoa", "Nghe An",
			"Ha Tinh", "Quang Ninh", "Hoa Binh", "Nam Dinh", "Thai Nguyen", "Binh Phuoc", "Tay Ninh", "Long An",
			"Binh Duong", "Dong Nai", "Ba Ria - Vung Tau", "Can Tho", "An Giang", "Da Nang", "Binh Thuan" };
	private JLabel lblNoiDi;
	private JComboBox<String> txtNoiDi;
	private JLabel lblNoiDen;
	private JComboBox<String> txtNoiDen;
	private JLabel lblGia;
	private JTextField txtGia;
	private DefaultTableModel modelTau;
	private JTable tableTau;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;

	private DAO_LichTrinh dao_lichTrinh;
	//pnNorth
	public ThemLichTrinh() {
		dao_lichTrinh= new DAO_LichTrinh();
		setLayout(new BorderLayout(0, 0));
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("THÊM LỊCH TRÌNH");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pNorth.add(lblTieuDe);

		//pCenter
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		pCenter.setBorder(new LineBorder(new Color(0, 0, 0)));
		pCenter.setLayout(new BorderLayout(0, 0));
		pCenter.setPreferredSize(new Dimension(1300,500));
		
		//pNCenter
		JPanel pNorthCenter = new JPanel();
		pNorthCenter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"  Thêm lịch trình", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		pCenter.add(pNorthCenter,BorderLayout.NORTH);
		
		Box b = Box.createVerticalBox();
		pNorthCenter.add(b);
		
		Box b1, b2, b3, b4;
		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Mã lịch trình:  "));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtMa = new JTextField(20));
		b1.add(Box.createHorizontalStrut(50));
		
		b1.add(lblTau = new JLabel("Mã tàu: "));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtTau = new JTextField(20));
		b.add(Box.createVerticalStrut(20));
		
		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblDi = new JLabel("Ngày đi: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtDi = new JDateChooser());
		b2.add(Box.createHorizontalStrut(50));
		
		b2.add(lblDen = new JLabel("Ngày đến: "));
		b2.add(Box.createHorizontalStrut(10));
		b2.add(txtDen = new JDateChooser());
		b.add(Box.createVerticalStrut(20));
		
		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblNoiDi = new JLabel("Ga đi: "));
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txtNoiDi = new JComboBox<String>(tinhThanh));
		b3.add(Box.createHorizontalStrut(50));
		
		b3.add(lblNoiDen = new JLabel("Ga đến: "));
		b3.add(Box.createHorizontalStrut(10));
		b3.add(txtNoiDen = new JComboBox<String>(tinhThanh));
		b.add(Box.createVerticalStrut(20));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(lblGia = new JLabel("Giá chuyến: "));
		b4.add(Box.createHorizontalStrut(10));
		b4.add(txtGia = new JTextField(20));
		b.add(Box.createVerticalStrut(20));
		lblTau.setPreferredSize(lblMa.getPreferredSize());
		lblDi.setPreferredSize(lblMa.getPreferredSize());
		lblDen.setPreferredSize(lblMa.getPreferredSize());
		lblNoiDi.setPreferredSize(lblMa.getPreferredSize());
		lblNoiDen.setPreferredSize(lblMa.getPreferredSize());
		lblMa.setPreferredSize(lblMa.getPreferredSize());
		
		//pCCenter
		JPanel pCCenter = new JPanel();
		pCenter.add(pCCenter,BorderLayout.CENTER);
		pCCenter.add(btnThem= new JButton("Thêm"));
	    pCCenter.add(btnXoa= new JButton("Xóa trắng"));
	    pCCenter.add(btnSua= new JButton("Cập Nhật"));
	    pCCenter.setBorder(new EmptyBorder(5,0,10,0));
		
	    
		//pSCenter
		JPanel pSouth = new JPanel();
		add(pSouth,BorderLayout.SOUTH);
		pSouth.setLayout(new BorderLayout(0, 0));
		

		   String[] header = {"Mã lịch trình", "Mã tàu", "Ngày đi", "Ngày đến", "Ga đi", "Ga đến", "Giá chuyến"};
	        modelTau = new DefaultTableModel(header, 0);
	        tableTau = new JTable(modelTau);
	        tableTau.setBackground(Color.WHITE);

	        try {
	            modelTau = dao_lichTrinh.getAllLichTrinh();
	            tableTau.setModel(modelTau);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        // Wrap table in JScrollPane
	        JScrollPane scrollPane = new JScrollPane(tableTau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        

	        // Add JScrollPane to the panel
	        pSouth.add(scrollPane, BorderLayout.CENTER);

	        // Add action listener to buttons
	        btnThem.addActionListener(this);
	    }
	
	
	
	public void actionPerformed(ActionEvent e) {
	    Object obj = e.getSource();
	    if (obj == btnThem) {
	        // Retrieve and convert input data
	        String maChuyen = txtMa.getText();
	        String maTau = txtTau.getText();
	        java.util.Date ngayDi_util = txtDi.getDate();
	        java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());
	        java.util.Date ngayDen_util = txtDen.getDate(); // Changed from txtDi to txtDen
	        java.sql.Date ngayDen = new java.sql.Date(ngayDen_util.getTime());
	        String noiDi = txtNoiDi.getSelectedItem().toString();
	        String noiDen = txtNoiDen.getSelectedItem().toString();
	        float giaChuyen = Float.parseFloat(txtGia.getText());

	        try {
	            // Insert new itinerary into the database
	            dao_lichTrinh.taoLichTrinhMoi(maChuyen, maTau, ngayDi, ngayDen, noiDi, noiDen, giaChuyen);
	            // Update table model
	            modelTau.addRow(new Object[]{maChuyen, maTau, ngayDi, ngayDen, noiDi, noiDen, giaChuyen});
	            JOptionPane.showMessageDialog(this, "Thêm thành công");
	        } catch (SQLException e1) {
	            // Handle SQL exception
	            if (e1.getMessage().contains("duplicate key")) {
	                JOptionPane.showMessageDialog(this, "Trùng mã lịch trình");
	            } else {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra: " + e1.getMessage());
	            }
	        }
	    }


		 else if(obj.equals(btnXoa)) 
		 {
			 txtMa.setText("");
			 txtTau.setText("");
			 txtDi.setDate(null);
			 txtDen.setDate(null);
			 txtNoiDi.setSelectedIndex(0);
			 txtNoiDen.setSelectedIndex(0);
			 txtGia.setText("");
		 }
	}

}
