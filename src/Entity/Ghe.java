package Entity;

public class Ghe {
	private String maGhe;
	private String loaiGhe;
	private int soLuongGhe;
	private String tinhTrang;
	
	public Ghe() {
		super();
	}

	public Ghe(String maGhe, String loaiGhe, int soLuongGhe, String tinhTrang) {
		super();
		this.maGhe = maGhe;
		this.loaiGhe = loaiGhe;
		this.soLuongGhe = soLuongGhe;
		this.tinhTrang = tinhTrang;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public String getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(String loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", loaiGhe=" + loaiGhe + ", soLuongGhe=" + soLuongGhe + ", tinhTrang="
				+ tinhTrang + ", getMaGhe()=" + getMaGhe() + ", getLoaiGhe()=" + getLoaiGhe() + ", getSoLuongGhe()="
				+ getSoLuongGhe() + ", getTinhTrang()=" + getTinhTrang() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
}
