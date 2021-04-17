package com.ada.payload.request;

import com.ada.model.User;

public class AgentRequest {

	private String agentName;
	private String agentLastname;
	private String agentDocumentType;
	private int agentDocumentNumber;
	private String agentEmail;
	private String agentPosition;
	private Long userId;
	private User user;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentLastname() {
		return agentLastname;
	}

	public void setAgentLastname(String agentLastname) {
		this.agentLastname = agentLastname;
	}

	public String getAgentDocumentType() {
		return agentDocumentType;
	}

	public void setAgentDocumentType(String agentDocumentType) {
		this.agentDocumentType = agentDocumentType;
	}

	public int getAgentDocumentNumber() {
		return agentDocumentNumber;
	}

	public void setAgentDocumentNumber(int agentDocumentNumber) {
		this.agentDocumentNumber = agentDocumentNumber;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	public String getAgentPosition() {
		return agentPosition;
	}

	public void setAgentPosition(String agentPosition) {
		this.agentPosition = agentPosition;
	}

	public User getUser() {
		return user;
	}

	public User setUser(User user) {
		return this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
