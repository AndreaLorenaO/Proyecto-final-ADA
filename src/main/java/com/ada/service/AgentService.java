package com.ada.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Agent;
import com.ada.repository.AgentRepo;

@Service
public class AgentService {

	@Autowired
	AgentRepo agentRepo;

	public Iterable<Agent> findAll() {
		return this.agentRepo.findAll();
	}

	public Optional<Agent> findById(Long id) {
		return this.agentRepo.findById(id);
	}

	public Agent save(Agent agent) {
		return this.agentRepo.save(agent);
	}

	public void deleteById(Long id) {
		Optional<Agent> entity = agentRepo.findById(id);
		agentRepo.delete(entity.get());
	}

}
