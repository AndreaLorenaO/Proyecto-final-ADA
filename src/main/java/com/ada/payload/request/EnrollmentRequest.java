package com.ada.payload.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class EnrollmentRequest {

	@NotNull
	private Long studentId;

	@NotNull
	private Long courseId;

	@NotNull
	private Set<String> paymentMethod;

	private int scholarshipId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Set<String> getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Set<String> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getScholarshipId() {
		return scholarshipId;
	}

	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
	}

}
