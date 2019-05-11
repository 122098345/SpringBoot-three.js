package cn.edu.ynnu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class yh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int yh_id;
	@Column(name = "yh_username")
	private String yh_username;

	@Column(name = "yh_password")
	private String yh_password;

	@Column(name = "yh_phone")
	private String yh_phone;

	@Column(name = "yh_email")
	private String yh_email;

	@Column(name = "yh_nc")
	private String yh_nc;

	@Column(name = "yh_zcdate")
	private String yh_zcdate;

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public String getYh_username() {
		return yh_username;
	}

	public void setYh_username(String yh_username) {
		this.yh_username = yh_username;
	}

	public String getYh_password() {
		return yh_password;
	}

	public void setYh_password(String yh_password) {
		this.yh_password = yh_password;
	}

	public String getYh_phone() {
		return yh_phone;
	}

	public void setYh_phone(String yh_phone) {
		this.yh_phone = yh_phone;
	}

	public String getYh_email() {
		return yh_email;
	}

	public void setYh_email(String yh_email) {
		this.yh_email = yh_email;
	}

	public String getYh_nc() {
		return yh_nc;
	}

	public void setYh_nc(String yh_nc) {
		this.yh_nc = yh_nc;
	}

	public String getYh_zcdate() {
		return yh_zcdate;
	}

	public void setYh_zcdate(String yh_zcdate) {
		this.yh_zcdate = yh_zcdate;
	}

}
