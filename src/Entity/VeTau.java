package Entity;

public class VeTau {
	private String maVe;
	private String tenVe;
	private double giaVe;
	private int loaiVe;
	private Tau tau;
	
	public VeTau() {
		super();
	}

	public VeTau(String maVe, String tenVe, double giaVe, int loaiVe, Tau tau) {
		super();
		this.maVe = maVe;
		this.tenVe = tenVe;
		this.giaVe = giaVe;
		this.loaiVe = loaiVe;
		this.tau = tau;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public String getTenVe() {
		return tenVe;
	}

	public void setTenVe(String tenVe) {
		this.tenVe = tenVe;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	public int getLoaiVe() {
		return loaiVe;
	}

	public void setLoaiVe(int loaiVe) {
		this.loaiVe = loaiVe;
	}

	public Tau getTau() {
		return tau;
	}

	public void setTau(Tau tau) {
		this.tau = tau;
	}

	@Override
	public String toString() {
		return "VeTau [maVe=" + maVe + ", tenVe=" + tenVe + ", giaVe=" + giaVe + ", loaiVe=" + loaiVe + ", tau=" + tau
				+ ", getMaVe()=" + getMaVe() + ", getTenVe()=" + getTenVe() + ", getGiaVe()=" + getGiaVe()
				+ ", getLoaiVe()=" + getLoaiVe() + ", getTau()=" + getTau() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
