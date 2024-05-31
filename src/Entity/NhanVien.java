package Entity;

import java.sql.Date;

public class NhanVien {
	private String maNV;
	private String hoTen;
	private Date ngaySinh;
	private String cCCD;
	private String diaChi;
	private String soDienThoai;
	private String email;
	private String caLam;
	private String trangThai;
	private Date ngayVaoLam;
	private String gioiTinh;
	
	public NhanVien() {
		super();
	}

	
	public NhanVien(String maNhanVien, String tenNhanVien) {
		// TODO Auto-generated constructor stub
		this.maNV = maNhanVien;
		this.hoTen = tenNhanVien;
	}
	


	public NhanVien(String maNV, String hoTen, Date ngaySinh, String cCCD, String diaChi, String soDienThoai,
			String email, String caLam, String trangThai, Date ngayVaoLam, String gioiTinh) {
		super();
		this.maNV = maNV;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.cCCD = cCCD;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.caLam = caLam;
		this.trangThai = trangThai;
		this.ngayVaoLam = ngayVaoLam;
		this.gioiTinh = gioiTinh;
	}


	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getHoTen() {
		return hoTen;
	}


	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public Date getNgaySinh() {
		return ngaySinh;
	}


	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
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


	public String getSoDienThoai() {
		return soDienThoai;
	}


	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCaLam() {
		return caLam;
	}


	public void setCaLam(String caLam) {
		this.caLam = caLam;
	}


	public String getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}


	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
}
