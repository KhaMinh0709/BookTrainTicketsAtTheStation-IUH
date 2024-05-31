package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import DAO.DAO_KhachHang;
import DAO.DAO_NHANVIEN;
import Entity.KhachHang;
import Entity.NhanVien;

public class QuanLyNhanVien extends JPanel implements ActionListener,MouseListener{
	private JButton btnTim;
	private JButton btn_them;
	private JButton btn_xoa_trang;
	private JButton btn_xoa;
	private JTextField txt_tim;
	private JButton btn_tim;
	private JTextField txtMaNV;
	private JTextField txtTenNV;

	private JButton btnChonHinh;
	private JTextField txtDiaChi;
	private JTextField txtCCCD;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private DefaultTableModel tableModel;
	private JTable table;
	private JDateChooser dateChoiser_1;
	private JDateChooser dateChoiser_2;
	private DAO_NHANVIEN dao_NV;
	private ButtonGroup bg;
	private JComboBox<String> cboCaLam;
	private JComboBox<String> cboTrangThai;
	private SimpleDateFormat sdf;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private NhanVien nv;
	private JButton btn_sua;
	private JButton btn_thoattim;
	private JButton LuuDSNV;


	public QuanLyNhanVien() throws SQLException  {
		// TODO Auto-generated constructor stub
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//lich
		dateChoiser_1 = new JDateChooser();
		dateChoiser_2 = new JDateChooser();
		
		//pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.CYAN);
		add(pnNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pnNorth.add(lblTieuDe);
		
		//pnCenter (cac tac vu. nhap thong tin)
		JPanel pnCenter = new JPanel(new BorderLayout());
		add(pnCenter, BorderLayout.CENTER);
		
		//pnNorth cua pmCenter ( JsplitPane)
		JSplitPane split = new JSplitPane();
		pnCenter.add(split,BorderLayout.NORTH);
		
		//left
		 JPanel leftPanel = new JPanel();
	     btn_them = new JButton("thêm");
	     btn_them.setBackground(Color.CYAN);
	     btn_xoa_trang = new JButton("xóa trắng");
	     btn_xoa_trang.setBackground(Color.CYAN);
	     btn_xoa = new JButton("xóa");
	     btn_xoa.setBackground(Color.CYAN);
	     btn_sua = new JButton("Cập Nhật");
	     btn_sua.setBackground(Color.CYAN);
	     leftPanel.add(btn_them);
	     leftPanel.add(btn_xoa_trang);
	     leftPanel.add(btn_xoa);
	     leftPanel.add(btn_sua);
	     
	     //right
	     JPanel rightPanel = new JPanel();
	     JLabel label_tim = new JLabel("nhập mã số cần tìm");
	     txt_tim = new JTextField(10);
	     btn_tim = new JButton("tìm");
	     btn_tim.setBackground(Color.CYAN);
	     
	     btn_thoattim = new JButton("thoát tìm");
	     btn_thoattim.setBackground(Color.CYAN);
	     
	     LuuDSNV = new JButton("Lưu danh sách Nhân Viên");
	     LuuDSNV.setBackground(Color.CYAN);
	     rightPanel.add(label_tim);
	     rightPanel.add(txt_tim);
	     rightPanel.add(btn_tim);
	     rightPanel.add(btn_thoattim);
	     rightPanel.add(LuuDSNV);
	     
	     split.setLeftComponent(leftPanel);
	     split.setRightComponent(rightPanel);
	     
		
		//pnCenter cua Pcenter
		JPanel pCenter = new JPanel();
		pCenter.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		pnCenter.add(pCenter,BorderLayout.CENTER);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		//pWestInCenterInCenter
		JPanel pnWest_in_Center = new JPanel();
		pCenter.add(pnWest_in_Center, BorderLayout.WEST);
		
		//Box
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		Box b5 = Box.createHorizontalBox();
		
		JLabel lblMaNV, lblTenNV,lblNgaySinh,lblGioiTinh,lblNgayVaoLam;
		
		b1.add(Box.createHorizontalStrut(20));
		b1.add(lblMaNV= new JLabel("Mã Nhân Viên:     "));
		b1.add(Box.createHorizontalStrut(20));
		b1.add(txtMaNV= new JTextField(40));
		b1.add(Box.createHorizontalStrut(20));
		
		b2.add(Box.createHorizontalStrut(20));
		b2.add(lblTenNV= new JLabel("Tên Nhân Viên: "));
		b2.add(Box.createHorizontalStrut(20));
		b2.add(txtTenNV= new JTextField(40));
		b2.add(Box.createHorizontalStrut(20));
		
		b3.add(Box.createHorizontalStrut(20));
		b3.add(lblNgaySinh= new JLabel("Ngày Sinh: "));
		b3.add(Box.createHorizontalStrut(20));
//		b3.add(txtNgaySinh= new JTextField(20));
		b3.add(dateChoiser_1);
		b3.add(Box.createHorizontalStrut(20));
		
		b4.add(Box.createHorizontalStrut(20));
		b4.add(lblGioiTinh= new JLabel("Giới Tính:        "));
		radNam = new JRadioButton("nam",true);
        radNu = new JRadioButton("nu");
         bg = new ButtonGroup();
    	b4.add(Box.createHorizontalStrut(100));
        bg.add(radNam);
        bg.add(radNu);
        b4.add(radNam);
    	b4.add(Box.createHorizontalStrut(10));
        b4.add(radNu);
    	b4.add(Box.createHorizontalStrut(250));
        
    	b5.add(Box.createHorizontalStrut(20));
        b5.add(lblNgayVaoLam = new JLabel("Ngày Vào Làm: "));
    	b5.add(Box.createHorizontalStrut(20));
//        b5.add(txtNgayVaoLam = new JTextField(20));
    	b5.add(dateChoiser_2);
    	b5.add(Box.createHorizontalStrut(30));
     
        Box BoxWest = Box.createVerticalBox();
        BoxWest.add(Box.createVerticalStrut(20));
        BoxWest.add(b1);
        BoxWest.add(Box.createVerticalStrut(20));
        BoxWest.add(b2);
        BoxWest.add(Box.createVerticalStrut(20));
        BoxWest.add(b3);
        BoxWest.add(Box.createVerticalStrut(20));
        BoxWest.add(b4);
        BoxWest.add(Box.createVerticalStrut(20));
        BoxWest.add(b5);
        BoxWest.add(Box.createVerticalStrut(20));
        
        lblTenNV.setPreferredSize(lblMaNV.getPreferredSize());
        lblNgaySinh.setPreferredSize(lblMaNV.getPreferredSize());
        lblNgayVaoLam.setPreferredSize(lblMaNV.getPreferredSize());
       
        pnWest_in_Center.add(BoxWest);
        
        
         //pnEastInCenter
        JPanel pnEast_in_Center = new JPanel();
        pCenter.add(pnEast_in_Center, BorderLayout.EAST);
    	//Box
		Box ba = Box.createHorizontalBox();
		Box bb = Box.createHorizontalBox();
		Box bc = Box.createHorizontalBox();
		Box bd = Box.createHorizontalBox();
		Box be = Box.createHorizontalBox();
		Box bf = Box.createHorizontalBox();
	
		JLabel lblDiaChi, lblCanCuoc,lblSDT,lblEmail,lblCaLam,lblTrangThai;

		ba.add(Box.createHorizontalStrut(20));
		ba.add(lblDiaChi= new JLabel("Địa Chỉ:      "));
		ba.add(Box.createHorizontalStrut(20));
		ba.add(txtDiaChi= new JTextField(40));
		ba.add(Box.createHorizontalStrut(20));
		
		bb.add(Box.createHorizontalStrut(20));
		bb.add(lblCanCuoc= new JLabel("CCCD: "));
		bb.add(Box.createHorizontalStrut(20));
		bb.add(txtCCCD= new JTextField(40));
		bb.add(Box.createHorizontalStrut(20));
		
		bc.add(Box.createHorizontalStrut(20));
		bc.add(lblSDT= new JLabel("SDT: "));
		bc.add(Box.createHorizontalStrut(20));
		bc.add(txtSDT= new JTextField(20));
		bc.add(Box.createHorizontalStrut(20));
		
		bd.add(Box.createHorizontalStrut(20));
		bd.add(lblEmail= new JLabel("Email: "));
		bd.add(Box.createHorizontalStrut(20));
        bd.add(txtEmail = new JTextField(40));
		bd.add(Box.createHorizontalStrut(20));
        
		be.add(Box.createHorizontalStrut(20));
        be.add(lblCaLam = new JLabel("Ca Làm:"));
		be.add(Box.createHorizontalStrut(10));
        cboCaLam = new JComboBox<String>();
        cboCaLam.addItem("Ca 1");
        cboCaLam.addItem("Ca 2");
        cboCaLam.addItem("Ca 3");
        cboCaLam.setSelectedIndex(-1);
		be.add(Box.createHorizontalStrut(20));
        be.add(cboCaLam);
		be.add(Box.createHorizontalStrut(20));
        
		bf.add(Box.createHorizontalStrut(20));
        bf.add(lblTrangThai = new JLabel("Trạng Thái:  "));
        cboTrangThai = new JComboBox<String>();
        cboTrangThai.addItem("Dang Lam");
        cboTrangThai.addItem("Da Nghi");
        cboTrangThai.addItem("Tam Nghi");
        cboTrangThai.setSelectedIndex(-1);
		bf.add(Box.createHorizontalStrut(20));
        bf.add(cboTrangThai);
		bf.add(Box.createHorizontalStrut(20));
        
        Box boxTong = Box.createVerticalBox();
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(ba);
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(bb);
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(bc);
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(bd);
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(be);
        boxTong.add(Box.createVerticalStrut(20));
        boxTong.add(bf);
        boxTong.add(Box.createVerticalStrut(20));
        
        lblCanCuoc.setPreferredSize(lblDiaChi.getPreferredSize());
        lblSDT.setPreferredSize(lblDiaChi.getPreferredSize());
        lblEmail.setPreferredSize(lblDiaChi.getPreferredSize());
        lblCaLam.setPreferredSize(lblDiaChi.getPreferredSize());
        cboCaLam.setPreferredSize(txtDiaChi.getPreferredSize());
        cboTrangThai.setPreferredSize(cboCaLam.getPreferredSize());

        pnEast_in_Center.add(boxTong);
       
       
        
		
		
		//pnSouth
		JPanel pnSouth = new JPanel();
		add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		String[] header = "Mã Nhân Viên;Tên;Ngày Sinh;CCCD;Địa Chỉ;SDT;Email;Ca Làm;Trạng Thái;Ngày vào Làm;Giới tinh".split(";");
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setRowHeight(20);
		JScrollPane sc = new JScrollPane(table);
		sc.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		sc.setPreferredSize(new Dimension(1300, table.getRowHeight() * 30));
		pnSouth.add(sc,BorderLayout.CENTER);
		
		//lay dlieu tu database
		dao_NV = new DAO_NHANVIEN();
		tableModel= dao_NV.getAllNV();
		table.setModel(tableModel);
		
		//dky skien nut an
		btn_them.addActionListener(this);
		btn_xoa.addActionListener(this);
		btn_xoa_trang.addActionListener(this);
		btn_sua.addActionListener(this);
		btn_tim.addActionListener(this);
		btn_thoattim.addActionListener(this);
		LuuDSNV.addActionListener(this);
		
		
		
		 //DKY SKIEN Chuot
		 table.addMouseListener(this);
		//dky skien bàn phím 
		 InputMap inputMap = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		 ActionMap actionMap = table.getActionMap();

		 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "previousRow");
		 inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "nextRow");

