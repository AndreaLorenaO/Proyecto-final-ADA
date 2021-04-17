package com.ada.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Agent {

	@Id
	@Column
	private Long id;

	@NotNull
	@Column(name = "name")
	private String agentName;

	@NotNull
	@Column(name = "lastname")
	private String agentLastname;

	@NotNull
	@Column(name = "document_type")
	private String agentDocumentType;

	@NotNull
	@Column(name = "document_number")
	private int agentDocumentNumber;

	@NotNull
	@Email
	@Column(name = "email")
	private String agentEmail;

	@NotNull
	@Column(name = "position")
	private String agentPosition;

	// @OneToOne indicates that the PK of User would be used as the PK of Agent
	// To indicate exactly that we use @MapsId
	// Every Agent will have an user, but not every user will be an agent
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name = "id")
	private User user;

	public Agent() {
	}

	public Agent(@NotNull String agentName, @NotNull String agentLastname, @NotNull String agentDocumentType,
			@NotNull int agentDocumentNumber, @NotNull @Email String agentEmail, @NotNull String agentPosition,
			User agent) {
		this.agentName = agentName;
		this.agentLastname = agentLastname;
		this.agentDocumentType = agentDocumentType;
		this.agentDocumentNumber = agentDocumentNumber;
		this.agentEmail = agentEmail;
		this.agentPosition = agentPosition;
		this.user = agent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAgent() {
		return user;
	}

	public User setAgent(User agent) {
		return this.user = agent;
	}

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

}
