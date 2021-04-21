package com.ada.payload.request;

import com.ada.model.Course;
import com.ada.model.Student;

public class ScholarshipRequest {

	private Long studentId;

	private Long courseId;

	private boolean studies;

	private boolean works;

	private boolean income;

	private int monthlyincome;

	private boolean dependents;

	private int numberOfDependents;

	private boolean approved;

	private Student student;

	private Course course;

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

	public boolean isStudies() {
		return studies;
	}

	public void setStudies(boolean studies) {
		this.studies = studies;
	}

	public boolean isWorks() {
		return works;
	}

	public void setWorks(boolean works) {
		this.works = works;
	}

	public boolean isIncome() {
		return income;
	}

	public void setIncome(boolean income) {
		this.income = income;
	}

	public int getMonthlyincome() {
		return monthlyincome;
	}

	public void setMonthlyincome(int monthlyincome) {
		this.monthlyincome = monthlyincome;
	}

	public boolean isDependents() {
		return dependents;
	}

	public void setDependents(boolean dependents) {
		this.dependents = dependents;
	}

	public int getNumberOfDependents() {
		return numberOfDependents;
	}

	public void setNumberOfDependents(int numberOfDependents) {
		this.numberOfDependents = numberOfDependents;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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

}