		 actionMap.put("previousRow", (Action) new AbstractAction() {
		     public void actionPerformed(ActionEvent e) {
		         int selectedRow = table.getSelectedRow();
		         if (selectedRow > 0) {
		             table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
		             updateTextFields(selectedRow - 1);
		         }
		     }
		 });

		 actionMap.put("nextRow", new AbstractAction() {
		     @Override
		     public void actionPerformed(ActionEvent e) {
		         int selectedRow = table.getSelectedRow();
		         if (selectedRow < table.getRowCount() - 1) {
		             table.setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
		             updateTextFields(selectedRow + 1);
		         }
		     }
		 });
	}
		
	protected void updateTextFields(int row) {
		// TODO Auto-generated method stub
		txtMaNV.setText(((String) table.getValueAt(row, 0)).trim());
	    txtTenNV.setText(table.getValueAt(row, 1).toString().trim());
	    
	    java.sql.Date dateInTableNS = (java.sql.Date) table.getValueAt(row, 2);
	    dateChoiser_1.setDate(dateInTableNS);
	    
	    txtCCCD.setText(table.getValueAt(row, 3).toString().trim());

	    txtDiaChi.setText(table.getValueAt(row, 4).toString().trim());
	    txtSDT.setText(table.getValueAt(row, 5).toString().trim());
	    txtEmail.setText(table.getValueAt(row, 6).toString().trim());

	    cboCaLam.setSelectedItem(((String) table.getValueAt(row, 7)).trim());

	    cboTrangThai.setSelectedItem(((String) table.getValueAt(row, 8)).trim());
	    //get ngay vao lam
	    java.sql.Date dateInTableNVL = (java.sql.Date) table.getValueAt(row, 9);
	    dateChoiser_2.setDate(dateInTableNVL);
	    
	    // get gioi tinh
        Object value = table.getValueAt(row, 10);
        if (value != null && value.toString().equalsIgnoreCase("nu")) {
            radNu.setSelected(true);
            radNam.setSelected(false);
        } else {
            radNu.setSelected(false);
            radNam.setSelected(true);
        }
		
	}

	private boolean validData() {
		String tenNV = txtTenNV.getText();
		String maNV = txtMaNV.getText();
		String sdt = txtSDT.getText();
		String cccd = txtCCCD.getText();
		String diachi = txtDiaChi.getText();
		
		 
		if(!(tenNV.length()>0)){

			JOptionPane.showMessageDialog(null, "Tên không trống " );
		
			return false;
		}
		if(!(maNV.length()>0 && maNV.matches("^NV\\d{3}$"))) {
			JOptionPane.showMessageDialog(null, "mã phải theo dạng NV+ d{3}");
			return false;
		}
		if(!(cccd.length()>0 && cccd.matches("\\d{9}"))) {
			JOptionPane.showMessageDialog(null, "Chứng minh nhân dân gồm  9 số");
			return false;
		}
		if(! diachi.matches("^[0-9a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ\" +\r\n" + 
				"	            \"ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ\" +\r\n" + 
				"	            \"ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\\\s/\\\\.,]+$")){
			JOptionPane.showMessageDialog(null, "Địa chỉ không hợp lệ " );
			return false;
	            }

		if(!(sdt.matches("^[0][1-9][0-9]{8}$"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại gồm 10 kí tự số và bắt đầu từ kí tự 0");
			return false;
		}
		return true;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 Object obj = e.getSource();
		 if(obj == btn_them) {
			 if(validData()) {
			 String maNV = txtMaNV.getText();
			 String tenNV = txtTenNV.getText();
			 java.util.Date NgaySinh_until = dateChoiser_1.getDate();
			 java.sql.Date ngaySinh = new java.sql.Date(NgaySinh_until.getTime());
			 String cccd = txtCCCD.getText();
			 String diachi = txtDiaChi.getText();
			 String std = txtSDT.getText();
			 String email = txtEmail.getText();
			 String calam = (String) cboCaLam.getSelectedItem();
			 String trangthai = (String) cboTrangThai.getSelectedItem();
			 java.util.Date NgayVL_until = dateChoiser_2.getDate();
			 java.sql.Date ngayVaoLam = new java.sql.Date(NgayVL_until.getTime());
			 String gioiTinh = radNam.isSelected() ? "nam" : "nu";
			 
			 nv = new NhanVien(maNV, tenNV, ngaySinh, cccd, diachi, std, email, calam, trangthai, ngayVaoLam, gioiTinh);
			 
				 try {
					 if(dao_NV.ThemNhanVien(nv)) {
				
						 	tableModel.addRow(new Object [] {nv.getMaNV(),nv.getHoTen(),nv.getNgaySinh(),nv.getcCCD(),
						 			nv.getDiaChi(),nv.getSoDienThoai(),nv.getEmail(),nv.getCaLam(),nv.getTrangThai(),
						 			nv.getNgayVaoLam(),nv.getGioiTinh()});
						 	JOptionPane.showMessageDialog(this," thêm thành công");
					 }else {
							JOptionPane.showMessageDialog(this, "trùng mã nhân viên !!!!");
					 }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				}
			 }
		 }
		 else if(obj.equals(btn_xoa_trang)) {
			 txtMaNV.setText("");
			 txtTenNV.setText("");
			 txtSDT.setText("");
			 txtCCCD.setText("");
			 txtDiaChi.setText("");
			 txt_tim.setText("");
			 txtEmail.setText("");
				
			}
		 else if(obj.equals(btn_xoa)) {
			 int NV_chonn = table.getSelectedRow();
			 String maNV = (String) table.getValueAt(NV_chonn, 0);
			 JFrame f= new JFrame();
			 int hoi=JOptionPane.showConfirmDialog(f, "Nhân Viên này sẽ bị xóa?","Chú ý",JOptionPane.YES_NO_OPTION);
			 if(hoi==JOptionPane.YES_OPTION) {
				 try {
					if(dao_NV.xoaNV(maNV)) {
						tableModel.removeRow(NV_chonn);
					}
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				
					}
			 	}
			}
		 else if(obj.equals(btn_sua)) {
				try {
					if(validData()) {
						JFrame f= new JFrame();
						int hoi=JOptionPane.showConfirmDialog(f, "Nhân viên này sẽ được cập nhật","Chú ý",JOptionPane.YES_NO_OPTION);
						if(hoi==JOptionPane.YES_OPTION) {
							 String maNV = txtMaNV.getText();
							 String tenNV = txtTenNV.getText();
							 java.util.Date NgaySinh_until = dateChoiser_1.getDate();
							 java.sql.Date ngaySinh = new java.sql.Date(NgaySinh_until.getTime());
							 String cccd = txtCCCD.getText();
							 String diachi = txtDiaChi.getText();
							 String std = txtSDT.getText();
							 String email = txtEmail.getText();
							 String calam = (String) cboCaLam.getSelectedItem().toString();
							 String trangthai = (String) cboTrangThai.getSelectedItem().toString();
							 java.util.Date NgayVL_until = dateChoiser_2.getDate();
							 java.sql.Date ngayVaoLam = new java.sql.Date(NgayVL_until.getTime());
							 String gioiTinh = radNam.isSelected() ? "nam" : "nu";
							 
							NhanVien nv = new NhanVien(maNV, tenNV, ngaySinh, cccd, diachi, std, email, calam, trangthai, ngayVaoLam, gioiTinh);
							if(dao_NV.update(nv)) {
								int row = table.getSelectedRow();
								table.setValueAt(tenNV, row,1);
								table.setValueAt(ngaySinh, row,2);
								table.setValueAt(cccd, row,3);
								table.setValueAt(diachi, row,4);
								table.setValueAt(std,row,5);
								table.setValueAt(email,row,6);
								table.setValueAt(calam,row,7);
								table.setValueAt(trangthai,row,8);
								table.setValueAt(ngayVaoLam,row,9);
								table.setValueAt(gioiTinh,row,10);
								JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
							}
							
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		 }else if(obj.equals(btn_tim)) {
			 String ma = txt_tim.getText().trim();
			    try {
			        tableModel = dao_NV.timKiem(ma);
			        if (tableModel.getRowCount() != 0) {
			            table.setModel(tableModel);
			            JOptionPane.showMessageDialog(this, "Đã tìm thấy nhân viên có mã: " + ma);
			        } else {
			            JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên có mã: " + ma);
			        }
			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
		 }else if(obj.equals(btn_thoattim)) {
			 dao_NV = new DAO_NHANVIEN();
			 try {
				tableModel= dao_NV.getAllNV();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 table.setModel(tableModel);
			 
		 }else if (obj.equals(LuuDSNV)) {
			    // Creating a file chooser dialog
			    JFileChooser chonFile = new JFileChooser();
			    int userSelection = chonFile.showSaveDialog(null);

			    // If the user selects to save
			    if (userSelection == JFileChooser.APPROVE_OPTION) {
			        // Get the selected file
			        File luuFile = chonFile.getSelectedFile();
			        try {
			            // Get the table model
			            TableModel model = table.getModel();
			            // Create a new Excel workbook
			            XSSFWorkbook workbook = new XSSFWorkbook();
			            // Create a new sheet in the workbook
			            XSSFSheet sheet = workbook.createSheet("Danh sách Nhân Viên");

			            // Add header row to the sheet
			            Row headerRow = sheet.createRow(0);
			            for (int i = 0; i < model.getColumnCount(); i++) {
			                Cell cell = headerRow.createCell(i);
			                cell.setCellValue(model.getColumnName(i));
			            }

			            // Write data to the sheet
			            for (int i = 0; i < model.getRowCount(); i++) {
			                Row row = sheet.createRow(i + 1); 
			                for (int j = 0; j < model.getColumnCount(); j++) {
			                    Cell cell = row.createCell(j);
			                    cell.setCellValue(model.getValueAt(i, j).toString());
			                }
			            }

			            // Write the workbook to a file
			            FileOutputStream fileOut = new FileOutputStream(luuFile.getAbsolutePath() + ".xlsx");
			            workbook.write(fileOut);
			            fileOut.close();
			            workbook.close();

			            // Show a success message
			            JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu vào " + luuFile.getAbsolutePath() + ".xlsx");
			        } catch (IOException e1) {
			            // Handle IOException
			            e1.printStackTrace();
			            JOptionPane.showMessageDialog(null, "Lỗi khi lưu tệp Excel.");
			        }
			    }
			}


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int row = table.getSelectedRow();
		 updateTextFields(row);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
