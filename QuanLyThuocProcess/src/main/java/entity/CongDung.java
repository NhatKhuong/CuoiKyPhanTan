package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CongDung {
	@Id
	@Column(columnDefinition = "nvarchar(30)")
	private String maCongDung;
	
//	@OneToMany(mappedBy = "congDung")
//	private List<Thuoc> thuocs;
	
	
	@Column(columnDefinition = "nvarchar(255)")
	private String nhomCongDung;
	@Column(columnDefinition = "nvarchar(255)")
	private String congDung;
	public CongDung() {
		// TODO Auto-generated constructor stub
	}
	public CongDung(String maCongDung, String nhomCongDung, String congDung) {
		super();
		this.maCongDung = maCongDung;
		this.nhomCongDung = nhomCongDung;
		this.congDung = congDung;
	}
	public String getMaCongDung() {
		return maCongDung;
	}
	public void setMaCongDung(String maCongDung) {
		this.maCongDung = maCongDung;
	}
	public String getNhomCongDung() {
		return nhomCongDung;
	}
	public void setNhomCongDung(String nhomCongDung) {
		this.nhomCongDung = nhomCongDung;
	}
	public String getCongDung() {
		return congDung;
	}
	public void setCongDung(String congDung) {
		this.congDung = congDung;
	}
	
}
