package com.ss.academy.java.model.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ss.academy.java.model.rating.Rating;

@Entity
@Table(name = "users")
@XmlRootElement(name = "user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 50)
	@Column(name = "username", unique = true)
	@NotNull
	private String username;

    @Size(min = 6, max = 60)
	@Column(name = "password")
	@NotNull
	private String password;

	@Size(min = 3, max = 50)
	@Column(name = "first_name")
	@NotNull
	private String firstName;

	@Size(min = 3, max = 50)
	@Column(name = "last_name")
	@NotNull
	private String lastName;

	@Size(min = 3, max = 50)
	@Column(name = "email")
	@NotNull
	private String email;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	private List<Rating> ratings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}