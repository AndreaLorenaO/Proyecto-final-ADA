package com.ada.payload.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.ada.model.Course;
import com.ada.model.Student;

public class EnrollmentRequest {

	@NotNull
	private Long studentId;

	@NotNull
	private Long courseId;

	@NotNull
	private Set<String> paymentMethod;

	private Student student;

	private Course course;

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

}
