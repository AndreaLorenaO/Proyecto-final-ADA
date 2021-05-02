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
import org.springframework.web.bind.annotation.RestController;

import com.ada.model.Agent;
import com.ada.model.Organization;
import com.ada.payload.request.OrganizationRequest;
import com.ada.service.AgentService;
import com.ada.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/org")
public class OrganizationController {

	@Autowired
	OrganizationService orgService;

	@Autowired
	AgentService agentService;

	@GetMapping("/")
	@Operation(description = "List of all the organizations registered in the app")
	public Iterable<Organization> getAllOrganization() {
		return orgService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(description = "This method return an organization according to a given id")
	public ResponseEntity<Organization> getOrganization(@PathVariable("id") Long orgId) {
		Optional<Organization> orgList = orgService.findById(orgId);
		if (orgList.isPresent()) {
			return new ResponseEntity<>(orgList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@Operation(description = "This method records an object-organization in the database")
	public ResponseEntity<Organization> createOrganization(@Valid @RequestBody OrganizationRequest orgRequest) {
		try {
			Optional<Agent> orgAgent = agentService.findById(orgRequest.getUserId());
			Agent agent = orgAgent.get();
			Organization newOrganization = orgService
					.save(new Organization(orgRequest.getOrgName(), orgRequest.getOrgCuil(), orgRequest.getOrgType(),
							orgRequest.getOrgAddress(), orgRequest.getOrgCateg(), orgRequest.getOrgFoundationYear(),
							orgRequest.getOrgContactNumber(), orgRequest.setAgent(agent)));
			return new ResponseEntity<>(newOrganization, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/accepted/{id}")
	@Operation(description = "This method accepts an organization by the admin")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Organization> acceptedOrganization(@PathVariable("id") Long orgId) {
		try {
			Optional<Organization> organization = orgService.findById(orgId);
			Organization acceptedOrganization = organization.get();
			acceptedOrganization.setAccepted(true);
			orgService.save(acceptedOrganization);
			return new ResponseEntity<>(acceptedOrganization, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/{id}")
	@Operation(description = "This method updates an object-organization in the database")
	public ResponseEntity<Organization> updateOrganization(@Valid @PathVariable("id") Long orgId,
			@RequestBody OrganizationRequest orgRequest) {
		Optional<Organization> orgInfo = orgService.findById(orgId);
		if (orgInfo.isPresent()) {
			Organization orgUpdate = orgInfo.get();
			orgUpdate.setOrgName(orgRequest.getOrgName());
			orgUpdate.setOrgCuil(orgRequest.getOrgCuil());
			orgUpdate.setOrgType(orgRequest.getOrgType());
			orgUpdate.setOrgAddress(orgRequest.getOrgAddress());
			orgUpdate.setOrgCateg(orgRequest.getOrgCateg());
			orgUpdate.setOrgFoundationYear(orgRequest.getOrgFoundationYear());
			orgUpdate.setOrgContactNumber(orgRequest.getOrgContactNumber());
			return new ResponseEntity<>(orgService.save(orgUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Organization> deleteOrg(@PathVariable("id") Long orgId) {
		try {
			orgService.deleteById(orgId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
