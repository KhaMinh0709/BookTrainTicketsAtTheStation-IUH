package GUI;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import DAO.DAO_HoaDonVeTau;

public class QuanLyHuyVe extends JPanel implements ActionListener,MouseListener{
	private JLabel lblThongTin;
	private JLabel lblMa;
	private JTextField txtMa;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private DefaultTableModel modelTV;
	private JTable tableTV;
	private JButton btnTim;
	private JLabel ve;
	private JButton btnTra;
	private DefaultTableModel modelChonVe;
	private JTable tableChonVe;
	private JButton btnTraVe;
	private JLabel lblTong;
	private JTextField txtTong;
	private JLabel lblPhi;
	private JTextField txtPhi;
	private JLabel lblTienTra;
	private JTextField txtTienTra;
	private JLabel lblChon;
	private DAO_HoaDonVeTau dao_ve;
	private JLabel lblGiaVe;
	private JTextField txtGiaVe;
	private JLabel lblTinhGia;
	private JLabel lblThoiGian;

	public QuanLyHuyVe() {
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//
		dao_ve = new DAO_HoaDonVeTau();
		//pnNorth
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("TRẢ VÉ TÀU");
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
		Box b0, b1, b2, b3;
		
		b.add(b0 = Box.createHorizontalBox());
		b0.add(lblThongTin= new JLabel("Tra cứu vé đặt"));
		lblThongTin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblThongTin.setForeground(Color.BLUE);
		b.add(Box.createVerticalStrut(10));
		
		b.add(b1 = Box.createHorizontalBox());
		b1.add(lblMa = new JLabel("Tên KH"));
		b.add(Box.createVerticalStrut(30));
		b1.add(txtMa= new JTextField(15));
		b.add(Box.createVerticalStrut(5));
		
		b.add(b2 = Box.createHorizontalBox());
		b2.add(lblSDT= new JLabel("Số điện thoại: "));
		b2.add(txtSDT= new JTextField(15));
		b.add(Box.createVerticalStrut(5));
		
		b.add(Box.createVerticalStrut(5));
		b.add(b3 = Box.createHorizontalBox());
		b3.add(btnTim = new JButton("Tìm kiếm"));
		
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
		veScroll.setPreferredSize(new Dimension(1300, tableTV.getRowHeight() * 11));
		bC1.add(veScroll,BorderLayout.CENTER);

		tableTV.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableTV.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableTV.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableTV.getColumnModel().getColumn(3).setPreferredWidth(160);
		
		bC.add(Box.createVerticalStrut(5));
		bC.add(bC2= Box.createHorizontalBox());
		bC2.add(btnTra= new JButton("Chọn vé trả"));
		
		bC.add(Box.createVerticalStrut(8));
		
		//p3
		JPanel p3 = new JPanel();
		pCenter.add(p3, BorderLayout.SOUTH);
		p3.setBorder(new EmptyBorder(10,0,0,0));
		p3.setLayout(new BorderLayout(0, 0));
		
		Box bS= Box.createVerticalBox();
		p3.add(bS);
		Box bS0, bS1, bS2;
		
		bS.add(bS0= Box.createHorizontalBox());
		bS0.add(lblChon= new JLabel("Vé đã chọn"));
		lblChon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblChon.setForeground(Color.RED);
		
		
		bS.add(bS1= Box.createHorizontalBox());
		String[] headerTra = {"Mã hóa đơn","Khách Hàng","Mã vé","Nhân viên lập vé","Ngày đi","Ga đi","Ga đến"};
		modelChonVe= new DefaultTableModel(headerTra, 0);
		tableChonVe= new JTable(modelChonVe);
		tableChonVe.setBackground(Color.WHITE);
		bS1.add(tableChonVe);
		JScrollPane traScroll = new JScrollPane(tableChonVe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		traScroll.setPreferredSize(new Dimension(1300, tableTV.getRowHeight() * 11));
		bS1.add(traScroll,BorderLayout.CENTER);
		btnTra.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int[] selectedRows = tableTV.getSelectedRows();
		        
		        for (int row : selectedRows) {
		            Object[] rowData = new Object[headerTra.length];
		            for (int col = 0; col < headerTra.length; col++) {
		                rowData[col] = tableTV.getValueAt(row, col);
		            }
		            modelChonVe.addRow(rowData); 
		        }
		        
		        for (int i = selectedRows.length - 1; i >= 0; i--) {
		            modelTV.removeRow(selectedRows[i]);
		        }
		        updateTongTienVe();
		    }
		});

		tableChonVe.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableChonVe.getColumnModel().getColumn(1).setPreferredWidth(160);
		tableChonVe.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableChonVe.getColumnModel().getColumn(3).setPreferredWidth(160);
		
		bS.add(Box.createVerticalStrut(5));
		bS.add(bS2= Box.createHorizontalBox());
		bS2.add(btnTra= new JButton("Bỏ chọn vé"));
		bS.add(Box.createVerticalStrut(10));
		btnTra.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int[] selectedRows = tableChonVe.getSelectedRows();
		        
		        for (int row : selectedRows) {
		            Object[] rowData = new Object[header.length];
		            for (int col = 0; col < header.length; col++) {
		                rowData[col] = tableChonVe.getValueAt(row, col);
		            }
		            modelTV.addRow(rowData);
		        }
		        for (int i = selectedRows.length - 1; i >= 0; i--) {
		            modelChonVe.removeRow(selectedRows[i]);
		        }
		        txtGiaVe.setText("");
		        updateTongTienVe();
		    }
		    
		});

		
		
		//pSouth
		JPanel pSouth = new JPanel();
		add(pSouth, BorderLayout.SOUTH);
		pSouth.setBorder(new LineBorder(new Color(0, 0, 0)));
		pSouth.setPreferredSize(new Dimension(1000,105));
		pSouth.setLayout(new BorderLayout(0, 0));
		
		//pLeft
		JPanel pLeft = new JPanel();
		pLeft.setLayout(new BorderLayout(0, 0));
		pLeft.setBorder(new EmptyBorder(10,50,30,100));
		pSouth.add(pLeft, BorderLayout.WEST);
		Box bL= Box.createVerticalBox();
		pLeft.add(bL);
		Box bL1, bL2, bL3, bL4;
		
		bL.add(Box.createVerticalStrut(10));
		bL.add(bL1= Box.createHorizontalBox());
		bL1.add(lblGiaVe= new JLabel("Tiền vé đang chọn: "));
		bL1.add(txtGiaVe= new JTextField(15));
		bL1.add(Box.createHorizontalStrut(50));
		bL1.add(lblTong= new JLabel("Tổng tiền vé: "));
		bL1.add(txtTong= new JTextField(15));
		bL1.add(Box.createHorizontalStrut(50));
		
		bL.add(Box.createVerticalStrut(10));
		bL.add(bL2= Box.createHorizontalBox());
		bL2.add(lblPhi= new JLabel("Tổng lệ phí: "));
		bL2.add(txtPhi= new JTextField(15));
		bL2.add(Box.createHorizontalStrut(50));
		bL2.add(lblTienTra= new JLabel("Tổng tiền trả: "));
		bL2.add(txtTienTra= new JTextField(15));
		
		bL.add(Box.createVerticalStrut(10));
		bL.add(bL3= Box.createHorizontalBox());
		bL3.add(lblTinhGia= new JLabel("* Mức phí hủy vé là 10% giá trị của vé ban đầu"));
		lblTinhGia.setForeground(Color.RED);
		
		bL.add(Box.createVerticalStrut(10));
		bL.add(bL4= Box.createHorizontalBox());
		bL4.add(lblThoiGian= new JLabel("* Chỉ áp dụng hủy vé cho chuyến tàu có thời gian khởi hành 24h trở lên"));
		lblThoiGian.setForeground(Color.RED);
		//lblTinhGia.setFont(new Font("Arial", Font.PLAIN, 12));
	

		
		//pR
		JPanel pRight = new JPanel();
		pSouth.add(pRight, BorderLayout.EAST);
		pRight.setLayout(new BorderLayout(0, 0));
		Box bR= Box.createVerticalBox();
		Box bR1= Box.createHorizontalBox();
		pSouth.add(bR);
		bR.add(Box.createVerticalStrut(10));
		bR.add(bR1);
		bR1.add(Box.createHorizontalStrut(160));
		bR1.add(btnTraVe= new JButton("Trả vé"));
		btnTraVe.setBackground(new Color(153, 255, 153));
		btnTraVe.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnTraVe.setPreferredSize(new Dimension(70,50));
		
		//dky action
		btnTim.addActionListener(this);
		btnTraVe.addActionListener(this);
		
		//
		tableChonVe.addMouseListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == btnTim) 
		{
			String tenKH = txtMa.getText().trim();
			String sdt = txtSDT.getText().trim();
			try {
				modelTV = dao_ve.TraCuuVe(tenKH, sdt);
		        if (modelTV.getRowCount() != 0) {
		        	tableTV.setModel(modelTV);
		            JOptionPane.showMessageDialog(this, "Đã tìm thấy hóa đơn đặt vé của khách hàng tên: " + tenKH);
		        } else {
		            JOptionPane.showMessageDialog(this, "Không tìm thấy đơn đặt vé");
		        }
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		} 
		else if (obj == btnTraVe)
		{
			 int ve_chon = tableChonVe.getSelectedRow();
			 String maVe = (String) tableChonVe.getValueAt(ve_chon, 2);
			 try {
				 String ngayDi= dao_ve.getNgayDi(maVe);
				 if(!validHuyVe(maVe, ngayDi))
					 return;
				 	
			 } catch (SQLException e2) {
				e2.printStackTrace();
			 }
			 String maHD = (String) tableChonVe.getValueAt(ve_chon, 0);
			 JFrame f= new JFrame();
			 int hoi= JOptionPane.showConfirmDialog(f, "Xác nhận hủy vé?","Chú ý",JOptionPane.YES_NO_OPTION);
			 if(hoi== JOptionPane.YES_OPTION) {
				 try {
					    String[] thongTinGhe = dao_ve.LayThongTinGheTuMaVe(maVe);
					    
			            String maGhe = thongTinGhe[0];
			            String maToa = thongTinGhe[1];
			            String maTau = thongTinGhe[2];
			            String maLichTrinh = dao_ve.laymaLichTrinh(maVe, maGhe, maToa, maTau);
					if(dao_ve.HuyVeTheoMaHD(maHD, maVe, maToa, maTau, maGhe, maLichTrinh)) {;
						modelChonVe.removeRow(ve_chon);
						JOptionPane.showMessageDialog(this, "Hủy thành công vé !!! ");
						
					}
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				
					}
			 	}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableChonVe.getSelectedRow();
		String maHD = tableChonVe.getValueAt(row, 0).toString();
		try {
			txtGiaVe.setText(dao_ve.laygiaveTheoMaHD(maHD));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	public void updateTongTienVe() {
		double tong=0;
		try 
		{
			for(int i=0; i< tableChonVe.getRowCount(); i++)
				tong+= Double.valueOf((dao_ve.laygiaveTheoMaHD(tableChonVe.getValueAt(i, 0).toString())));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtTienTra.setForeground(Color.red);
		txtPhi.setForeground(Color.red);
		txtTong.setText(String.valueOf(tong));
		txtPhi.setText(String.valueOf(tong*0.1));
		txtTienTra.setText(String.valueOf(tong*0.9));
	}
	public boolean validHuyVe(String maVe, String ngayDi) {
		LocalDate ngay = LocalDate.parse(ngayDi);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDate now = LocalDate.now();  
		if(ngay.isBefore(now))
		{
			JOptionPane.showMessageDialog(null, "Không thể hủy vé: "+ maVe+"\nChuyến tàu đã khởi hành "+ ngayDi, "Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		if(ngay.equals(now))
		{
			JOptionPane.showMessageDialog(null, "Vé phải được hủy 24h trước khởi hành: "+ maVe+"\nChuyến tàu sẽ khởi hành "+ ngayDi, "Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			return false;
		}
		return true;

	}
}
