package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.record.PageBreakRecord.Break;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_CTHoaDonVeTau;
import DAO.DAO_Ghe;
import DAO.DAO_HoaDonVeTau;
import DAO.DAO_KhachHang;
import DAO.DAO_LichTrinh;
import DAO.DAO_VeTau;
import Entity.KhachHang;

public class QuanLyDatVe extends JPanel implements ActionListener, ItemListener {
	private JLabel lblThongTin;
	private JLabel lblDi;
	private JLabel lblDen;
	private JLabel lblNgayDi;
	private JButton btnTim;
	private JLabel lblConVe;
	private JButton btnDatVe;
	private JPanel pCenter_home;
	private JDateChooser txtNgayDi;
	private JDateChooser txtNgayVe;
	private JPanel tauDi;
	private JTextField txtTGDi;
	private JTextField txtTGDen;
	private JTextField txtSLDả;
	private JLabel lblSLTrong;
	private JTextField txtSLTrong;
	private JTextField txtToaDi;
	private JPanel chonToa;
	private JButton btnBackdi;
	private JButton btnNextdi;
	private DefaultTableModel modelKmDi;
	private JTable tableKmDi;
	private JPanel tauVe;
	private JTextField txtTGDenVe;
	private JTextField txtTGDiVe;
	private JTextField txtSLTrongVe;
	private JTextField txtSLDatVe;
	private JPanel chonToaVe;
	private JTextField txtToaVe;
	private JButton btnBackVe;
	private JButton btnNextVe;
	private DefaultTableModel modelKmVe;
	private JTable tableKmVe;

	private JPanel[] khoangDi;
	private JPanel[] khoangVe;
	private JButton[] toaTauDi;
	private JButton[] toaTauVe;

	private int soToaDi = 0;
	private int soToaVe = 0;
	private String soTauDi = null;
	private String soTauVe = null;

	private String[] tinhThanh = { "Ha Noi", "Sai Gon", "Hai Phong", "Da Nang", "Hai Duong", "Hai Nam", "Bac Giang",
			"Bac Ninh", "Thai Binh", "Phu Tho", "Vinh Phuc", "Bac Kan", "Lang Son", "Tuyen Quang", "Cao Bang", "Son La",
			"Yen Bai", "Lao Cai", "Dien Bien", "Lai Chau", "Ha Giang", "Dak Lak", "Dak Nong", "Gia Lai", "Kon Tum",
			"An Giang", "Dong Thap", "Ben Tre", "Vinh Long", "Tra Vinh", "Hau Giang", "Kien Giang", "Ca Mau",
			"Soc Trang", "Bac Lieu", "Ninh Thuan", "Binh Thuan", "Quang Binh", "Quang Tri", "Thua Thien Hue",
			"Quang Nam", "Quang Ngai", "Binh Dinh", "Phu Yen", "Khanh Hoa", "Ninh Binh", "Thanh Hoa", "Nghe An",
			"Ha Tinh", "Quang Ninh", "Hoa Binh", "Nam Dinh", "Thai Nguyen", "Binh Phuoc", "Tay Ninh", "Long An",
			"Binh Duong", "Dong Nai", "Ba Ria - Vung Tau", "Can Tho", "An Giang", "Da Nang", "Binh Thuan" };
	private JComboBox<String> txtDi;
	private JComboBox<String> txtDen;
	private JRadioButton btn1Chieu;
	private JRadioButton btn2Chieu;
	private ButtonGroup radio;
	private DAO_KhachHang dao_kh;
	private String maNV;
	private DAO_HoaDonVeTau dao_hoadon;
	private DAO_LichTrinh dao_lichTrinh;
	private DAO_VeTau dao_ve;
	private DAO_CTHoaDonVeTau dao_CTHoaDon;
	private JLabel lblCTKM;
	private DAO_Ghe dao_ghe;

	public QuanLyDatVe(String maNV) {
		// TODO Auto-generated constructor stub
		this.maNV = maNV;
		setSize(400, 900);
		setVisible(true);
		setLayout(new BorderLayout(0, 0));

		// dao
		dao_lichTrinh = new DAO_LichTrinh();
		dao_kh = new DAO_KhachHang();
		dao_hoadon = new DAO_HoaDonVeTau();
		dao_ve = new DAO_VeTau();
		dao_CTHoaDon = new DAO_CTHoaDonVeTau();
		dao_ghe = new DAO_Ghe();

		// pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("ĐẶT VÉ TÀU");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pNorth.add(lblTieuDe);

		// pWest
		JPanel pWest = new JPanel();
		add(pWest, BorderLayout.WEST);
		pWest.setBorder(new LineBorder(new Color(0, 0, 0)));
		pWest.setLayout(new BorderLayout(0, 0));

		Box b = Box.createVerticalBox();
		pWest.add(b);
		pWest.setBorder(new EmptyBorder(20, 15, 240, 15));
		Box b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;

		b.add(b0 = Box.createHorizontalBox());
		b0.add(lblThongTin = new JLabel("Thông tin hành trình"));
		lblThongTin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThongTin.setForeground(Color.BLUE);
		b.add(Box.createVerticalStrut(10));

		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblDi = new JLabel("Ga đi:"));
		b.add(Box.createVerticalStrut(5));

		b.add(b2 = Box.createHorizontalBox());
		b2.add(txtDi = new JComboBox<String>(tinhThanh));
		b.add(Box.createVerticalStrut(5));

		b.add(b3 = Box.createHorizontalBox());
		b3.add(lblDen = new JLabel("Ga đến:"));

		b.add(b4 = Box.createHorizontalBox());
		b4.add(txtDen = new JComboBox<String>(tinhThanh));
		b.add(Box.createVerticalStrut(5));

		b.add(b5 = Box.createHorizontalBox());
		b5.add(lblNgayDi = new JLabel("Ngày đi:"));
		b.add(Box.createVerticalStrut(5));

		b.add(b6 = Box.createHorizontalBox());
		b6.add(txtNgayDi = new JDateChooser());
		b.add(Box.createVerticalStrut(5));

		b.add(b7 = Box.createHorizontalBox());
		JLabel lblNgayVe;
		b7.add(lblNgayVe = new JLabel("Ngày Về:"));
		b.add(Box.createVerticalStrut(5));

		b.add(b8 = Box.createHorizontalBox());
		b8.add(txtNgayVe = new JDateChooser());
		b.add(Box.createVerticalStrut(5));

		b.add(b9 = Box.createHorizontalBox());
		b9.add(btn1Chieu = new JRadioButton("1 chiều"));
		b9.add(btn2Chieu = new JRadioButton("Khứ hồi"));
		b.add(Box.createVerticalStrut(15));
		radio = new ButtonGroup();
		radio.add(btn1Chieu);
		radio.add(btn2Chieu);

		b.add(b10 = Box.createHorizontalBox());
		b10.add(btnTim = new JButton("Tìm kiếm"));

		// pCenter
		pCenter_home = new JPanel();
		add(pCenter_home);
		pCenter_home.setBackground(Color.white);
		pCenter_home.setBorder(new LineBorder(new Color(0, 0, 0)));
		ImageIcon img = new ImageIcon("icon/datVeTau.jpg");
		Image img1 = img.getImage();
		Image temp_img = img1.getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
		img = new ImageIcon(temp_img);
		JLabel lblBackground = new JLabel("", img, JLabel.CENTER);
		lblBackground.setLayout(new BoxLayout(lblBackground, BoxLayout.Y_AXIS));
		pCenter_home.add(lblBackground);

		// pnSouth
		JPanel pSouth = new JPanel();
		pSouth.setBackground(Color.WHITE);
		pSouth.setPreferredSize((new Dimension(500, 100)));
		pSouth.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(pSouth, BorderLayout.SOUTH);
		Box bS, bS1;
		pSouth.add(bS = Box.createVerticalBox());
		bS.add(bS1 = Box.createHorizontalBox());
		bS1.add(lblConVe = new JLabel(Resize("icon/coVe.jpg", 145, 40)));
		JLabel lblChonVe;
		bS1.add(lblChonVe = new JLabel(Resize("icon/chonVe.jpg", 155, 40)));
		JLabel lblHetVe;
		bS1.add(lblHetVe = new JLabel(Resize("icon/hetVe.jpg", 150, 35)));
		JLabel lblTrongVe;
		bS1.add(lblTrongVe = new JLabel(Resize("icon/trongVe.jpg", 150, 35)));
		JLabel lbldaBanVe;
		bS1.add(lbldaBanVe = new JLabel(Resize("icon/daBanVe.jpg", 215, 40)));
		bS1.add(Box.createHorizontalStrut(25));
		bS1.add(btnDatVe = new JButton("Đặt vé"));
		btnDatVe.setBackground(new Color(153, 255, 153));
		btnDatVe.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDatVe.setPreferredSize(new Dimension(100, 70));

