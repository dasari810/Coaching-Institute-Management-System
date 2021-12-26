package com.dbms.spark.models;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

	private int userId;

	@NotEmpty
	@Size(min=5, max=255, message = "Username must be between {min} and {max} characters long")	
	private String username;

	@Size(min = 5, message = "Password must be atleast {min} characters long")
	private String password;

	@NotEmpty
	@Email(message = "Please provide a valid email address")
	private String emailAddress;

	private String gender;

	private Date dateCreated;
	private boolean isActive;
	private boolean isEmailVerified;
	private Date lastLoginDate;
	private Time lastLoginTime;
	private String role;
	private String token;

	@NotEmpty
	private String firstName;

	private String middleName;

	@NotEmpty
	private String lastName;

	public User() {
		isActive = false;
		isEmailVerified = false;
	}

	public User(int userId, String username, String password, String firstName, String middleName, String lastName,
			String emailAddress, Date dateCreated, boolean isActive, boolean isEmailVerified, Date lastLoginDate,
			Time lastLoginTime, String role, String gender, String token) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.dateCreated = dateCreated;
		this.isActive = isActive;
		this.isEmailVerified = isEmailVerified;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginTime = lastLoginTime;
		this.role = role;
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getName() {
		return firstName + " " + middleName + " " + lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean getIsEmailVerified() {
		return this.isEmailVerified;
	}

	public void setIsEmailVerified(boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Time getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Time lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRole() {
		return role;
	}

	public String getSmallRole() {
		// Convert ROLE_ABC to abc
		return role.substring(5).toLowerCase();
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "{" + " userId='" + getUserId() + "'" + ", username='" + getUsername() + "'" + ", password='"
				+ getPassword() + "'" + ", gender='" + getGender() + "'" + ", firstName='" + getFirstName() + "'"
				+ ", middleName='" + getMiddleName() + "'" + ", lastName='" + getLastName() + "'" + ", emailAddress='"
				+ getEmailAddress() + "'" + ", dateCreated='" + getDateCreated() + "'" + ", isActive='" + getIsActive()
				+ "'" + ", lastLoginDate='" + getLastLoginDate() + "'" + ", lastLoginTime='" + getLastLoginTime() + "'"
				+ ", role='" + getRole() + "'" + ", token='" + getToken() + "'" + "}";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof User)) {
			return false;
		}
		User user = (User) o;
		return userId == user.userId && Objects.equals(username, user.username)
				&& Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName)
				&& Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName)
				&& Objects.equals(emailAddress, user.emailAddress) && Objects.equals(dateCreated, user.dateCreated)
				&& isActive == user.isActive && isEmailVerified == user.isEmailVerified
				&& Objects.equals(lastLoginDate, user.lastLoginDate)
				&& Objects.equals(lastLoginTime, user.lastLoginTime) && Objects.equals(role, user.role);
	}

}
