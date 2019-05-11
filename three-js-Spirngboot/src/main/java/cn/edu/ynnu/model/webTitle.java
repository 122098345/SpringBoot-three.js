package cn.edu.ynnu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class webTitle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer t_id;
	@Column(name = "t_title")
	private String t_title;

	@Column(name = "t_date")
	private Date t_date;

	@Column(name = "t_level")
	private String t_level;

	@Column(name = "t_Content")
	private String t_Content;

	@Column(name = "czy_id")
	private Integer czy_id;

	@Column(name = "czy_name")
	private String czy_name;

	public Integer getT_id() {
		return t_id;
	}

	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}

	public Date getT_date() {
		return t_date;
	}

	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}

	public String getT_level() {
		return t_level;
	}

	public void setT_level(String t_level) {
		this.t_level = t_level;
	}

	public String getT_Content() {
		return t_Content;
	}

	public void setT_Content(String t_Content) {
		this.t_Content = t_Content;
	}

	public Integer getCzy_id() {
		return czy_id;
	}

	public void setCzy_id(Integer czy_id) {
		this.czy_id = czy_id;
	}

	public String getCzy_name() {
		return czy_name;
	}

	public void setCzy_name(String czy_name) {
		this.czy_name = czy_name;
	}

}
