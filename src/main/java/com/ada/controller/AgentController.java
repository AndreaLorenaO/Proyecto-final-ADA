package com.ada.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ada.model.Agent;
import com.ada.model.User;
import com.ada.payload.request.AgentRequest;
import com.ada.repository.UserRepo;
import com.ada.service.AgentService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/agent")

public class AgentController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AgentService agentService;

	@Autowired
	Agent agent;

	@GetMapping("/")
	@Operation(description = "List of all the agents registered in the app")
//	@PreAuthorize("hasRole('ADMIN')")
	public @ResponseBody Iterable<Agent> getAllAgent() {
		return agentService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(description = "This method return an agent according to a given id")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Agent> getAgent(@PathVariable("id") Long id) {
		Optional<Agent> agentList = agentService.findById(id);

		if (agentList.isPresent()) {
			return new ResponseEntity<>(agentList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@Operation(description = "This method records an object-agent in the database")
	public ResponseEntity<Agent> createAgent(@Valid @RequestBody AgentRequest agentRequest) {
		try {
			Optional<User> userAgent = userRepo.findById(agentRequest.getUserId());
			User user = userAgent.get();
			Agent newAgent = agentService.save(new Agent(agentRequest.getAgentName(), agentRequest.getAgentLastname(),
					agentRequest.getAgentDocumentType(), agentRequest.getAgentDocumentNumber(),
					agentRequest.getAgentEmail(), agentRequest.getAgentPosition(), agentRequest.setUser(user)));
			return new ResponseEntity<>(newAgent, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Operation(description = "This method updates the information related to an object-agent in the database according to a given id")
	public ResponseEntity<Agent> updateAgent(@PathVariable("id") Long id, @RequestBody AgentRequest agentRequest) {
		Optional<Agent> agentInfo = agentService.findById(id);

		if (agentInfo.isPresent()) {
			Agent agentUpdate = agentInfo.get();
			agentUpdate.setAgentName(agentRequest.getAgentName());
			agentUpdate.setAgentLastname(agentRequest.getAgentLastname());
			agentUpdate.setAgentDocumentType(agentRequest.getAgentDocumentType());
			agentUpdate.setAgentDocumentNumber(agentRequest.getAgentDocumentNumber());
			agentUpdate.setAgentEmail(agentRequest.getAgentEmail());
			agentUpdate.setAgentPosition(agentRequest.getAgentPosition());
			return new ResponseEntity<>(agentService.save(agentUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Agent> deleteStudent(@PathVariable("id") Long id) {
		try {
			agentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
