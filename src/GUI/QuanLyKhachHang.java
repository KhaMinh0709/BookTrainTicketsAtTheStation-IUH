package GUI;

import javax.swing.AbstractAction;  
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;    
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_KhachHang;
import Entity.KhachHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class QuanLyKhachHang extends JPanel implements ActionListener,MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btn_them;
	private JButton btn_xoa_trang;
	private JButton btn_xoa;
	private JTextField txt_tim;
	private JButton btn_tim;
	private DefaultTableModel tableModel;
	private JTable table = null;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JComboBox<String> CboGioiTinh;
	private JTextField txtDiaChi;
	private JLabel lblCCCD;
	private JTextField txtCCCD;
	private JLabel lblLoaiKH;
	private JTextField txtLoaiKH;
	private DAO_KhachHang dao_kh;
	private JButton btn_sua;
	private JDateChooser dateChooser;
	private JButton btn_thoattim;
	private JButton LuuDSKH;

	public QuanLyKhachHang() throws SQLException {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(0, 0));
		setVisible(true);
	
		//pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.CYAN);
		add(pnNorth, BorderLayout.NORTH);
				
		JLabel lblTieuDe = new JLabel("QUẢN LÝ KHÁCH HÀNG");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pnNorth.add(lblTieuDe);
		
		//pnCenter
		JPanel pnCenter = new JPanel(new BorderLayout());
		add(pnCenter, BorderLayout.CENTER);
		
		
		//pnNorthInCenter
		JPanel pnNorthInCenter = new JPanel();
		pnNorthInCenter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				new Color(255, 255, 255), new Color(160, 160, 160)),
				"  Thông Tin khách Hàng", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		pnCenter.add(pnNorthInCenter,BorderLayout.NORTH);
		
		//Box
		Box b1 = Box.createHorizontalBox();
		Box b2 = Box.createHorizontalBox();
		Box b3 = Box.createHorizontalBox();
		Box b4 = Box.createHorizontalBox();
		
		JLabel lblMaKH, lblTenKH,lblSDT,lblGioiTinh,lblNgaýinh,lblDiaChi;
		
		b1.add(Box.createHorizontalStrut(30));
		b1.add(lblMaKH= new JLabel("Mã Khách Hàng:                  "));
		b1.add(Box.createHorizontalStrut(30));
		b1.add(txtMaKH= new JTextField(30));
		b1.add(Box.createHorizontalStrut(180));
		b1.add(lblGioiTinh = new JLabel("Giới Tính"));
		b1.add(Box.createHorizontalStrut(30));
		CboGioiTinh = new JComboBox<String>();
		CboGioiTinh.addItem("Nam");
		CboGioiTinh.addItem("Nu");
		b1.add(CboGioiTinh);
		b1.add(Box.createHorizontalStrut(30));
		
		
		b2.add(Box.createHorizontalStrut(30));
		b2.add(lblTenKH= new JLabel("Tên Khách Hàng: "));
		b2.add(Box.createHorizontalStrut(30));
		b2.add(txtTenKH= new JTextField(30));
		b2.add(Box.createHorizontalStrut(160));
		dateChooser = new JDateChooser();
		b2.add(lblNgaýinh = new JLabel("       Ngày Sinh"));
		b2.add(Box.createHorizontalStrut(30));
		b2.add(dateChooser);
		b2.add(Box.createHorizontalStrut(30));
		
		b3.add(Box.createHorizontalStrut(30));
		b3.add(lblSDT= new JLabel("Số Điện Thoại: "));
		b3.add(Box.createHorizontalStrut(30));
		b3.add(txtSDT= new JTextField(30));
		b3.add(Box.createHorizontalStrut(180));
		b3.add(lblDiaChi = new JLabel("Địa Chỉ"));
		b3.add(Box.createHorizontalStrut(30));
		b3.add(txtDiaChi  = new JTextField(30));
		b3.add(Box.createHorizontalStrut(30));
		
		b4.add(Box.createHorizontalStrut(30));
		b4.add(lblCCCD= new JLabel("Căn Cước : "));
		b4.add(Box.createHorizontalStrut(30));
		b4.add(txtCCCD= new JTextField(30));
		b4.add(Box.createHorizontalStrut(180));
		b4.add(lblLoaiKH = new JLabel("Loại KH"));
		b4.add(Box.createHorizontalStrut(30));
		b4.add(txtLoaiKH  = new JTextField(30));
		b4.add(Box.createHorizontalStrut(30));
		
		
		 Box boxTong = Box.createVerticalBox();
	     boxTong.add(Box.createVerticalStrut(20));
	     boxTong.add(b1);
	     boxTong.add(Box.createVerticalStrut(20));
	     boxTong.add(b2);
	     boxTong.add(Box.createVerticalStrut(20));
	     boxTong.add(b3);
	     boxTong.add(Box.createVerticalStrut(20));
	     boxTong.add(b4);
	     
	     lblTenKH.setPreferredSize(lblMaKH.getPreferredSize());
	     lblSDT.setPreferredSize(lblMaKH.getPreferredSize());
	     lblGioiTinh.setPreferredSize(lblMaKH.getPreferredSize());
	     lblNgaýinh.setPreferredSize(lblMaKH.getPreferredSize());
	     lblDiaChi.setPreferredSize(lblMaKH.getPreferredSize());
	     CboGioiTinh.setPreferredSize(txtMaKH.getPreferredSize());
	     dateChooser.setPreferredSize(txtMaKH.getPreferredSize());
	     lblLoaiKH.setPreferredSize(lblMaKH.getPreferredSize());
	     lblCCCD.setPreferredSize(lblMaKH.getPreferredSize());
	     
	     pnNorthInCenter.add(boxTong);
		
		
		 
		//pnCenter cua pmCenter ( JsplitPane)
		JSplitPane split = new JSplitPane();
		pnCenter.add(split,BorderLayout.CENTER);
		
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
	     
	     LuuDSKH = new JButton("Lưu danh sách Khách hàng");
	     LuuDSKH.setBackground(Color.CYAN);
	     rightPanel.add(label_tim);
	     rightPanel.add(txt_tim);
	     rightPanel.add(btn_tim);
	     rightPanel.add(btn_thoattim);
	     rightPanel.add(LuuDSKH);
	     
	     split.setLeftComponent(leftPanel);
	     split.setRightComponent(rightPanel);
				
	     //pSouthInCenter
	     JPanel pnSouthInCenter = new JPanel();
	     pnSouthInCenter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
					new Color(255, 255, 255), new Color(160, 160, 160)),
					"  Danh Sách khách Hàng", 
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
	     pnCenter.add(pnSouthInCenter,BorderLayout.SOUTH);
	     String[] header = "Mã Khách Hàng;Tên;Giới Tính;SDT;CCCD;Địa Chỉ;Loại Khách Hàng;Ngày Sinh".split(";");
	     tableModel = new DefaultTableModel(header, 0);
		 table = new JTable(tableModel);
		 JScrollPane sc = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 table.setRowHeight(25);
		 sc.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		 sc.setPreferredSize(new Dimension(1300, table.getRowHeight() * 20));
		 pnSouthInCenter.add(sc,BorderLayout.CENTER);
		 
		 // đưa dữ liệu từ database lên table
		 dao_kh = new DAO_KhachHang();
		 tableModel= dao_kh.getAllKH();
		 table.setModel(tableModel);
		 
		 //DKY SKIEN action
		 btn_them.addActionListener(this);
		 btn_xoa_trang.addActionListener(this);
		 btn_xoa.addActionListener(this);
		 btn_sua.addActionListener(this);
		 btn_tim.addActionListener(this);
		 btn_thoattim.addActionListener(this);
		 LuuDSKH.addActionListener(this);
		 
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
	private void updateTextFields(int row) {
	    txtMaKH.setText(((String) table.getValueAt(row, 0)).trim());
	    txtTenKH.setText(table.getValueAt(row, 1).toString().trim());
	    CboGioiTinh.setSelectedItem(((String) table.getValueAt(row, 2)).trim());
	    txtSDT.setText(table.getValueAt(row, 3).toString().trim());
	    txtCCCD.setText(table.getValueAt(row, 4).toString().trim());
	    txtDiaChi.setText(table.getValueAt(row, 5).toString().trim());
	    txtLoaiKH.setText(table.getValueAt(row, 6).toString().trim());
	    String dateInTable = (String) table.getValueAt(row, 7);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dateInDatechoice = null;
	    try {
	        dateInDatechoice = sdf.parse(dateInTable.trim());
	    } catch (ParseException e1) {
	        e1.printStackTrace();
	    }
	    dateChooser.setDate(dateInDatechoice);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 Object obj = e.getSource();
		 if(obj == btn_them) {
			 if(validData()) {
			 String maKH = txtMaKH.getText();
			 String tenKH = txtTenKH.getText();
			 String gioiTinh = (String) CboGioiTinh.getSelectedItem();
			 String sdt = txtSDT.getText();
			 String cccd = txtCCCD.getText();
			 String diachi = txtDiaChi.getText();
			 int loaikh = Integer.parseInt(txtLoaiKH.getText());
			 java.util.Date NgaySinh_until = dateChooser.getDate();
			 java.sql.Date ngaySinh = new java.sql.Date(NgaySinh_until.getTime());
			 
			 KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, sdt, cccd, diachi, loaikh, ngaySinh);
				 try {
					 if(dao_kh.ThemKhachHang(kh)) {
				
						 	tableModel.addRow(new Object [] {kh.getMaKh(),kh.getTenKH(),kh.getGioiTinh(), kh.getSoDienThoai(),
							kh.getcCCD(),kh.getDiaChi(),kh.getLoaiKH(),kh.getNgaySinh()});
						 	JOptionPane.showMessageDialog(this," thêm thành công");
					 }else {
							JOptionPane.showMessageDialog(this, "trùng mã khách hàng !!!!");
					 }
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				}
			 }
		 }
		 else if(obj.equals(btn_xoa_trang)) {
			 txtMaKH.setText("");
			 txtTenKH.setText("");
			 txtSDT.setText("");
			 txtCCCD.setText("");
			 txtDiaChi.setText("");
			 txtLoaiKH.setText("");
				
			}
		 else if(obj.equals(btn_xoa)) {
			 int KH_chonn = table.getSelectedRow();
			 String maKH = (String) table.getValueAt(KH_chonn, 0);
			 JFrame f= new JFrame();
			 int hoi=JOptionPane.showConfirmDialog(f, "Khách Hàng này sẽ bị xóa?","Chú ý",JOptionPane.YES_NO_OPTION);
			 if(hoi==JOptionPane.YES_OPTION) {
				 try {
					if(dao_kh.xoaKH(maKH)) {
						tableModel.removeRow(KH_chonn);
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
						int hoi=JOptionPane.showConfirmDialog(f, "Khách hàng này sẽ được cập nhật","Chú ý",JOptionPane.YES_NO_OPTION);
						if(hoi==JOptionPane.YES_OPTION) {
							 String maKH = txtMaKH.getText();
							 String tenKH = txtTenKH.getText();
							 String gioiTinh = (String) CboGioiTinh.getSelectedItem();
							 String sdt = txtSDT.getText();
							 String cccd = txtCCCD.getText();
							 String diachi = txtDiaChi.getText();
							 int loaikh = Integer.parseInt(txtLoaiKH.getText());
							 java.util.Date NgaySinh_until = dateChooser.getDate();
							 java.sql.Date ngaySinh = new java.sql.Date(NgaySinh_until.getTime());
							 
							KhachHang kh = new KhachHang(maKH, tenKH, gioiTinh, sdt, cccd, diachi, loaikh, ngaySinh);
							if(dao_kh.update(kh)) {
								int row = table.getSelectedRow();
								table.setValueAt(tenKH, row,1);
								table.setValueAt(gioiTinh, row,2);
								table.setValueAt(sdt, row,3);
								table.setValueAt(cccd,row,4);
								table.setValueAt(diachi, row,5);
								table.setValueAt(loaikh,row,6);
								table.setValueAt(ngaySinh,row,7);
								JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
							}
							
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		 }else if(obj.equals(btn_tim)) {
			 String ma = txt_tim.getText().trim();
			    try {
			        tableModel = dao_kh.timKiem(ma);
			        if (tableModel.getRowCount() != 0) {
			            table.setModel(tableModel);
			            JOptionPane.showMessageDialog(this, "Đã tìm thấy khách hàng có mã: " + ma);
			        } else {
			            JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng có mã: " + ma);
			        }
			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
		 }else if(obj.equals(btn_thoattim)) {
			 dao_kh = new DAO_KhachHang();
			 try {
				tableModel= dao_kh.getAllKH();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 table.setModel(tableModel);
			 
		 } else if (obj.equals(LuuDSKH)) {
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
			            XSSFSheet sheet = workbook.createSheet("Danh sách khách hàng");

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
			
	
		 
		
	private boolean validData() {
		String tenNV = txtTenKH.getText();
		String maNV = txtMaKH.getText();
		String gioiTinh = (String) CboGioiTinh.getSelectedItem();
		String sdt = txtSDT.getText();
		String cccd = txtCCCD.getText();
		String diachi = txtDiaChi.getText();
		int loaikh = Integer.parseInt(txtLoaiKH.getText());
		 
		if(!(tenNV.length()>0)){

			JOptionPane.showMessageDialog(null, "Tên khách hàng không trống " );
		
			return false;
		}
		if(!(maNV.length()>0 && maNV.matches("^KH\\d{3}$"))) {
			JOptionPane.showMessageDialog(null, "mã phải theo dạng KH+ d{3}");
			return false;
		}
		if(!(gioiTinh.matches("(Nam|Nu)$"))) {
			JOptionPane.showMessageDialog(null, "giới tính Nam hoặc Nu " );
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
		if(!(loaikh >0 && loaikh <=3 )) {
			JOptionPane.showMessageDialog(null, "loai KH là 1 hoặc 2 hoặc 3");
			return false;
		}
		if(!(sdt.matches("^[0][1-9][0-9]{8}$"))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại gồm 10 kí tự số và bắt đầu từ kí tự 0");
			return false;
		}
		return true;
		
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



