package com.ada.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
public class Organization {

	@Id
	@Column(name = "id_organization")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orgId;

	@NotNull
	@Column(name = "name")
	private String orgName;

	@NotNull
	@Column(name = "cuil")
	private int orgCuil;

	@NotNull
	@Column(name = "type_org")
	private String orgType;

	@NotNull
	@Column(name = "address")
	private String orgAddress;

	@NotNull
	@Column(name = "category")
	private String orgCateg;

	@NotNull
	@Column(name = "year_of_foundation")
	private int orgFoundationYear;

	@NotNull
	@Column(name = "contact_number")
	private int orgContactNumber;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_agent", nullable = false)
	private Agent agent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	@JsonManagedReference
	List<Course> courses;

	private boolean accepted;

	public Organization() {
	}

	public Organization(@NotNull String orgName, @NotNull int orgCuil, @NotNull String orgType,
			@NotNull String orgAddress, @NotNull String orgCateg, @NotNull int orgFoundationYear,
			@NotNull int orgContactNumber, Agent agent) {
		this.orgName = orgName;
		this.orgCuil = orgCuil;
		this.orgType = orgType;
		this.orgAddress = orgAddress;
		this.orgCateg = orgCateg;
		this.orgFoundationYear = orgFoundationYear;
		this.orgContactNumber = orgContactNumber;
		this.agent = agent;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public int getOrgCuil() {
		return orgCuil;
	}

	public void setOrgCuil(int orgCuil) {
		this.orgCuil = orgCuil;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getOrgCateg() {
		return orgCateg;
	}

	public void setOrgCateg(String orgCateg) {
		this.orgCateg = orgCateg;
	}

	public int getOrgFoundationYear() {
		return orgFoundationYear;
	}

	public void setOrgFoundationYear(int orgFoundationYear) {
		this.orgFoundationYear = orgFoundationYear;
	}

	public int getOrgContactNumber() {
		return orgContactNumber;
	}

	public void setOrgContactNumber(int orgContactNumber) {
		this.orgContactNumber = orgContactNumber;
	}

	public Agent getAgent() {
		return agent;
	}

	public Agent setAgent(Agent agent) {
		return this.agent = agent;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

}
