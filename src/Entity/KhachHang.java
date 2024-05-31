package Entity;

import java.util.Date;

import javax.xml.crypto.Data;

public class KhachHang {
	private String maKh;
	private String tenKH;
	private String gioiTinh;
	private String soDienThoai;
	private String cCCD;
	private String diaChi;
	private int loaiKH;
	private java.sql.Date ngaySinh;
	
	public KhachHang() {
		super();
	}

	public KhachHang(String maKh, String tenKH, String gioiTinh, String soDienThoai, String cCCD, String diaChi,
			int loaiKH, java.sql.Date ngaySinh) {
		super();
		this.maKh = maKh;
		this.tenKH = tenKH;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.cCCD = cCCD;
		this.diaChi = diaChi;
		this.loaiKH = loaiKH;
		this.ngaySinh = ngaySinh;
	}


	public KhachHang(String ma) {
		// TODO Auto-generated constructor stub
		super();
		this.maKh = maKh;
	}

	public String getMaKh() {
		return maKh;
		
	}



	public void setMaKh(String maKh) {
		this.maKh = maKh;
	}



	public String getTenKH() {
		return tenKH;
	}



	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}



	public String getGioiTinh() {
		return gioiTinh;
	}



	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}



	public String getSoDienThoai() {
		return soDienThoai;
	}



	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}



	public String getcCCD() {
		return cCCD;
	}



	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}



	public String getDiaChi() {
		return diaChi;
	}



	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}



	public int getLoaiKH() {
		return loaiKH;
	}



	public void setLoaiKH(int loaiKH) {
		this.loaiKH = loaiKH;
	}



	public java.sql.Date getNgaySinh() {
		return ngaySinh;
	}



	public void setNgaySinh(java.sql.Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}



	@Override
	public String toString() {
		return "KhachHang [maKh=" + maKh + ", tenKH=" + tenKH + ", gioiTinh=" + gioiTinh + ", soDienThoai="
				+ soDienThoai + ", cCCD=" + cCCD + ", diaChi=" + diaChi + ", loaiKH=" + loaiKH + ", ngaySinh="
				+ ngaySinh + ", getMaKh()=" + getMaKh() + ", getTenKH()=" + getTenKH() + ", getGioiTinh()="
				+ getGioiTinh() + ", getSoDienThoai()=" + getSoDienThoai() + ", getcCCD()=" + getcCCD()
				+ ", getDiaChi()=" + getDiaChi() + ", getLoaiKH()=" + getLoaiKH() + ", getNgaySinh()=" + getNgaySinh()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


}
