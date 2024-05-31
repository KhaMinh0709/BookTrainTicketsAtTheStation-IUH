package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAO_TAIKHOAN;
import Entity.TaiKhoan;
import connectDB.ConnectDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class DangNhap extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	private JLabel lblAnh;
	private JLabel lbltieuDe;
	private JLabel lbluser,lblbackground;
	private JLabel lblpass;
	private JPanel paneluser;
	private JPanel panelpass;
	private JLabel imageuser;
	private JLabel imagepass;
	public static JButton btndangNhap;
	public static JTextField txtuser;
	private JPasswordField txtpass;
	private JLabel lblxoauser,lblxoapass;
	
	private JLabel lblanh1,lblanh2;
	
	public DangNhap() throws InterruptedException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			ConnectDB.getInstance().getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DAO_TAIKHOAN dao_tk = new DAO_TAIKHOAN();
		setLayout(new BorderLayout());
		setTitle("Công Ty Đường Sắt Việt Nam");
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);	
		setSize(500,500);
		setLocationRelativeTo(null);
		JPanel pnNorth = new JPanel();
		lblAnh = new JLabel("");

		lblAnh.setIcon(new ImageIcon("icon/User-Administrator-Red-icon.png"));
		pnNorth.add(lblAnh);
		add(pnNorth,BorderLayout.NORTH);
		 pnNorth = new JPanel() { };
			
			lblAnh = new JLabel("");

			lblAnh.setIcon(new ImageIcon("Icon/User-Administrator-Red-icon.png"));
			pnNorth.add(lblAnh);
			add(pnNorth,BorderLayout.NORTH);
		
		
			JPanel pnCenter = new JPanel();
			pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
			JPanel pnThongTin = new JPanel();
	
			
			pnThongTin.setLayout(new BoxLayout(pnThongTin, BoxLayout.Y_AXIS));
			pnCenter.add(pnThongTin);
			
			add(pnCenter,BorderLayout.CENTER);
			
			JPanel pnAo = new JPanel();
			pnThongTin.add(pnAo);
		
			
			JPanel pnMaNV = new JPanel();
			JLabel lblmaNV = new JLabel("Mã nhân viên");
			lblmaNV.setFont(new Font("Tahoma", Font.BOLD, 25));
			pnMaNV.add(lblmaNV);
			pnThongTin.add(pnMaNV);
			
			JPanel pnUser = new JPanel();
			JLabel imageuser = new JLabel("");
			imageuser.setIcon(new ImageIcon("Icon/user.PNG"));
		
			
			txtuser = new JTextField(17);
			txtuser.setFont(new Font("Tahoma", Font.BOLD, 25));
			pnUser.add(imageuser);
			pnUser.add(txtuser);
			
			pnThongTin.add(pnUser);
		
			
			
			JPanel pnMK = new JPanel();
			JLabel lblMK = new JLabel("Mật khẩu");
			lblMK.setFont(new Font("Tahoma", Font.BOLD, 25));
			pnMK.add(lblMK);
			pnThongTin.add(pnMK);
			
			JPanel pnpass = new JPanel();
			JLabel imagepass = new JLabel("");
			imagepass.setIcon(new ImageIcon("Icon/pass.PNG"));
			
			
			txtpass = new JPasswordField(10);
			txtpass.setFont(new Font("Tahoma", Font.BOLD, 25));
			
			pnpass.add(imagepass);
			pnpass.add(txtpass);
			pnThongTin.add(pnpass);

		
	
		txtuser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtuser.getText().length()>0) {
					lblxoauser.setVisible(true);
				}
				else {
					lblxoauser.setVisible(false);
				}
			}
		});
		txtuser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtuser.setBackground(Color.WHITE);
	
		txtuser.setBorder(null);
//		pnUser.add(txtuser);
	
		
		JPanel pnDN = new JPanel();
		btndangNhap = new JButton("Đăng nhập");
		pnDN.add(btndangNhap);
		pnThongTin.add(pnDN);
		btndangNhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = txtuser.getText();
				@SuppressWarnings("deprecation")
				String password = txtpass.getText();
				TaiKhoan tk = null;
				try {
					tk = dao_tk.Login(username, password);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(tk != null) {
					try {
						new ChucNang(username).setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					close();

				}
				else {
					JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ", "Wrong !", JOptionPane.ERROR_MESSAGE, null);
				}
			}
		});
		
		
		btndangNhap.setForeground(SystemColor.inactiveCaptionBorder);
		btndangNhap.setBackground(new Color(0, 153, 255));
		btndangNhap.setFont(new Font("Tahoma", Font.BOLD, 25));
		btndangNhap.setBounds(155, 469, 202, 49);
		
		
		txtpass.setBorder(null);
		
		pnpass.add(txtpass);
		
		JPanel pnxoauser = new JPanel();
		lblxoauser = new JLabel("");
		
		lblxoauser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtuser.setText("");
				txtuser.requestFocus();
				lblxoauser.setVisible(false);
			}
		});
	}
	private void close() {
		WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
	}

	
}
