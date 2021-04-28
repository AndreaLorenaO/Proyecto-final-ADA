package com.ada.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Enrollment {

	@Id
	@Column(name = "id_enrollment")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int enrollmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_student")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_course")
	private Course course;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "enrollment_payment", joinColumns = @JoinColumn(name = "id_enrollment"), inverseJoinColumns = @JoinColumn(name = "id_payment_method"))
	private Set<PaymentMethod> paymentMethods = new HashSet<>();

	public Enrollment() {
	}

	public Enrollment(Student student, Course course) {
		this.student = student;
		this.course = course;
	}

	public int getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Student getStudent() {
		return student;
	}

	public Student setStudent(Student student) {
		return this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public Course setCourse(Course course) {
		return this.course = course;
	}

	public Set<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

}
