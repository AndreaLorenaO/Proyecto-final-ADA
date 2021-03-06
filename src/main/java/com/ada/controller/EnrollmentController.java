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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.model.Enrollment;
import com.ada.payload.request.EnrollmentRequest;
import com.ada.repository.EnrollmentRepo;
import com.ada.repository.PaymentMethodRepo;
import com.ada.service.CourseService;
import com.ada.service.EnrollmentService;
import com.ada.service.StudentService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/enrollment")
public class EnrollmentController {

	@Autowired
	EnrollmentRepo enrollmentRepo;

	@Autowired
	EnrollmentService enrollmentService;

	@Autowired
	PaymentMethodRepo paymentMethodRepo;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	Log log = LogFactory.getLog(EnrollmentController.class);

	@GetMapping("/")
	public Iterable<Enrollment> getAllEnrollment() {
		return enrollmentService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Enrollment> getEnrollment(@PathVariable("id") int enrollmentId) {
		Optional<Enrollment> enrollmentList = enrollmentService.findById(enrollmentId);

		if (enrollmentList.isPresent()) {
			return new ResponseEntity<>(enrollmentList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/direct-payment")
//	@PreAuthorize("hasRole('STUDENT')")	
	public ResponseEntity<Enrollment> createEnrollmentDirect(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
		try {
			Enrollment enrollment = enrollmentService.createEnrollmentRequest(enrollmentRequest);
			if (enrollmentService.checkMaxQuota(enrollment)) {
				enrollmentService.save(enrollment);
				enrollmentService.updateMaxQuota(enrollment);
				log.info("Enrollment created");
				return new ResponseEntity<>(null, HttpStatus.CREATED);
			} else {
				log.info("There is no more quota available for the selected course");
				return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/scholarship-payment")
//@PreAuthorize("hasRole('STUDENT')")	
	public ResponseEntity<Enrollment> createEnrollmentWithScholarship(
			@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
		try {
			Enrollment enrollment = enrollmentService.createEnrollmentRequestWithScholarship(enrollmentRequest);
			if (enrollmentService.acceptedScholarship(enrollmentRequest)) {
				if (enrollmentService.checkScholarshipQuota(enrollment)) {
					enrollmentService.save(enrollment);
					enrollmentService.updateScholarshipQuota(enrollment);
					log.info("Enrollment with scholarship created");
					return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
				} else {
					log.info("There is no more quota with scholarship available for the selected course");
					return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
				}
			} else {
				log.info("The scholarship has not been accepted by the admin");
				return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("id") int enrollmentId,
//			@RequestBody EnrollmentRequest enrollmentRequest) {
//		Optional<Enrollment> enrollmentInfo = enrollmentService.findById(enrollmentId);
//		if (enrollmentInfo.isPresent()) {
//			Enrollment enrollmentUpdate = enrollmentInfo.get();
//			enrollmentUpdate.setStudent(enrollmentRequest.getStudentId());
//			return new ResponseEntity<>(enrollmentService.save(enrollmentUpdate), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Enrollment> deleteEnrollment(@PathVariable("id") int enrollmentId) {
		try {
			enrollmentService.deleteById(enrollmentId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