		// dkyskien
		btnTim.addActionListener(this);
		btnDatVe.addActionListener(this);
	}

	public static ImageIcon Resize(String ImagePath, int width, int heigh) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(width, heigh, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Button= tìm kiếm
		Object obj = e.getSource();
		if (obj == btnTim) {
			if (radio == null) {
				JOptionPane.showMessageDialog(this, "hãy chọn vé một chiều hoặc khứ hồi");
			} else {
				pCenter_home.removeAll();
				try {
					JPanel newContent = Controler_TimTau();
					if (newContent != null) {

						pCenter_home.removeAll();
						pCenter_home.add(newContent);
					} else {
						// Hiển thị hình ảnh gốc khi không tìm thấy dữ liệu
						pCenter_home.setBackground(Color.white);
						pCenter_home.setBorder(new LineBorder(new Color(0, 0, 0)));
						ImageIcon img = new ImageIcon("icon/datVeTau.jpg");
						Image img1 = img.getImage();
						Image temp_img = img1.getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
						img = new ImageIcon(temp_img);
						JLabel lblBackground = new JLabel("", img, JLabel.CENTER);
						lblBackground.setLayout(new BoxLayout(lblBackground, BoxLayout.Y_AXIS));
						pCenter_home.add(lblBackground);
					}

					pCenter_home.revalidate();
					pCenter_home.repaint();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (obj == btnDatVe) {
			JFrame DatVeFrame = new JFrame("Thanh toán");
			DatVeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			DatVeFrame.setSize(1150, 700);
			DatVeFrame.setLayout(new BorderLayout(0, 0));
			DatVeFrame.setLocationRelativeTo(null);
			DatVeFrame.setVisible(true);

			// pNorth
			JPanel pNorth = new JPanel();
			DatVeFrame.add(pNorth, BorderLayout.NORTH);
			JLabel lblTieuDe = new JLabel("Xác nhận đặt vé và thanh toán");
			lblTieuDe.setFont(new Font("Times New Roman", Font.ITALIC, 24));
			lblTieuDe.setForeground(Color.blue);
			pNorth.add(lblTieuDe);

			/// pCenter
			JPanel pCenter = new JPanel(new BorderLayout());
			DatVeFrame.add(pCenter, BorderLayout.CENTER);

			// PNorthInCenter
			JPanel PNorthInCenter = new JPanel();
			pCenter.add(PNorthInCenter, BorderLayout.NORTH);

			String[] headerDi = { "STT", "Ngày Đi", "Ngày đến", "Mã Tàu", "số Toa", "Số ghế", "giá vé" };
			DefaultTableModel modelThanhToan = new DefaultTableModel(headerDi, 0);
			JTable table = new JTable(modelThanhToan);
			table.setBackground(Color.WHITE);
			JScrollPane Scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			Scroll.setPreferredSize(new Dimension(1100, table.getRowHeight() * 13));
			PNorthInCenter.add(Scroll);
			if (btn2Chieu.isSelected()) {
				int rowCountDi = modelKmDi.getRowCount();
				int rowCountVe = modelKmVe.getRowCount();
				int maxRowCount = Math.max(rowCountDi, rowCountVe);

				for (int i = 0; i < maxRowCount; i++) {
					Vector<Object> rowDataDi = new Vector<>();
					Vector<Object> rowDataVe = new Vector<>();

					if (i < rowCountDi) {
						rowDataDi.add(i + 1);
						for (int j = 1; j < modelKmDi.getColumnCount(); j++) {
							rowDataDi.add(modelKmDi.getValueAt(i, j));
						}
					}

					// Lấy dữ liệu từ bảng về
					if (i < rowCountVe) {
						rowDataVe.add(modelKmDi.getRowCount() + i + 1);
						for (int j = 1; j < modelKmVe.getColumnCount(); j++) {
							rowDataVe.add(modelKmVe.getValueAt(i, j));
						}
					}

					// Thêm dữ liệu từ bảng đi vào modelThanhToan
					if (!rowDataDi.isEmpty()) {
						modelThanhToan.addRow(rowDataDi);
					}

					// Thêm dữ liệu từ bảng về vào modelThanhToan
					if (!rowDataVe.isEmpty()) {
						modelThanhToan.addRow(rowDataVe);
					}
				}

			} else if (btn1Chieu.isSelected()) {
				for (int i = 0; i < modelKmDi.getRowCount(); i++) {
					Vector<Object> rowData = new Vector<>();
					rowData.add(i + 1);
					for (int j = 1; j < modelKmDi.getColumnCount(); j++) {
						rowData.add(modelKmDi.getValueAt(i, j));
					}
					modelThanhToan.addRow(rowData);
				}
			}
			// pCenterInCenter
			JPanel pCenterInCenter = new JPanel();
			pCenter.add(pCenterInCenter, BorderLayout.CENTER);

			// Box layout for form inputs
			Box b1 = Box.createHorizontalBox();
			Box b2 = Box.createHorizontalBox();
			Box b3 = Box.createHorizontalBox();
			Box b4 = Box.createHorizontalBox();

			JLabel lblTenKH, lblSDT, lblGioiTinh, lblNgaySinh, lblDiaChi, lblCCCD, lblLoaiKH;

			b1.add(lblGioiTinh = new JLabel("Giới Tính:"));
			b1.add(Box.createHorizontalStrut(20));
			JComboBox<String> cboGioiTinh = new JComboBox<String>();
			cboGioiTinh.addItem("Nam");
			cboGioiTinh.addItem("Nữ");
			b1.add(cboGioiTinh);
			b1.add(Box.createHorizontalGlue());

			b2.add(lblTenKH = new JLabel("Tên Khách Hàng:                                "));
			b2.add(Box.createHorizontalStrut(20));
			JTextField txtTenKH = new JTextField(30);
			b2.add(txtTenKH);
			b2.add(Box.createHorizontalStrut(20));
			JDateChooser dateChooser = new JDateChooser();
			b2.add(lblNgaySinh = new JLabel("Ngày Sinh:"));
			b2.add(Box.createHorizontalStrut(20));
			b2.add(dateChooser);
			b2.add(Box.createHorizontalGlue());

			b3.add(lblSDT = new JLabel("Số Điện Thoại:"));
			b3.add(Box.createHorizontalStrut(20));
			JTextField txtSDT = new JTextField(30);
			b3.add(txtSDT);
			b3.add(Box.createHorizontalStrut(20));
			b3.add(lblDiaChi = new JLabel("Địa Chỉ:"));
			b3.add(Box.createHorizontalStrut(20));
			JTextField txtDiaChi = new JTextField(30);
			b3.add(txtDiaChi);
			b3.add(Box.createHorizontalGlue());

			b4.add(lblCCCD = new JLabel("Căn Cước:"));
			b4.add(Box.createHorizontalStrut(20));
			JTextField txtCCCD = new JTextField(30);
			b4.add(txtCCCD);
			b4.add(Box.createHorizontalStrut(20));
			b4.add(lblLoaiKH = new JLabel("Loại KH:"));
			b4.add(Box.createHorizontalStrut(20));
			JTextField txtLoaiKH = new JTextField(30);
			b4.add(txtLoaiKH);
			b4.add(Box.createHorizontalGlue());

	
			int labelWidth = lblTenKH.getPreferredSize().width;
			lblGioiTinh.setPreferredSize(new Dimension(labelWidth, lblSDT.getPreferredSize().height));
			lblNgaySinh.setPreferredSize(new Dimension(labelWidth, lblSDT.getPreferredSize().height));
			lblSDT.setPreferredSize(new Dimension(labelWidth, lblSDT.getPreferredSize().height));
			lblDiaChi.setPreferredSize(new Dimension(labelWidth, lblDiaChi.getPreferredSize().height));
			lblCCCD.setPreferredSize(new Dimension(labelWidth, lblCCCD.getPreferredSize().height));
			lblLoaiKH.setPreferredSize(new Dimension(labelWidth, lblLoaiKH.getPreferredSize().height));

		
			Box boxTong = Box.createVerticalBox();
			boxTong.add(Box.createVerticalStrut(10));
			boxTong.add(b1);
			boxTong.add(Box.createVerticalStrut(10));
			boxTong.add(b2);
			boxTong.add(Box.createVerticalStrut(10));
			boxTong.add(b3);
			boxTong.add(Box.createVerticalStrut(10));
			boxTong.add(b4);
			boxTong.add(Box.createVerticalStrut(10));

			pCenterInCenter.add(boxTong);

			// PSouthInCenter
			JPanel PSouthInCenter = new JPanel();
			pCenter.add(PSouthInCenter, BorderLayout.SOUTH);
			PSouthInCenter.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
					"Thanh Toán", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));

			Box ba = Box.createHorizontalBox();
			Box bb = Box.createHorizontalBox();
			Box bc = Box.createHorizontalBox();
			Box bd = Box.createHorizontalBox();

			JLabel lblHinhThuc;
			ba.add(lblHinhThuc = new JLabel("Hãy chọn hình thức thanh toán:"));
			ba.add(Box.createHorizontalStrut(100));
			JComboBox<String> cboHinhThuc = new JComboBox<String>();
			cboHinhThuc.addItem("1. Tiền mặt");
			cboHinhThuc.addItem("2. Thanh toán qua thẻ");
			cboHinhThuc.addItem("3. Thanh toán VNpay");
			cboHinhThuc.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String selectedOption = (String) cboHinhThuc.getSelectedItem();
					if (selectedOption.equals("2. Thanh toán qua thẻ")
							|| selectedOption.equals("3. Thanh toán VNpay")) {
						JOptionPane.showMessageDialog(null, "Hệ thống đang bảo trì\n xin lỗi vì sự bất tiện!");
					}
				}
			});
			ba.add(cboHinhThuc);
			ba.add(Box.createHorizontalGlue());

			JLabel lblMaKM;
			bb.add(lblMaKM = new JLabel("Nhập mã khuyến mãi:"));
			bb.add(Box.createHorizontalStrut(100));
			JTextField txtKM = new JTextField(30);
			bb.add(txtKM);
			bb.add(lblCTKM = new JLabel(""));
			lblCTKM.setForeground(Color.red);
			bb.add(Box.createHorizontalGlue());

			JLabel lblTongTien;
			bc.add(lblTongTien = new JLabel("Tổng tiền vé:"));
			bc.add(Box.createHorizontalStrut(100));
			JTextField txtTong = new JTextField(30);
			bc.add(txtTong);
			double tongTienVe = 0.0;
			for (int i = 0; i < modelThanhToan.getRowCount(); i++) {
				double giaVe = Double.parseDouble(modelThanhToan.getValueAt(i, 6).toString());
				tongTienVe += giaVe;
			}
			txtTong.setText(String.valueOf(tongTienVe));
			txtTong.setForeground(Color.RED);
			bc.add(Box.createHorizontalGlue());

			// giảm giá
			txtKM.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String maKhuyenMai = txtKM.getText();
					double tongTienVe = 0.0;
					for (int i = 0; i < modelThanhToan.getRowCount(); i++) {
						double giaVe = Double.parseDouble(modelThanhToan.getValueAt(i, 6).toString());
						tongTienVe += giaVe;
					}
					double totalAmount = tongTienVe;
					switch (maKhuyenMai) {
					case "IUH":
						totalAmount *= 0.8; // Giảm giá 20%
						lblCTKM.setText("Giảm giá 20%");
						break;
					case "TauHoa25%":
						totalAmount *= 0.75; // Giảm giá 25%
						lblCTKM.setText("Giảm giá 25%");
						break;
					case "DiCungSE":
						totalAmount *= 0.9; // Giảm giá 10%
						lblCTKM.setText("Giảm giá 10%");
						break;
					default:
						lblCTKM.setText("Không có khuyến mãi");
						JOptionPane.showConfirmDialog(null, "Không có mã như vậy", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
						break;
					}

					txtTong.setText(String.valueOf(totalAmount));
				}
			});

			JButton btnThanhToan;
			bd.add(btnThanhToan = new JButton("Thanh Toán"));
			bd.add(Box.createHorizontalGlue());
			btnThanhToan.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "Xác nhận", "Hủy" };
					int chon = JOptionPane.showOptionDialog(null, "Bạn Xác nhận thanh toán và đặt vé?", "Đặt vé",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

					if (chon == 0) {
						try {
							// Tạo mã khách hàng mới
							String maKH = dao_kh.generateNewMaKH();
							String tenKH = txtTenKH.getText();
							String gioiTinh = (String) cboGioiTinh.getSelectedItem();
							String sdt = txtSDT.getText();
							String cccd = txtCCCD.getText();
							String diachi = txtDiaChi.getText();
							int loaikh = Integer.parseInt(txtLoaiKH.getText());
							java.util.Date NgaySinh_until = dateChooser.getDate();
							java.sql.Date ngaySinh = new java.sql.Date(NgaySinh_until.getTime());

							KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, sdt, cccd, diachi, loaikh, ngaySinh);
							dao_kh.ThemKhachHang(kh);

							// Tạo hóa đơn mới
							String maHD = dao_hoadon.generateNewMaHD();
							String nhanVienDangLapHoaDon = QuanLyDatVe.this.maNV;
							LocalDate localDate = LocalDate.now();
							Date ngayLapHoaDon = Date.valueOf(localDate);
							Float TongTienHD = Float.parseFloat(txtTong.getText());
							dao_hoadon.TaoHoaDonMoi(maHD, nhanVienDangLapHoaDon, kh.getMaKh(), ngayLapHoaDon,
									TongTienHD);

							// Lưu vé tàu đã đặt xuống database (dùng để thống kê và kiểm tra chỗ đặt)
							String noiDi = ((String) txtDi.getSelectedItem()).trim();
							String noiDen = ((String) txtDen.getSelectedItem()).trim();

							if (btn1Chieu.isSelected()) {
								for (int i = 0; i < modelThanhToan.getRowCount(); i++) {
									String maTau = modelThanhToan.getValueAt(i, 3).toString();
									String soToaTrenBang = modelThanhToan.getValueAt(i, 4).toString();
									String soGheTrenBang = modelThanhToan.getValueAt(i, 5).toString();
									java.util.Date ngayDi_util = txtNgayDi.getDate();
									java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());

									String malichtrinh = dao_lichTrinh.laymaLichTrinh(maTau, noiDi, noiDen, ngayDi);
									System.out.println(malichtrinh);
									

									// Chuyển đổi o
									String soToa = String.format("TOA%02d", Integer.parseInt(soToaTrenBang));
									String soGhe = String.format("GHE%02d", Integer.parseInt(soGheTrenBang));
									
									// Tạo vé cho mỗi dòng
									String maVe = dao_ve.generateNewmaVE();
									dao_ve.TaoVeChoKhach(maVe, malichtrinh, soGhe, soToa, maTau);

									// Tạo CT hóa đơn cho mỗi dòng
									dao_CTHoaDon.TaoCTHoaDonMoi(maHD, maVe);

									
									// Thêm trạng thái ghế
									dao_ghe.chinhsuaTrangThaiDatGhe(soGhe, soToa, maTau, malichtrinh);
								}
							} else if (btn2Chieu.isSelected()) {
								for (int i = 0; i < modelThanhToan.getRowCount(); i++) {
									if (i % 2 == 0) {
										String maTau = modelThanhToan.getValueAt(i, 3).toString();
										String soToaTrenBang = modelThanhToan.getValueAt(i, 4).toString();
										String soGheTrenBang = modelThanhToan.getValueAt(i, 5).toString();
										java.util.Date ngayDi_util = txtNgayDi.getDate();
										java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());

										String malichtrinh = dao_lichTrinh.laymaLichTrinh(maTau, noiDi, noiDen, ngayDi);
										System.out.println(malichtrinh);
										

										// Chuyển đổi o
										String soToa = String.format("TOA%02d", Integer.parseInt(soToaTrenBang));
										String soGhe = String.format("GHE%02d", Integer.parseInt(soGheTrenBang));
										
										// Tạo vé cho mỗi dòng
										String maVe = dao_ve.generateNewmaVE();
										dao_ve.TaoVeChoKhach(maVe, malichtrinh, soGhe, soToa, maTau);

										// Tạo CT hóa đơn cho mỗi dòng
										dao_CTHoaDon.TaoCTHoaDonMoi(maHD, maVe);

										
										System.out.println(malichtrinh);
										// Thêm trạng thái ghế
										dao_ghe.chinhsuaTrangThaiDatGhe(soGhe, soToa, maTau, malichtrinh);
									} else {
										String maTau = modelThanhToan.getValueAt(i, 3).toString();
										String soToaTrenBang = modelThanhToan.getValueAt(i, 4).toString();
										String soGheTrenBang = modelThanhToan.getValueAt(i, 5).toString();
										java.util.Date ngayDi_util = txtNgayVe.getDate();
										java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());

										String malichtrinh = dao_lichTrinh.laymaLichTrinh(maTau, noiDen, noiDi, ngayDi);
										
										System.out.println(malichtrinh);
										

										// Chuyển đổi o
										String soToa = String.format("TOA%02d", Integer.parseInt(soToaTrenBang));
										String soGhe = String.format("GHE%02d", Integer.parseInt(soGheTrenBang));
										
										// Tạo vé cho mỗi dòng
										String maVe = dao_ve.generateNewmaVE();
										dao_ve.TaoVeChoKhach(maVe, malichtrinh, soGhe, soToa, maTau);

										// Tạo CT hóa đơn cho mỗi dòng
										dao_CTHoaDon.TaoCTHoaDonMoi(maHD, maVe);

										
										// Thêm trạng thái ghế
										dao_ghe.chinhsuaTrangThaiDatGhe(soGhe, soToa, maTau, malichtrinh);
									}
								}
							}

							JOptionPane.showMessageDialog(null, "Đặt vé và thanh toán thành công!");
							DatVeFrame.dispose(); // Đóng DatVeFrame

							// trở về màn hình ban đầu
							pCenter_home.removeAll();
							resetPcenter();

						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Lỗi khi đặt vé và thanh toán!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});

			lblHinhThuc.setPreferredSize(new Dimension(labelWidth, lblHinhThuc.getPreferredSize().height));
			lblTongTien.setPreferredSize(new Dimension(labelWidth, lblHinhThuc.getPreferredSize().height));
			lblMaKM.setPreferredSize(new Dimension(labelWidth, lblTongTien.getPreferredSize().height));

			Box boxTonga = Box.createVerticalBox();
			boxTonga.add(Box.createVerticalStrut(5));
			boxTonga.add(ba);
			boxTonga.add(Box.createVerticalStrut(5));
			boxTonga.add(bb);
			boxTonga.add(Box.createVerticalStrut(5));
			boxTonga.add(bc);
			boxTonga.add(Box.createVerticalStrut(5));
			boxTonga.add(bd);
			boxTonga.add(Box.createVerticalStrut(5));

			PSouthInCenter.add(boxTonga);
		}

		if (btn2Chieu.isSelected()) {
			// Button= chọn tàu
			JButton button = (JButton) e.getSource();
			JPanel chieuTau = null;
			for (int i = 0; i < tauDi.getComponentCount(); i++) {
				if (tauDi.getComponent(i).equals(button) && validChonTau(tauDi, modelKmDi)) {
					chieuTau = tauDi;
					tauDi.getComponent(i).setBackground(new Color(153, 255, 153));
					soTauDi = button.getText(); // Get soTau
					modelKmDi.setRowCount(0); // Xóa hết dòng trong table
					deleteAllGheChon(khoangDi); // Bỏ chọn nút bên đi nếu có
					txtTGDi.setText(DateFormat.getDateTimeInstance().format(txtNgayDi.getDate()));
					String soTau = soTauDi;
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					txtTGDen.setText(getTGden(soTau, noiDi, noiDen));

				}
			}
			for (int i = 0; i < tauVe.getComponentCount(); i++) {
				if (tauVe.getComponent(i).equals(button) && validChonTau(tauVe, modelKmVe)) {
					chieuTau = tauVe;
					tauVe.getComponent(i).setBackground(new Color(153, 255, 153));
					soTauVe = soTauDi = button.getText();
					modelKmVe.setRowCount(0);
					deleteAllGheChon(khoangVe);
					txtTGDiVe.setText(DateFormat.getDateTimeInstance().format(txtNgayVe.getDate()));
					String soTau = soTauVe;
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					txtTGDenVe.setText(getTGden(soTau, noiDen, noiDi));
				}
			}
			if (chieuTau != null) {
				for (int i = 0; i < chieuTau.getComponentCount(); i++) {
					if (!chieuTau.getComponent(i).equals(button))
						chieuTau.getComponent(i).setBackground(Color.white);
				}
			}

			// Button= chọn toa
			boolean chon = false;
			JButton[] chieuToa;
			if (toaTauDi.length >= toaTauVe.length) {
				chieuToa = toaTauDi;
			} else {
				chieuToa = toaTauVe;
			}

			for (JButton i : toaTauDi) {
				
			
				if (i.equals(button)) {
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					String maLichTrinh = null;
				
					java.util.Date ngayDi_util = txtNgayDi.getDate();
					java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());
					try {
						maLichTrinh = dao_lichTrinh.laymaLichTrinh(soTauDi, noiDi, noiDen, ngayDi);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(maLichTrinh);
					
					String maToa = "TOA" + String.format("%02d", soToaDi);
					updateGheUI(soTauDi, maToa, maLichTrinh, khoangDi, i);
					if (soTauDi == null) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu đi!", "Lỗi!", JOptionPane.ERROR_MESSAGE,
								null);
						return;
					}
					chon = true;
					chieuToa = toaTauDi;
					soToaDi = Integer.valueOf(((JLabel) button.getComponent(1)).getText());
					txtToaDi.setText(" Toa số " + soToaDi + ": Giường nằm ");
					i.setBackground(new Color(185, 202, 240));
					updateToaChon(modelKmDi, khoangDi);

				

				}
			}

			for (JButton i : toaTauVe) {
				if (i.equals(button)) {
					String maToa = "TOA" + String.format("%02d", soToaVe);
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					String maLichTrinh = null;
					java.util.Date ngayVe_util = txtNgayVe.getDate();
					java.sql.Date ngayVe = new java.sql.Date(ngayVe_util.getTime());
					
					
					try {
						maLichTrinh = dao_lichTrinh.laymaLichTrinh(soTauVe, noiDen, noiDi, ngayVe);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println(maLichTrinh);
					updateGheUI(soTauVe, maToa, maLichTrinh, khoangVe, i);
					if (soTauVe == null) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu về!", "Lỗi!", JOptionPane.ERROR_MESSAGE,
								null);
						return;
					}
					chon = true;
					chieuToa = toaTauVe;
					soToaVe = Integer.valueOf(((JLabel) button.getComponent(1)).getText());
					txtToaVe.setText(" Toa số " + (soToaVe) + ": Giường nằm ");
					i.setBackground(new Color(185, 202, 240));
					updateToaChon(modelKmVe, khoangVe);

					// Cập nhật tình trạng ghế cho toa đã chọn


				}
			}
			for (JButton i : chieuToa) // Reset các nút không được chọn
			{
				if (!i.equals(button) && chon)
					i.setBackground(Color.white);
			}
		} else if (btn1Chieu.isSelected()) {
			// Button= chọn tàu
			JButton button = (JButton) e.getSource();
			JPanel chieuTau = null;
			for (int i = 0; i < tauDi.getComponentCount(); i++) {
				if (tauDi.getComponent(i).equals(button) && validChonTau(tauDi, modelKmDi)) {
					chieuTau = tauDi;
					tauDi.getComponent(i).setBackground(new Color(153, 255, 153));
					soTauDi = button.getText(); // Get soTau
					modelKmDi.setRowCount(0); // Xóa hết dòng trong table
					deleteAllGheChon(khoangDi); // Bỏ chọn nút bên đi nếu có
					txtTGDi.setText(DateFormat.getDateTimeInstance().format(txtNgayDi.getDate()));
					String soTau = soTauDi;
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					txtTGDen.setText(getTGden(soTau, noiDi, noiDen));

				}
			}
			if (chieuTau != null) {
				for (int i = 0; i < chieuTau.getComponentCount(); i++) {
					if (!chieuTau.getComponent(i).equals(button))
						chieuTau.getComponent(i).setBackground(Color.white);
				}
			}

			// Button= chọn toa
			boolean chon = false;
			JButton[] chieuToa;
			chieuToa = toaTauDi;
			for (JButton i : toaTauDi) {
				if (i.equals(button)) {
					String noiDi = ((String) txtDi.getSelectedItem());
					String noiDen = ((String) txtDen.getSelectedItem());
					String maLichTrinh = null;
					java.util.Date ngayDi_util = txtNgayDi.getDate();
					java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());
					try {
						maLichTrinh = dao_lichTrinh.laymaLichTrinh(soTauDi, noiDi, noiDen, ngayDi);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String maToa = "TOA" + String.format("%02d", soToaDi);
					updateGheUI(soTauDi, maToa, maLichTrinh, khoangDi, i);
					if (soTauDi == null) {
						JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu đi!", "Lỗi!", JOptionPane.ERROR_MESSAGE,
								null);
						return;
					}
					chon = true;
					chieuToa = toaTauDi;
					soToaDi = Integer.valueOf(((JLabel) button.getComponent(1)).getText());
					txtToaDi.setText(" Toa số " + soToaDi + ": Giường nằm ");
					i.setBackground(new Color(185, 202, 240));
					updateToaChon(modelKmDi, khoangDi);


				}
			}
			for (JButton i : chieuToa) // Reset các nút không được chọn
			{
				if (!i.equals(button) && chon)
					i.setBackground(Color.white);
			}
		}
	}

	private String getTGden(String maTau, String gaDi, String gaDen) {
		// TODO Auto-generated method stub
		String tgDen = null;
		try {
			tgDen = dao_lichTrinh.layTGDen(maTau, gaDi, gaDen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tgDen;
	}

	public JPanel Controler_TimTau() throws SQLException {
		JPanel pCenter = new JPanel();
		pCenter.removeAll();

		String gaDi = txtDi.getSelectedItem().toString().trim();
		String gaDen = txtDen.getSelectedItem().toString().trim();
		java.util.Date ngayDi_util = txtNgayDi.getDate();
		java.sql.Date ngayDi = new java.sql.Date(ngayDi_util.getTime());

		if (btn2Chieu.isSelected()) {
			java.util.Date ngayVe_util = txtNgayVe.getDate();
			java.sql.Date ngayVe = new java.sql.Date(ngayVe_util.getTime());

			String[] veKhuhoi = null;
			String[] veMotChieu = null;

			try {
				veKhuhoi = dao_lichTrinh.timKiemLichTrinh(gaDen, gaDi, ngayVe);
				veMotChieu = dao_lichTrinh.timKiemLichTrinh(gaDi, gaDen, ngayDi);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi truy vấn cơ sở dữ liệu.");
				return null;
			}

			if ((veKhuhoi == null || veKhuhoi.length == 0)) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy chuyến tàu nào, có thể đã sai, hãy kiểm tra lại.");
				return null;
			} else {
				pCenter.removeAll();
				JOptionPane.showMessageDialog(this, "Đã tìm thấy chuyến tàu khứ hồi dành cho bạn!");
				createRoundTripUI(pCenter, veMotChieu, veKhuhoi);
			}

		} else if (btn1Chieu.isSelected()) {
			String[] veMotChieu = null;

			try {
				veMotChieu = dao_lichTrinh.timKiemLichTrinh(gaDi, gaDen, ngayDi);
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi truy vấn cơ sở dữ liệu.");
				return null;
			}

			if (veMotChieu == null || veMotChieu.length == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy chuyến tàu nào, có thể đã sai, hãy kiểm tra lại.");
				return null;
			} else {
				pCenter.removeAll();
				JOptionPane.showMessageDialog(this, "Đã tìm thấy chuyến tàu 1 chiều dành cho bạn!");
				createOneWayUI(pCenter, veMotChieu);
			}
		}

		return pCenter;
	}

	private void createOneWayUI(JPanel pCenter, String[] veMotChieu) {

		pCenter.removeAll();
		// pLeft
		JPanel pLeft = new JPanel();
		pCenter.add(pLeft, BorderLayout.CENTER);
		pLeft.setLayout(new BorderLayout(0, 0));

		Box bL = Box.createVerticalBox();
		pCenter.add(bL);
		Box bL0, bL01, bL02, bL03, bL04;
		Box bL1, bL2, bL3, bL4;

		bL.add(bL0 = Box.createHorizontalBox());
		JLabel lblChieuDi;
		bL0.add(lblChieuDi = new JLabel("Chiều đi:"));
		lblChieuDi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChieuDi.setForeground(Color.BLUE);

		bL.add(bL01 = Box.createHorizontalBox());
		bL01.add(tauDi = new JPanel(new GridLayout(1, 0)));
		tauDi.setBackground(Color.WHITE);
		for (int i = 0; i < veMotChieu.length; i++) {
			JButton btnTau = createTauButton(veMotChieu[i]);
			btnTau.add(new JLabel(veMotChieu[i], SwingConstants.CENTER));
			tauDi.add(btnTau);
		}

		JScrollPane tauScroll = new JScrollPane(tauDi, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tauScroll.setPreferredSize(new Dimension(100, 130));
		bL01.add(tauScroll, BorderLayout.CENTER);

		bL.add(bL02 = Box.createVerticalBox());
		bL02.add(Box.createVerticalStrut(15));

		bL02.add(bL03 = Box.createHorizontalBox());
		JLabel lblTGDi;
		bL03.add(lblTGDi = new JLabel("TG đi: "));
		bL03.add(txtTGDi = new JTextField(5));
		bL02.add(Box.createVerticalStrut(5));
		JLabel lblTGDen;
		bL03.add(lblTGDen = new JLabel("TG đến: "));
		bL03.add(txtTGDen = new JTextField(5));
		bL02.add(Box.createVerticalStrut(5));

		bL02.add(bL04 = Box.createHorizontalBox());
		JLabel lblSLDat;

		lblTGDi.setPreferredSize(txtTGDi.getPreferredSize());
		lblTGDen.setPreferredSize(txtTGDi.getPreferredSize());

		bL.add(Box.createVerticalStrut(15));
		bL.add(bL1 = Box.createHorizontalBox());
		bL1.setPreferredSize(new Dimension(30, 35));
		bL1.add(chonToa = new JPanel(new GridLayout(1, 0)));
		chonToa.setBackground(Color.WHITE);
		toaTauDi = addToa(12, chonToa);
		// toaTauDi[0] //get nút JToggleButton ứng với toa số 1

		bL.add(Box.createVerticalStrut(15));
		bL.add(bL2 = Box.createHorizontalBox());
		bL2.add(Box.createHorizontalStrut(40));
		bL2.add(txtToaDi = new JTextField(10));
		txtToaDi.disable();
		txtToaDi.setText("Chọn một toa");
		txtToaDi.setFont(new Font("Tahoma", Font.BOLD, 14));
		bL2.add(Box.createHorizontalStrut(40));

		bL.add(Box.createVerticalStrut(10));
		bL.add(bL3 = Box.createHorizontalBox());
		bL3.add(btnBackdi = new JButton("<"));
		bL3.add(Box.createHorizontalStrut(5));
		khoangDi = addKhoang(4, bL3); //
		bL3.add(Box.createHorizontalStrut(5));
		bL3.add(btnNextdi = new JButton(">"));

		bL.add(Box.createVerticalStrut(20));
		bL.add(bL4 = Box.createHorizontalBox());
		JLabel lblKmDi;
		bL4.add(lblKmDi = new JLabel("Chi tiết vé cho chiều đi"));
		lblKmDi.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblKmDi.setForeground(Color.BLUE);
		String[] headerDi = { "STT", "Ngày Đi", "Ngày Đến", "Mã Tàu", " số Toa", "Số ghế", "giá vé" };
		modelKmDi = new DefaultTableModel(headerDi, 0);
		tableKmDi = new JTable(modelKmDi);
		tableKmDi.setBackground(Color.WHITE);
		JScrollPane Scroll = new JScrollPane(tableKmDi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Scroll.setPreferredSize(new Dimension(1100, tableKmDi.getRowHeight() * 13));
		bL.add(Scroll);
	}

	private void createRoundTripUI(JPanel pCenter, String[] veMotChieu, String[] veKhuhoi) {

		pCenter.removeAll();
		// pLeft
		JPanel pLeft = new JPanel();
		pCenter.add(pLeft, BorderLayout.CENTER);
		pLeft.setLayout(new BorderLayout(0, 0));

		Box bL = Box.createVerticalBox();
		pCenter.add(bL);
		Box bL0, bL01, bL02, bL03, bL04;
		Box bL1, bL2, bL3, bL4;

		bL.add(bL0 = Box.createHorizontalBox());
		JLabel lblChieuDi;
		bL0.add(lblChieuDi = new JLabel("Chiều đi:"));
		lblChieuDi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChieuDi.setForeground(Color.BLUE);

		bL.add(bL01 = Box.createHorizontalBox());
		bL01.add(tauDi = new JPanel(new GridLayout(1, 0)));
		tauDi.setBackground(Color.WHITE);
		for (int i = 0; i < veMotChieu.length; i++) {
			JButton btnTau = createTauButton(veMotChieu[i]);
			btnTau.add(new JLabel(veMotChieu[i], SwingConstants.CENTER));
			tauDi.add(btnTau);
		}

		JScrollPane tauScroll = new JScrollPane(tauDi, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tauScroll.setPreferredSize(new Dimension(100, 130));
		bL01.add(tauScroll, BorderLayout.CENTER);

		bL.add(bL02 = Box.createVerticalBox());
		bL02.add(Box.createVerticalStrut(15));

		bL02.add(bL03 = Box.createHorizontalBox());
		JLabel lblTGDi;
		bL03.add(lblTGDi = new JLabel("TG đi: "));
		bL03.add(txtTGDi = new JTextField(5));
		bL02.add(Box.createVerticalStrut(5));
		JLabel lblTGDen;
		bL03.add(lblTGDen = new JLabel("TG đến: "));
		bL03.add(txtTGDen = new JTextField(5));
		bL02.add(Box.createVerticalStrut(5));

		bL02.add(bL04 = Box.createHorizontalBox());
		JLabel lblSLDat;

		lblTGDi.setPreferredSize(txtTGDi.getPreferredSize());
		lblTGDen.setPreferredSize(txtTGDi.getPreferredSize());

		bL.add(Box.createVerticalStrut(15));
		bL.add(bL1 = Box.createHorizontalBox());
		bL1.setPreferredSize(new Dimension(30, 35));
		bL1.add(chonToa = new JPanel(new GridLayout(1, 0)));
		chonToa.setBackground(Color.WHITE);
		toaTauDi = addToa(12, chonToa);
		// toaTauDi[0] //get nút JToggleButton ứng với toa số 1

		bL.add(Box.createVerticalStrut(15));
		bL.add(bL2 = Box.createHorizontalBox());
		bL2.add(Box.createHorizontalStrut(40));
		bL2.add(txtToaDi = new JTextField(10));
		txtToaDi.disable();
		txtToaDi.setText("Chọn một toa");
		txtToaDi.setFont(new Font("Tahoma", Font.BOLD, 14));
		bL2.add(Box.createHorizontalStrut(40));

		bL.add(Box.createVerticalStrut(10));
		bL.add(bL3 = Box.createHorizontalBox());
		bL3.add(btnBackdi = new JButton("<"));
		bL3.add(Box.createHorizontalStrut(5));
		khoangDi = addKhoang(4, bL3); //
		bL3.add(Box.createHorizontalStrut(5));
		bL3.add(btnNextdi = new JButton(">"));

		bL.add(Box.createVerticalStrut(20));
		bL.add(bL4 = Box.createHorizontalBox());
		JLabel lblKmDi;
		bL4.add(lblKmDi = new JLabel("Chi tiết vé cho chiều đi"));
		lblKmDi.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblKmDi.setForeground(Color.BLUE);
		String[] headerDi = { "STT", "Ngày Đi", "Ngày Đến", "Mã Tàu", " số Toa", "Số ghế", "giá vé" };
		modelKmDi = new DefaultTableModel(headerDi, 0);
		tableKmDi = new JTable(modelKmDi);
		tableKmDi.setBackground(Color.WHITE);
		JScrollPane Scroll = new JScrollPane(tableKmDi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Scroll.setPreferredSize(new Dimension(500, tableKmDi.getRowHeight() * 13));
		bL.add(Scroll);

		// pRight
		JPanel pRight = new JPanel();
		pCenter.add(pRight, BorderLayout.EAST);
		pRight.setLayout(new BorderLayout(0, 0));

		Box bR = Box.createVerticalBox();
		pCenter.add(bR);
		Box bR0, bR1, bR2, bR3, bR4;
		Box bR5, bR6, bR7, bR8;

		bR.add(bR0 = Box.createHorizontalBox());
		JLabel lblChieuVe;
		bR0.add(lblChieuVe = new JLabel("Chiều về:"));
		lblChieuVe.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChieuVe.setForeground(Color.BLUE);

		bR.add(bR1 = Box.createHorizontalBox());
		bR1.add(tauVe = new JPanel(new GridLayout(1, 0)));
		tauVe.setBackground(Color.WHITE);

		// Tạo JButton cho mỗi tàu và thêm vào mảng
		for (int i = 0; i < veKhuhoi.length; i++) {
			JButton btnTau = createTauButton(veKhuhoi[i]);
			btnTau.add(new JLabel(veKhuhoi[i], SwingConstants.CENTER));
			tauVe.add(btnTau);
		}

		JScrollPane tauVeScroll = new JScrollPane(tauVe, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tauVeScroll.setPreferredSize(new Dimension(100, 130));
		bR1.add(tauVeScroll, BorderLayout.CENTER);

		bR.add(bR2 = Box.createVerticalBox());
		bR2.add(Box.createVerticalStrut(15));

		bR2.add(bR3 = Box.createHorizontalBox());
		JLabel lblTGDiVe;
		bR3.add(lblTGDiVe = new JLabel("TG đi: "));
		bR3.add(txtTGDiVe = new JTextField(5));
		bR2.add(Box.createVerticalStrut(5));
		JLabel lblTGDenVe;
		bR3.add(lblTGDenVe = new JLabel("TG đến: "));
		bR3.add(txtTGDenVe = new JTextField(5));
		bR2.add(Box.createVerticalStrut(5));

		bR2.add(bR4 = Box.createHorizontalBox());
		JLabel lblSLDatVe;
		bR2.add(Box.createVerticalStrut(5));
		lblTGDiVe.setPreferredSize(txtTGDiVe.getPreferredSize());
		lblTGDenVe.setPreferredSize(txtTGDiVe.getPreferredSize());

		bR.add(Box.createVerticalStrut(15));
		bR.add(bR5 = Box.createHorizontalBox());
		bR5.setPreferredSize(new Dimension(30, 35));
		bR5.add(chonToaVe = new JPanel(new GridLayout(1, 0)));
		chonToa.setBackground(Color.WHITE);
		toaTauVe = addToa(12, chonToaVe);
		// tau1[0] //get nút JButton ứng với toa số 1

		bR.add(Box.createVerticalStrut(15));
		bR.add(bR6 = Box.createHorizontalBox());
		bR6.add(Box.createHorizontalStrut(40));
		bR6.add(txtToaVe = new JTextField(10));
		txtToaVe.disable();
		txtToaVe.setText(" Chọn 1 toa");
		txtToaVe.setFont(new Font("Tahoma", Font.BOLD, 14));
		bR6.add(Box.createHorizontalStrut(40));

		bR.add(Box.createVerticalStrut(10));
		bR.add(bR7 = Box.createHorizontalBox());
		bR7.add(btnBackVe = new JButton("<"));
		bR7.add(Box.createHorizontalStrut(5));
		khoangVe = addKhoang(4, bR7);
		// khoangVe[0].getComponent(0); //get nút JButton ứng với ghế thứ 1 của khoang
		// thứ 1
		bR7.add(Box.createHorizontalStrut(5));
		bR7.add(btnNextVe = new JButton(">"));

		bR.add(Box.createVerticalStrut(20));
		bR.add(bR8 = Box.createHorizontalBox());
		JLabel lblKmVe;
		bR8.add(lblKmVe = new JLabel("chi tiết đặt vé cho chiều về"));
		lblKmVe.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblKmVe.setForeground(Color.BLUE);
		String[] header = { "STT", "Ngày Đi", "Ngày Đến", "Mã Tàu", " số Toa", "Số ghế", "giá vé" };
		modelKmVe = new DefaultTableModel(header, 0);
		tableKmVe = new JTable(modelKmVe);
		tableKmVe.setBackground(Color.WHITE);
		JScrollPane Scrollve = new JScrollPane(tableKmVe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Scrollve.setPreferredSize(new Dimension(510, tableKmVe.getRowHeight() * 13));
		bR.add(Scrollve);

		JScrollPane pCenterScroll = new JScrollPane(pCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pCenterScroll.setPreferredSize(new Dimension(1100, 650));
		add(pCenterScroll, BorderLayout.CENTER);
		pRight.setBorder(new EmptyBorder(0, 15, 0, 10));

	}

	//// Xử lý
	public JButton createTauButton(String maTau) { //// Tàu
		JButton btnTau = new JButton(maTau);
		btnTau.setBackground(Color.WHITE);
		JLabel img;
		btnTau.setLayout(new BorderLayout());
		btnTau.add(img = new JLabel(Resize("icon/tau.png", 75, 75)), BorderLayout.NORTH);
		btnTau.addActionListener(this);
		return btnTau;
	}

	public JButton addToa(String label) { //// Toa
		JButton btnToa = new JButton();
		btnToa.setBackground(Color.WHITE);
		JLabel img, name;
		btnToa.setLayout(new BorderLayout());
		btnToa.add(img = new JLabel(Resize("icon/toa.jpg", 30, 20)), BorderLayout.NORTH);
		btnToa.add(name = new JLabel(label, SwingConstants.CENTER));
		btnToa.setPreferredSize(new Dimension(30, 30));
		btnToa.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnToa.addActionListener(this);
		return btnToa;
	}

	public JButton addDauTau() {
		JButton btnDau = new JButton();
		btnDau.setBackground(Color.WHITE);
		JLabel img;
		btnDau.setLayout(new BorderLayout());
		btnDau.add(img = new JLabel(Resize("icon/dau.jpg", 30, 20)), BorderLayout.NORTH);
		btnDau.setPreferredSize(new Dimension(30, 30));
		btnDau.setBorder(new EmptyBorder(0, 0, 0, 0));
		return btnDau;
	}

	public JButton[] addToa(int soToa, JPanel panel) {
		JButton[] toa = new JButton[soToa + 1];
		for (int i = 0; i < soToa; i++) {
			toa[i] = new JButton();
			toa[i] = addToa(Integer.toString(i + 1));
			panel.add(toa[i]);
			if ((i + 1) == soToa) {
				toa[i + 1] = new JButton();
				toa[i + 1] = addDauTau();
				toa[i + 1].setEnabled(false);
				panel.add(toa[i + 1]);
			}
		}
		return toa;
	}

	public JToggleButton addGhe(String label) { //// Ghế
		JToggleButton btnGhe = new JToggleButton();
		JLabel name;
		btnGhe.setLayout(new BorderLayout());
		btnGhe.add(name = new JLabel(label, SwingConstants.CENTER));
		btnGhe.setPreferredSize(new Dimension(20, 30));
		btnGhe.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnGhe.addItemListener(this);
		return btnGhe;
	}

	public JPanel[] addKhoang(int soGhe, Box box) {
		JPanel khoang[] = new JPanel[7];
		int sttGhe = 00;
		for (int i = 0; i < 7; i++) {
			khoang[i] = new JPanel(new GridLayout(0, 2, 4, 4));
			khoang[i].setBorder(new EmptyBorder(5, 6, 5, 6));
			if ((i % 2) == 0)
				khoang[i].setBackground(new Color(162, 185, 219));
			else
				khoang[i].setBackground(new Color(140, 222, 190));
			for (int k = 0; k < soGhe; k++) {
				sttGhe++;
				khoang[i].add(addGhe(Integer.toString(sttGhe)));
			}
			box.add(khoang[i]);
		}
		return khoang;
	}

	public int[] getAllGheChon(JPanel dsKhoang[]) { //// Xử lý Ghế
		@SuppressWarnings("deprecation")
		int soGheMoiKhoang = dsKhoang[0].countComponents();
		int dsGheChon[] = new int[soGheMoiKhoang * 7]; // Mỗi tàu có 7 khoang
		JToggleButton btnGhe = new JToggleButton();
		int sttGhe = 0;
		int i = 0;
		for (int soKhoang = 0; soKhoang < 7; soKhoang++) {
			for (int soGhe = 0; soGhe < soGheMoiKhoang; soGhe++) {
				sttGhe++;
				btnGhe = (JToggleButton) dsKhoang[soKhoang].getComponent(soGhe);
				if (btnGhe.isSelected()) {
					dsGheChon[i] = sttGhe;
					i++;
				}
			}
		}
		return dsGheChon;
	}

	public void themVeChonChoChieuDi(DefaultTableModel tableModel, JPanel khoang[]) {

		if (!validChonToa(khoang))
			return;

		int AllGheChon[] = getAllGheChon(khoang);
		int stt, sttToa;
		String soTau;
		sttToa = sttToaDangChon(khoang);

		soTau = soTauDi;
		String ngayDi = DateFormat.getDateInstance().format(txtNgayDi.getDate());
		String ngayDen = txtTGDen.getText();
		String giaVe = null;
		try {
			giaVe = dao_lichTrinh.layGiaLichTrinh(soTau);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int soGhe : AllGheChon) // lặp lại AllGheChonDi.length lần
		{
			stt = tableModel.getRowCount() + 1;
			if (soGhe != 0 && !contains(tableModel, soGhe, sttToa)) // if(soGhe được chọn && tableModel không chứa
																	// soGhe)
				tableModel.addRow(new Object[] { stt, ngayDi, ngayDen, soTau, sttToa, soGhe, giaVe });
		}
	}

	public void themVeChonChoChieuVe(DefaultTableModel tableModel, JPanel khoang[]) {
		if (!validChonToa(khoang))
			return;
		int AllGheChon[] = getAllGheChon(khoang);
		int stt, sttToa;
		String soTau;
		sttToa = sttToaDangChon(khoang);
		soTau = soTauVe;
		String ngayDi = DateFormat.getDateInstance().format(txtNgayVe.getDate());
		String ngayDen = txtTGDenVe.getText();

		String noiDi = ((String) txtDi.getSelectedItem()).trim();
		String noiDen = ((String) txtDen.getSelectedItem()).trim();
		String giaVe = null;
		try {
			giaVe = dao_lichTrinh.layGiaLichTrinh(soTau);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int soGhe : AllGheChon) // lặp lại AllGheChonDi.length lần
		{
			stt = tableModel.getRowCount() + 1;
			if (soGhe != 0 && !contains(tableModel, soGhe, sttToa)) // if(soGhe được chọn && tableModel không chứa
																	// soGhe)
				tableModel.addRow(new Object[] { stt, ngayDi, ngayDen, soTau, sttToa, soGhe, giaVe });
		}
	}

	public void xoaVeChon(DefaultTableModel tableModel, JPanel khoang[]) {
		int AllGheChon[] = getAllGheChon(khoang);
		int soGhe, sttToa;
		sttToa = sttToaDangChon(khoang);
		int stt = 0;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			stt++;
			soGhe = (int) tableModel.getValueAt(i, 5);
			if (!containsDs(AllGheChon, soGhe) && tableModel.getValueAt(i, 4).equals(sttToa)) // if(soGhe tồn tại trong
																								// tableModel nhưng
																								// không có trong
																								// AllGheChonDi[])
				tableModel.removeRow(i);
			if (i + 1 <= tableModel.getRowCount())
				tableModel.setValueAt(stt, i, 0); // Đặt lại stt nếu có xóa dòng
		}
	}

	public boolean contains(DefaultTableModel tableModel, int soGhe, int soToa) {
		for (int i = 0; i < tableModel.getRowCount(); i++)
			if (tableModel.getValueAt(i, 5).equals(soGhe) && tableModel.getValueAt(i, 4).equals(soToa))
				return true;
		return false;
	}

	public boolean containsDs(int[] dsGheChon, int soGhe) {
		for (int i : dsGheChon)
			if (i == soGhe)
				return true;
		return false;
	}

	public boolean containsBtn(JToggleButton[] dsBtnChon, JToggleButton button) {
		for (JToggleButton i : dsBtnChon)
			if (i == button)
				return true;
		return false;
	}

	public int sttToaDangChon(JPanel khoang[]) {
		if (btn2Chieu.isSelected()) {
			//// Xử lý Toa
			if (khoang.equals(khoangVe))
				return soToaVe;
			return soToaDi;
		} else if (btn1Chieu.isSelected()) {
			return soToaDi;
		}
		return soToaDi;

	}

	public boolean validChonToa(JPanel[] khoang) {
		if (btn2Chieu.isSelected()) {
			if (khoang.equals(khoangDi)) {
				if (soToaVe != 0 && soToaDi == 0) {
					return false;
				}
				if (soToaDi == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn toa đi!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				return true;
			} else {
				if (soToaDi != 0 && soToaVe == 0) {
					return false;
				}
				if (soToaVe == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn toa về!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				return true;
			}
		} else if (btn1Chieu.isSelected()) {
			if (khoang.equals(khoangDi)) {
				if (soToaDi == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn toa đi!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				return true;
			}
		}
		return true;
	}

	public JToggleButton[] getGheFromTable(DefaultTableModel tableModel, JPanel khoang[]) {
		int soGhe, soKhoang, sttGhe;
		int stt = 0;
		int gheMoiKhoang = khoang[0].countComponents(); // gheMoiKhoang= 4
		int currentToa, sttToa;
		JToggleButton dsBtnChon[] = new JToggleButton[27];
		JToggleButton btnGhe = new JToggleButton();
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			sttGhe = (int) tableModel.getValueAt(i, 5); // get cột số ghế từ table
			currentToa = (int) tableModel.getValueAt(i, 4); // get cột số toa từ table
			sttToa = sttToaDangChon(khoang);
			soGhe = (sttGhe - 1) % gheMoiKhoang; // ??
			soKhoang = (sttGhe - 1) / gheMoiKhoang; // ??
			btnGhe = (JToggleButton) khoang[soKhoang].getComponent(soGhe);
			if (currentToa == sttToa) {
				dsBtnChon[stt] = btnGhe; // Thêm vào ds if(btnGhe thuộc toa đang chọn)
				stt++;
			}
		}
		return dsBtnChon;
	}

	public void updateToaChon(DefaultTableModel tableModel, JPanel khoang[]) {
		int soGheMoiKhoang = khoang[0].countComponents();
		JToggleButton btnGhe = new JToggleButton();
		JToggleButton dsBtnChon[] = getGheFromTable(tableModel, khoang);
		for (int soKhoang = 0; soKhoang < 7; soKhoang++) {
			for (int soGhe = 0; soGhe < soGheMoiKhoang; soGhe++) {
				btnGhe = (JToggleButton) khoang[soKhoang].getComponent(soGhe);
				if (containsBtn(dsBtnChon, btnGhe)) // if(btnGhe toa đang chọn có trong tableModel)
					btnGhe.setSelected(true);
				else
					btnGhe.setSelected(false);
			}
		}
	}

	public boolean validChonTau(JPanel tau, DefaultTableModel tableModel) { //// Xử lý Tàu
		Object[] options = { "Tiếp tục", "Hủy" };
		int chon = 0;
		if (tableModel.getRowCount() != 0)
			chon = JOptionPane.showOptionDialog(null,
					"Bạn có muốn thay đổi chuyến tàu?\nCác vé chưa thanh toán sẽ không được lưu.", "Đổi chuyến tàu",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		if (chon == 0)
			return true;
		return false;
	}

	public void deleteAllGheChon(JPanel khoang[]) {
		int soGheMoiKhoang = khoang[0].countComponents();
		JToggleButton btnGhe = new JToggleButton();
		for (int soKhoang = 0; soKhoang < 7; soKhoang++) {
			for (int soGhe = 0; soGhe < soGheMoiKhoang; soGhe++) {
				btnGhe = (JToggleButton) khoang[soKhoang].getComponent(soGhe);
				btnGhe.setSelected(false);
			}
		}
	}

	public void itemStateChanged(ItemEvent i) {
		JToggleButton button = (JToggleButton) i.getSource();
		if (btn2Chieu.isSelected()) {
			if (button.isSelected()) {
				button.getComponent(0).setForeground(Color.white);
				button.setBorder(BorderFactory.createLineBorder(Color.white, 2));
				themVeChonChoChieuDi(modelKmDi, khoangDi);
				themVeChonChoChieuVe(modelKmVe, khoangVe);
			} else {
				button.getComponent(0).setForeground(Color.black);
				button.setBorder(BorderFactory.createLineBorder(Color.white, 0));
				xoaVeChon(modelKmDi, khoangDi);
				xoaVeChon(modelKmVe, khoangVe);
			}
		} else if (btn1Chieu.isSelected()) {
			if (button.isSelected()) {
				button.getComponent(0).setForeground(Color.white);
				button.setBorder(BorderFactory.createLineBorder(Color.white, 2));
				themVeChonChoChieuDi(modelKmDi, khoangDi);
			} else {
				button.getComponent(0).setForeground(Color.black);
				button.setBorder(BorderFactory.createLineBorder(Color.white, 0));
				xoaVeChon(modelKmDi, khoangDi);
			}
		}
	}

	public JPanel resetPcenter() {
		pCenter_home.setBackground(Color.white);
		pCenter_home.setBorder(new LineBorder(new Color(0, 0, 0)));
		ImageIcon img = new ImageIcon("icon/datVeTau.jpg");
		Image img1 = img.getImage();
		Image temp_img = img1.getScaledInstance(1100, 650, Image.SCALE_SMOOTH);
		img = new ImageIcon(temp_img);
		JLabel lblBackground = new JLabel("", img, JLabel.CENTER);
		lblBackground.setLayout(new BoxLayout(lblBackground, BoxLayout.Y_AXIS));
		pCenter_home.add(lblBackground);
		pCenter_home.revalidate();
		pCenter_home.repaint();
		return pCenter_home;
	}

	public void updateGheUI(String maTau, String maToa, String maLichTrinh, JPanel[] khoang, JButton toa) {
		try {
			Map<String, Boolean> gheTinhTrang = dao_ghe.getTinhTrangGhe(maTau, maToa, maLichTrinh);
			int sttGhe = 1;
			boolean toaFull = true;

			for (JPanel khoangPanel : khoang) {
				Component[] components = khoangPanel.getComponents();
				for (Component component : components) {
					if (component instanceof JToggleButton) {
						JToggleButton ghe = (JToggleButton) component;
						String maGhe = String.format("GHE%02d", sttGhe);

						// Kiểm tra tình trạng ghế và cập nhật màu sắc
						if (gheTinhTrang != null && gheTinhTrang.containsKey(maGhe)) {
							boolean tinhTrang = gheTinhTrang.get(maGhe);
							if (!tinhTrang) {
								ghe.setBackground(Color.GREEN);
								ghe.setEnabled(true);
								toaFull = false;
							} else {
								ghe.setBackground(Color.RED);
								ghe.setEnabled(false);
								
							}
						} else {
							ghe.setBackground(Color.GREEN);
							ghe.setEnabled(true);
							toaFull = false;
						}

						sttGhe++;
					}
				}
			}

			// Nếu toa đã đầy, set màu của toa thành màu đỏ
			if (toaFull) {
				toa.setBackground(Color.RED);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Lỗi khi tải tình trạng ghế!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

}