package com.ada.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.ada.model.Scholarship;
import com.ada.payload.request.ScholarshipRequest;
import com.ada.repository.ScholarshipRepo;
import com.ada.service.ScholarshipService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/scholarship")
public class ScholarshipController {

	@Autowired
	ScholarshipService scholarshipService;

	@Autowired
	ScholarshipRepo scholarshipRepo;

	Log log = LogFactory.getLog(ScholarshipController.class);

	@GetMapping("/")
	public Iterable<Scholarship> getAllScholarship() {
		return scholarshipService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Scholarship> getScholarship(@PathVariable("id") int scholarshipId) {
		Optional<Scholarship> scholarshipList = scholarshipService.findById(scholarshipId);
		if (scholarshipList.isPresent()) {
			return new ResponseEntity<>(scholarshipList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
//	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<Scholarship> registerScholarship(@Valid @RequestBody ScholarshipRequest scholarshipRequest) {
		try {
			Optional<Scholarship> scholarshipOp = scholarshipRepo.findByStudentId(scholarshipRequest.getStudentId());
			if (scholarshipOp.isPresent()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				Scholarship scholarship = scholarshipService.createScholarshipRequest(scholarshipRequest);
				return new ResponseEntity<>(scholarship, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/accepted/{id}")
	@Operation(description = "This method accepts a scholarship by the admin")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Scholarship> acceptedScholarship(@PathVariable("id") int scholarshipId) {
		try {
			Optional<Scholarship> scholarship = scholarshipService.findById(scholarshipId);
			Scholarship acceptedScholarship = scholarship.get();
			acceptedScholarship.setApproved(true);
			scholarshipService.save(acceptedScholarship);
			return new ResponseEntity<>(acceptedScholarship, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Scholarship> updateScholarship(@Valid @PathVariable("id") int scholarshipId,
			@RequestBody ScholarshipRequest scholarshipRequest) {
		try {
			Optional<Scholarship> scholarshipInfo = scholarshipService.findById(scholarshipId);
			if (scholarshipInfo.isPresent()) {
				Scholarship scholarshipUpdate = scholarshipService.updateScholarship(scholarshipInfo,
						scholarshipRequest);
				return new ResponseEntity<>(scholarshipUpdate, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{id}")
	@Operation(description = "This method delete the selected scholarship")
	public ResponseEntity<Scholarship> deleteScholarship(@PathVariable("id") int scholarshipId) {
		try {
			scholarshipService.deleteById(scholarshipId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
