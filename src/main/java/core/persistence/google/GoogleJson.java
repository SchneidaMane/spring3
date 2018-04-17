package main.java.core.persistence.google;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "GOOGLEJSON")
public class GoogleJson {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "json")
	@Type(type = "text")
	private String json;

	public GoogleJson(String username, String json) {
		this.username = username;
		this.json = json;
	}

	public GoogleJson() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
