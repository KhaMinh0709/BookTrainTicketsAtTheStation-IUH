package GUI;

import javax.swing.JFrame;  
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.Canvas;
import java.awt.Button;
import javax.swing.border.TitledBorder;

import DAO.DAO_NHANVIEN;

import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.Window.Type;

public class ChucNang extends JFrame implements ActionListener,MouseListener{
	private JPanel pnTrangChu;
	private JTextField txtTenNV;
	private JTextField txtMaNV;
	private JButton btb_trangchu;
	private JButton btn_KhachHang;
	private JButton btn_DatVe;
	private JButton btn_thongke;
	private JButton btn_nhanvien;
	private JButton btn_dangxuat;
	private JPanel pnWest;
	private JPanel pnKhachHang;
	private JPanel pnDatVe;
	private JPanel pnThongKe;
	private JPanel pnNhanVien;
	private JPanel pnTaoLichTrinh;
	boolean flag = true;
	private JPanel pnHuyVe;
	private JButton btn_huyve;
	private DAO_NHANVIEN DAO_NHANVIEN;
	private JPanel pnTraCuu;
	private JButton btn_TraCuuVe;
	private String Manv;
	private JButton btn_TaoLichTrinh;

	
	public ChucNang(String Manv) throws SQLException {
		this.Manv = Manv;
		setTitle("Chức năng");
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		//nhan vien đang đăng nhập
		DAO_NHANVIEN = new DAO_NHANVIEN();

		
		// các panel (nhân viên, khách hàng, đặt vé...) sẽ thay thế cho pWest
		pnTrangChu = new JPanel();
		pnTrangChu = new TrangChu();
		
		pnKhachHang = new JPanel();
		pnKhachHang = new QuanLyKhachHang();
		
		pnDatVe = new JPanel();
		pnDatVe = new QuanLyDatVe(Manv);
	
		pnTaoLichTrinh = new JPanel();
		pnTaoLichTrinh = new ThemLichTrinh();
		
		pnThongKe = new JPanel();
		pnThongKe = new QuanLyThongKe();
		
		pnNhanVien = new JPanel();
		pnNhanVien = new QuanLyNhanVien();
		
		pnHuyVe = new JPanel();
		pnHuyVe = new QuanLyHuyVe();
		
		pnTraCuu = new JPanel();
		pnTraCuu = new QuanLyTraCuuVe();
		
		
		//pnEast ( gồm các nút ấn chức năng và thông tin nhân viên đang đăng nhập)
		JPanel pnEast = new JPanel();
		pnEast.setBounds(0, 0, 194, 845);
		getContentPane().add(pnEast);
		pnEast.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnEast.setLayout(null);
		
		//panel Thông tin Nvien
		JPanel pn_thongtinNhanVien = new JPanel();
		pn_thongtinNhanVien.setBackground(Color.WHITE);
		pn_thongtinNhanVien.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		pn_thongtinNhanVien.setBounds(0, 0, 194, 136);
		pnEast.add(pn_thongtinNhanVien);
		JLabel lblLogoNhanvien = new JLabel(ResizeImage("icon/NhanVien.jpg"));
		lblLogoNhanvien.setPreferredSize(new Dimension(140,70));
		pn_thongtinNhanVien.add(lblLogoNhanvien);
		
		//nhân viên đang đăng nhập
		txtTenNV = new JTextField(15);
		txtTenNV.setForeground(Color.RED);
		txtTenNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtTenNV.setEditable(false);
		txtTenNV.setText(DAO_NHANVIEN.LayNhanVienTheoMa(Manv).getHoTen().toString());
		pn_thongtinNhanVien.add(txtTenNV);
		
		txtMaNV = new JTextField(15);
		txtMaNV.setForeground(Color.RED);
		txtMaNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtMaNV.setEditable(false);
		pn_thongtinNhanVien.add(txtMaNV);

		txtMaNV.setText(Manv);
		
		//panel các nút ấn
	    JPanel pn_NutAnChucNang = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 10));
	    pn_NutAnChucNang.setBounds(1, 135, 193, 666);
	    pn_NutAnChucNang.setBackground(Color.BLUE);
	    pn_NutAnChucNang.setBorder(new LineBorder(Color.BLACK, 2));
		pnEast.add(pn_NutAnChucNang);
		
		
		btb_trangchu = new JButton(" Trang Chủ");
		btb_trangchu.setBackground(new Color(153, 255, 153));
		btb_trangchu.setFont(new Font("Tahoma", Font.BOLD, 12));
		btb_trangchu.setPreferredSize(new Dimension(175,50));
		ImageIcon icon = new ImageIcon("icon/iconTrangChu.jpg");
		Image image = icon.getImage(); 
		Image newImage = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		btb_trangchu.setIcon(icon);
		btb_trangchu.setLayout(new BorderLayout());
		pn_NutAnChucNang.add(btb_trangchu);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		btn_KhachHang = new JButton(" Khách hàng");
		btn_KhachHang.setBackground(new Color(255, 255, 255));
		btn_KhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_KhachHang.setPreferredSize(new Dimension(175,50));
		ImageIcon icon2 = new ImageIcon("icon/iconKhachHang.jpg");
		Image image2 = icon2.getImage(); 
		Image newImage2 = image2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon2 = new ImageIcon(newImage2);
		btn_KhachHang.setIcon(icon2);
		pn_NutAnChucNang.add(btn_KhachHang);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		btn_TaoLichTrinh = new JButton(" Tạo Lịch Trình");
		btn_TaoLichTrinh.setBackground(new Color(255, 255, 255));
		btn_TaoLichTrinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_TaoLichTrinh.setPreferredSize(new Dimension(175,50));
		ImageIcon icon10 = new ImageIcon("icon/taolichTrinh.jpg");
		Image image10 = icon10.getImage(); 
		Image newImage10 = image10.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon10 = new ImageIcon(newImage10);
		btn_TaoLichTrinh.setIcon(icon10);
		pn_NutAnChucNang.add(btn_TaoLichTrinh);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		btn_DatVe = new JButton("       Đặt vé");
		btn_DatVe.setBackground(new Color(255, 255, 255));
		btn_DatVe.setPreferredSize(new Dimension(175,50));
		btn_DatVe.setFont(new Font("Tahoma", Font.BOLD, 12));
		ImageIcon icon3 = new ImageIcon("icon/iconDatVe.jpg");
		Image image3 = icon3.getImage(); 
		Image newImage3 = image3.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon3 = new ImageIcon(newImage3);
		btn_DatVe.setIcon(icon3);
		pn_NutAnChucNang.add(btn_DatVe);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		

		
		
		btn_thongke = new JButton("     Thống Kê ");
		btn_thongke.setBackground(new Color(255, 255, 255));
		btn_thongke.setPreferredSize(new Dimension(175,50));
		btn_thongke.setFont(new Font("Tahoma", Font.BOLD, 12));
		ImageIcon icon5 = new ImageIcon("icon/iconThongKe.jpg");
		Image image5 = icon5.getImage(); 
		Image newImage5 = image5.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon5 = new ImageIcon(newImage5);
		btn_thongke.setIcon(icon5);
		pn_NutAnChucNang.add(btn_thongke);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		
		btn_nhanvien = new JButton("      Nhân Viên");
		btn_nhanvien.setBackground(new Color(255, 255, 255));
		btn_nhanvien.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_nhanvien.setPreferredSize(new Dimension(175,50));
		ImageIcon icon6 = new ImageIcon("icon/iconNhanVien.jpg");
		Image image6 = icon6.getImage(); 
		Image newImage6 = image6.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon6 = new ImageIcon(newImage6);
		btn_nhanvien.setIcon(icon6);
		pn_NutAnChucNang.add(btn_nhanvien);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		
		btn_huyve = new JButton("      Hủy Vé  ");
		btn_huyve.setBackground(new Color(255, 255, 255));
		btn_huyve.setPreferredSize(new Dimension(175,50));
		btn_huyve.setFont(new Font("Tahoma", Font.BOLD, 12));
		ImageIcon icon7 = new ImageIcon("icon/huyve.jpg");
		Image image7 = icon7.getImage(); 
		Image newImage7 = image7.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon7 = new ImageIcon(newImage7);
		btn_huyve.setIcon(icon7);
		pn_NutAnChucNang.add(btn_huyve);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		btn_TraCuuVe = new JButton("   Tra Cứu Vé  ");
		btn_TraCuuVe.setBackground(new Color(255, 255, 255));
		btn_TraCuuVe.setPreferredSize(new Dimension(175,50));
		btn_TraCuuVe.setFont(new Font("Tahoma", Font.BOLD, 12));
		ImageIcon icon8 = new ImageIcon("icon/iconTraCuu.png");
		Image image8 = icon8.getImage(); 
		Image newImage8 = image8.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon8 = new ImageIcon(newImage8);
		btn_TraCuuVe.setIcon(icon8);
		pn_NutAnChucNang.add(btn_TraCuuVe);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		btn_dangxuat = new JButton("Đăng Xuất   ");
		btn_dangxuat.setBackground(new Color(255, 255, 255));
		btn_dangxuat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_dangxuat.setPreferredSize(new Dimension(175,50));
		ImageIcon icon9 = new ImageIcon("icon/iconDangXuat.jpg");
		Image image9 = icon9.getImage(); 
		Image newImage9 = image9.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		icon9 = new ImageIcon(newImage9); 
		btn_dangxuat.setIcon(icon9);
		pn_NutAnChucNang.add(btn_dangxuat);
		pn_NutAnChucNang.add(Box.createHorizontalStrut(30));
		
		
		//pnWest là nơi chứa các panel( nhân viên, khách hàng, đặt vé ....) có thể thay thế cho nhau khi ấn nút
		pnWest = new JPanel();
		pnWest.setBackground(new Color(0, 255, 255));
		pnWest.setBounds(193, 0, 1347, 801);
		pnWest.add(pnTrangChu);
		getContentPane().add(pnWest);
		pnWest.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		 
		
		// add actionlistener
		btb_trangchu.addActionListener(this);
		btn_dangxuat.addActionListener(this);
		btn_DatVe.addActionListener(this);
		btn_KhachHang.addActionListener(this);
		btn_nhanvien.addActionListener(this);
		btn_thongke.addActionListener(this);
		btn_huyve.addActionListener(this);
		btn_TraCuuVe.addActionListener(this);
		btn_TaoLichTrinh.addActionListener(this);
		
		// add mouselistener
		btb_trangchu.addMouseListener(this);
		btn_dangxuat.addMouseListener(this);
		btn_DatVe.addMouseListener(this);
		btn_KhachHang.addMouseListener(this);
		btn_nhanvien.addMouseListener(this);
		btn_thongke.addMouseListener(this);
		btn_huyve.addMouseListener(this);
		btn_TraCuuVe.addMouseListener(this);
		btn_TaoLichTrinh.addMouseListener(this);
	}
	
	public ImageIcon ResizeImage(String ImagePath)
	 {
		 ImageIcon MyImage = new ImageIcon(ImagePath);
		 Image img = MyImage.getImage();
		 Image newImg = img.getScaledInstance(90, 80, Image.SCALE_SMOOTH);
		 ImageIcon image = new ImageIcon(newImg);
	     return image;
	 }

	public static void main(String[] args) throws SQLException {
		new ChucNang("123").setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if(object.equals(btb_trangchu)) {
			
			btb_trangchu.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnTrangChu);
			pnWest.revalidate();
			pnWest.repaint();
			
		}
		
		else if(object.equals(btn_KhachHang)) {
			
	
			btn_KhachHang.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnKhachHang);
			pnWest.revalidate();
			pnWest.repaint();
		}

		else if(object.equals(btn_TaoLichTrinh)) {
			
			
			btn_TaoLichTrinh.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnTaoLichTrinh);
			pnWest.revalidate();
			pnWest.repaint();
		}
		else if(object.equals(btn_DatVe)) {
			btn_DatVe.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnDatVe);
			pnWest.revalidate();
			pnWest.repaint();
		}
	
		else if(object.equals(btn_thongke)) {
			btn_thongke.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnThongKe);
			pnWest.revalidate();
			pnWest.repaint();
		}
		
		else if(object.equals(btn_nhanvien)) {
			btn_nhanvien.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnNhanVien);
			pnWest.revalidate();
			pnWest.repaint();
		}
		else if(object.equals(btn_huyve)) {
			btn_huyve.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btn_TraCuuVe.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnHuyVe);
			pnWest.revalidate();
			pnWest.repaint();
		}
		else if(object.equals(btn_TraCuuVe)) {
			btn_TraCuuVe.setBackground(new Color(153, 255, 153));
			btn_dangxuat.setBackground(Color.WHITE);
			btn_KhachHang.setBackground(Color.WHITE);
			btn_DatVe.setBackground(Color.WHITE);
			btn_thongke.setBackground(Color.WHITE);
			btn_nhanvien.setBackground(Color.WHITE);
			btb_trangchu.setBackground(Color.WHITE);
			btn_huyve.setBackground(Color.WHITE);
			btn_TaoLichTrinh.setBackground(Color.WHITE);
			
			pnWest.removeAll();
			pnWest.revalidate();
			pnWest.add(pnTraCuu);
			pnWest.revalidate();
			pnWest.repaint();
		}
		
		else if(object.equals(btn_dangxuat)) {			
			JFrame f= new JFrame();
			int hoi=JOptionPane.showConfirmDialog(f, "Bạn có chắc muốn đăng xuất?","Chú ý",JOptionPane.YES_NO_OPTION);
			if(hoi==JOptionPane.YES_OPTION) {
				try {
		
					DangNhap dn = new DangNhap();
					dn.setVisible(true);
					dispose();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		Object obj = e.getSource();
		if(flag==true) {
			if(obj.equals(btb_trangchu)) {
				btb_trangchu.setPreferredSize(new Dimension(190,70));
				btb_trangchu.revalidate();
				}
			else if(obj.equals(btn_KhachHang)) {
				btn_KhachHang.setPreferredSize(new Dimension(190,70));
				btn_KhachHang.revalidate();
				}
			else if(obj.equals(btn_TaoLichTrinh)) {
				btn_TaoLichTrinh.setPreferredSize(new Dimension(190,70));
				btn_TaoLichTrinh.revalidate();
			}
			else if(obj.equals(btn_DatVe)) {
				btn_DatVe.setPreferredSize(new Dimension(190,70));
				btn_DatVe.revalidate();
				}
			else if(obj.equals(btn_thongke)) {
				btn_thongke.setPreferredSize(new Dimension(190,70));
				btn_thongke.revalidate();
				}	
			else if(obj.equals(btn_nhanvien)) {
				btn_nhanvien.setPreferredSize(new Dimension(190,70));
				btn_nhanvien.revalidate();
				}	
			else if(obj.equals(btn_huyve)) {
				btn_huyve.setPreferredSize(new Dimension(190,70));
				btn_huyve.revalidate();
				}	
			else if(obj.equals(btn_dangxuat)) {
				btn_dangxuat.setPreferredSize(new Dimension(190,70));
				btn_dangxuat.revalidate();
				}	
			else if(obj.equals(btn_TraCuuVe)) {
				btn_TraCuuVe.setPreferredSize(new Dimension(190,70));
				btn_TraCuuVe.revalidate();
			}	
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(flag==true) {
			if(obj.equals(btb_trangchu)) {
				btb_trangchu.setPreferredSize(new Dimension(175,50));
				btb_trangchu.revalidate();
				}
			else if(obj.equals(btn_KhachHang)) {
				btn_KhachHang.setPreferredSize(new Dimension(175,50));
				btn_KhachHang.revalidate();
				}
			else if(obj.equals(btn_TaoLichTrinh)) {
				btn_TaoLichTrinh.setPreferredSize(new Dimension(175,50));
				btn_TaoLichTrinh.revalidate();
			}
			else if(obj.equals(btn_DatVe)) {
				btn_DatVe.setPreferredSize(new Dimension(175,50));
				btn_DatVe.revalidate();
				}
			else if(obj.equals(btn_thongke)) {
				btn_thongke.setPreferredSize(new Dimension(175,50));
				btn_thongke.revalidate();
				}	
			else if(obj.equals(btn_nhanvien)) {
				btn_nhanvien.setPreferredSize(new Dimension(175,50));
				btn_nhanvien.revalidate();
				}	
			else if(obj.equals(btn_huyve)) {
				btn_huyve.setPreferredSize(new Dimension(175,50));
				btn_huyve.revalidate();
				}	
			else if(obj.equals(btn_dangxuat)) {
				btn_dangxuat.setPreferredSize(new Dimension(175,50));
				btn_dangxuat.revalidate();
				}	
			else if(obj.equals(btn_TraCuuVe)) {
				btn_TraCuuVe.setPreferredSize(new Dimension(175,50));
				btn_TraCuuVe.revalidate();
			}	
		}
	}

}


