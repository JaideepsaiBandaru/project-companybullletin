package com;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity(name = "clinets")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String username;

	private String password;
	private String firstName;
	private String lastName;
	private String mnumber;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CompanyPost> posts;

	public User() {

	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMnumber() {
		return mnumber;
	}


	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}


	public List<CompanyPost> getPosts() {
		return posts;
	}


	public void setPosts(List<CompanyPost> posts) {
		this.posts = posts;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", posts=" + posts + "]";
	}



	

	


}

