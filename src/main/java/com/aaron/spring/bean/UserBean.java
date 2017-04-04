package com.aaron.spring.bean;

import javax.persistence.*;


/**
 * User entity bean,
 * matches table "User" in db
 *
 *
 * @author Aaron Chang
 */
@Entity
@Table(name = "UserBean")
public class UserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "age")
	private int age;

	public UserBean() {
	}

	public UserBean(String name, String email, int age) {
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long studentId) {
		this.id = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + "]";
	}

}
