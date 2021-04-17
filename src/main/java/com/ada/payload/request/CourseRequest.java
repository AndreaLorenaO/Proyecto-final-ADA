package com.ada.payload.request;

import com.ada.model.Organization;

public class CourseRequest {

	private String courseName;
	private String courseDescrip;
	private String courseMode;
	private float coursePrice;
	private int courseHours;
	private String courseCateg;
	private int maxQuota;
	private int scholarshipsQuota;
	private int courseYear;
	private Long orgId;
	private Organization org;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Organization getOrg() {
		return org;
	}

	public Organization setOrg(Organization org) {
		return this.org = org;
	}

}
