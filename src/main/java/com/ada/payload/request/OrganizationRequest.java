package com.ada.payload.request;

import com.ada.model.Agent;

public class OrganizationRequest {

	private String orgName;
	private int orgCuil;
	private String orgType;
	private String orgAddress;
	private String orgCateg;
	private int orgFoundationYear;
	private int orgContactNumber;
	private Long userId;
	private Agent agent;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Agent getAgent() {
		return agent;
	}

	public Agent setAgent(Agent agent) {
		return this.agent = agent;
	}

}
