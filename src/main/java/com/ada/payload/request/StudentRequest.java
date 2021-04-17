package com.ada.payload.request;

import com.ada.model.User;

public class StudentRequest {

	private String studentName;
	private String studentLastname;
	private String studentBirthDate;
	private String studentCountry;
	private String studentDocumentType;
	private int studentDocumentNumber;
	private Long userId;
	private User user;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentLastname() {
		return studentLastname;
	}

	public void setStudentLastname(String studentLastname) {
		this.studentLastname = studentLastname;
	}

	public String getStudentBirthDate() {
		return studentBirthDate;
	}

	public void setStudentBirthDate(String studentBirthDate) {
		this.studentBirthDate = studentBirthDate;
	}

	public String getStudentCountry() {
		return studentCountry;
	}

	public void setStudentCountry(String studentCountry) {
		this.studentCountry = studentCountry;
	}

	public String getStudentDocumentType() {
		return studentDocumentType;
	}

	public void setStudentDocumentType(String studentDocumentType) {
		this.studentDocumentType = studentDocumentType;
	}

	public int getStudentDocumentNumber() {
		return studentDocumentNumber;
	}

	public void setStudentDocumentNumber(int studentDocumentNumber) {
		this.studentDocumentNumber = studentDocumentNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public User setUser(User user) {
		return this.user = user;
	}

}
