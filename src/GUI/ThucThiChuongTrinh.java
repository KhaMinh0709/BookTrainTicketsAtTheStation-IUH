package GUI;

import java.awt.EventQueue;

public class ThucThiChuongTrinh {
	protected static final int DISPOSE_ON_CLOSE = 0;

	public static void main(String[] args) {
		
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				DangNhap frame = new DangNhap();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
}
