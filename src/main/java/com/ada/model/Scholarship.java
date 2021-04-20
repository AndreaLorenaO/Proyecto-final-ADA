package com.ada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scholarship {

	@Id
	@Column(name = "id_scholarship")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scholarshipId;

//	@OneToMany
//	private Student studenId;

	private boolean studies;

	private boolean works;

	private boolean income;

	@Column(name = "monthly_income")
	private int monthlyincome;

	private boolean dependents;

	@Column(name = "number_dependents")
	private int numberOfDependents;

	private boolean approved;

//	public Student getStudenId() {
//		return studenId;
//	}
//
//	public void setStudenId(Student studenId) {
//		this.studenId = studenId;
//	}

	public int getScholarshipId() {
		return scholarshipId;
	}

	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
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

}
