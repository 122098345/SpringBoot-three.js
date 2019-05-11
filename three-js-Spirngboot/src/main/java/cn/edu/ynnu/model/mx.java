package cn.edu.ynnu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class mx {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mx_id;
	
	@Column(name = "yh_id")
	private int yh_id;
	
	@Column (name = "mx_yhm")
	private String mx_yhm;
	
	@Column (name = "mx_bt")
	private String mx_bt;
	@Column (name = "mx_fl")
	private String mx_fl;
	
	@Column (name = "mx_date")
	private String mx_date;
	
	@Column (name = "mx_json")
	private String mx_json;

	public int getMx_id() {
		return mx_id;
	}

	public void setMx_id(int mx_id) {
		this.mx_id = mx_id;
	}

	public int getYh_id() {
		return yh_id;
	}

	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}

	public String getMx_yhm() {
		return mx_yhm;
	}

	public void setMx_yhm(String mx_yhm) {
		this.mx_yhm = mx_yhm;
	}

	public String getMx_bt() {
		return mx_bt;
	}

	public void setMx_bt(String mx_bt) {
		this.mx_bt = mx_bt;
	}

	public String getMx_fl() {
		return mx_fl;
	}

	public void setMx_fl(String mx_fl) {
		this.mx_fl = mx_fl;
	}

	public String getMx_date() {
		return mx_date;
	}

	public void setMx_date(String mx_date) {
		this.mx_date = mx_date;
	}

	public String getMx_json() {
		return mx_json;
	}

	public void setMx_json(String mx_json) {
		this.mx_json = mx_json;
	}
	
}
