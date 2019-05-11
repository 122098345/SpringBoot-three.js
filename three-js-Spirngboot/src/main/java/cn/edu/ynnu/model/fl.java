package cn.edu.ynnu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class fl {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fl_id;
	@Column(name = "flmc")
	private String flmc;
	@Column(name = "fl_nr")
	private String fl_nr;

	public int getFl_id() {
		return fl_id;
	}

	public void setFl_id(int fl_id) {
		this.fl_id = fl_id;
	}

	public String getFlmc() {
		return flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	public String getFl_nr() {
		return fl_nr;
	}

	public void setFl_nr(String fl_nr) {
		this.fl_nr = fl_nr;
	}

}
