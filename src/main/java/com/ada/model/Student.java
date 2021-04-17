package com.ada.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Student {

	@Id
	private Long id;

	@NotNull
	@Column(name = "name")
	private String studentName;

	@NotNull
	@Column(name = "lastname")
	private String studentLastname;

	@NotNull
	@Column(name = "birth_date")
	private String studentBirthDate;

	@NotNull
	@Column(name = "country")
	private String studentCountry;

	@NotNull
	@Column(name = "document_type")
	private String studentDocumentType;

	@NotNull
	@Column(name = "document_number")
	private int studentDocumentNumber;

	@OneToMany(mappedBy = "student")
	Set<Enrollment> courseEnrollment;

	// @OneToOne indicates that the PK of User would be used as the PK of Student
	// To indicate exactly that we use @MapsId
	// Every Student will have an user, but not every user will be a student
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	public Student() {
	}

	public Student(@NotNull String studentName, @NotNull String studentLastname, @NotNull String studentBirthDate,
			@NotNull String studentCountry, @NotNull String studentDocumentType, @NotNull int studentDocumentNumber,
			User student) {
		this.studentName = studentName;
		this.studentLastname = studentLastname;
		this.studentBirthDate = studentBirthDate;
		this.studentCountry = studentCountry;
		this.studentDocumentType = studentDocumentType;
		this.studentDocumentNumber = studentDocumentNumber;
		this.user = student;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public User getStudent() {
		return user;
	}

	public User setStudent(User student) {
		return this.user = student;
	}

}
