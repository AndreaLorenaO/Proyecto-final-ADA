package com.ada.payload.request;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class ScholarshipRequest {

	@NotNull
	private Long studentId;

	@NotNull
	private Set<String> paymentMethod;

	@NotNull
	private Long courseId;

	@NotNull
	private boolean studies;

	@NotNull
	private boolean works;

	@NotNull
	private boolean income;

	@NotNull
	private int monthlyincome;

	@NotNull
	private boolean dependents;

	@NotNull
	private int numberOfDependents;

	private boolean approved;

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

	public Set<String> getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(Set<String> paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
