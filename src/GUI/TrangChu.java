package GUI;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.Image;

public class TrangChu extends JPanel{
	JLabel lblBackground;
	public TrangChu() {
		// TODO Auto-generated constructor stub
		
		setVisible(true);
		setLayout(new BorderLayout(0, 0));
		
		//pnNorth
		JPanel pnNorth = new JPanel();
		pnNorth.setBackground(Color.CYAN);
		add(pnNorth, BorderLayout.NORTH);
		
		JLabel lblTieuDe = new JLabel("TỔNG CÔNG TY ĐƯỜNG SẮT VIỆT NAM");
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblTieuDe.setBackground(Color.DARK_GRAY);
		lblTieuDe.setVerticalAlignment(SwingConstants.BOTTOM);
		pnNorth.add(lblTieuDe);
	
		//pCenter
		JPanel pCenter = new JPanel();
		add(pCenter, BorderLayout.CENTER);
		ImageIcon img = new ImageIcon("icon/trangchu.jpg");
		Image img1 = img.getImage();
		Image temp_img = img1.getScaledInstance(1400,750, Image.SCALE_SMOOTH);
		img = new ImageIcon(temp_img);
		lblBackground = new JLabel("",img,JLabel.CENTER);
		lblBackground.setLayout(new BoxLayout(lblBackground, BoxLayout.Y_AXIS));
		pCenter.add(lblBackground);
		
		
	}
	public static void main(String[] args) {
		TrangChu trangchu = new TrangChu();
		trangchu.setVisible(true);
	}

}
