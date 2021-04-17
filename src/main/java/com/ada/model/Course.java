
package com.ada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
public class Course {

	@Id
	@Column(name = "id_course")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	@NotNull
	@Column(name = "name")
	private String courseName;

	@NotNull
	@Column(name = "description")
	private String courseDescrip;

	@NotNull
	@Column(name = "mode")
	private String courseMode;

	@NotNull
	@Column(name = "price")
	private float coursePrice;

	@NotNull
	@Column(name = "hours")
	private int courseHours;

	@NotNull
	@Column(name = "category")
	private String courseCateg;

	@NotNull
	@Column(name = "max_quota")
	private int maxQuota;

	@NotNull
	@Column(name = "scholarships_quota")
	private int scholarshipsQuota;

	@NotNull
	@Column(name = "year")
	private int courseYear;

	@ManyToOne
	@JoinColumn(name = "id_organization", nullable = false)
	@JsonBackReference
	private Organization organization;

	public Course() {
	}

	public Course(@NotNull String courseName, @NotNull String courseDescrip, @NotNull String courseMode,
			@NotNull float coursePrice, @NotNull int courseHours, @NotNull String courseCateg, @NotNull int maxQuota,
			@NotNull int scholarshipsQuota, @NotNull int courseYear, Organization organization) {
		this.courseName = courseName;
		this.courseDescrip = courseDescrip;
		this.courseMode = courseMode;
		this.coursePrice = coursePrice;
		this.courseHours = courseHours;
		this.courseCateg = courseCateg;
		this.maxQuota = maxQuota;
		this.scholarshipsQuota = scholarshipsQuota;
		this.courseYear = courseYear;
		this.organization = organization;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescrip() {
		return courseDescrip;
	}

	public void setCourseDescrip(String courseDescrip) {
		this.courseDescrip = courseDescrip;
	}

	public String getCourseMode() {
		return courseMode;
	}

	public void setCourseMode(String courseMode) {
		this.courseMode = courseMode;
	}

	public float getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(float coursePrice) {
		this.coursePrice = coursePrice;
	}

	public int getCourseHours() {
		return courseHours;
	}

	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}

	public String getCourseCateg() {
		return courseCateg;
	}

	public void setCourseCateg(String courseCateg) {
		this.courseCateg = courseCateg;
	}

	public int getMaxQuota() {
		return maxQuota;
	}

	public void setMaxQuota(int maxQuota) {
		this.maxQuota = maxQuota;
	}

	public int getScholarshipsQuota() {
		return scholarshipsQuota;
	}

	public void setScholarshipsQuota(int scholarshipsQuota) {
		this.scholarshipsQuota = scholarshipsQuota;
	}

	public int getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(int courseYear) {
		this.courseYear = courseYear;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
