package com.ada.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ada.model.Enrollment;
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

	@GetMapping("/")
	public @ResponseBody Iterable<Enrollment> getAllEnrollment() {
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

//	@PostMapping("/direct-payment")
////	@PreAuthorize("hasRole('STUDENT')")	
//	public ResponseEntity<Enrollment> createEnrollmentDirect(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
//		Enrollment enrollment = new Enrollment();
//		enrollment = enrollmentService.addEnrollmentRequest(enrollmentRequest, enrollment);
//		enrollment = enrollmentService.paymentMethod(enrollment, enrollmentRequest);
//
//		if (enrollment.getPaymentMethods().equals(EPaymentMethods.DIRECT_PAYMENT)) {
//			if (enrollmentService.checkMaxQuota(enrollment)) {
//				enrollment.setApproved(true);
//				enrollmentService.save(enrollment);
//				try {
//					enrollmentService.updateMaxQuota(enrollment);
//				} catch (Exception e) {
//					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//				}
//			}
//			return new ResponseEntity<>(enrollment, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

//	@PostMapping("/scholarship")
////	@PreAuthorize("hasRole('STUDENT')")	
//	public ResponseEntity<Enrollment> createEnrollmentScholarsip(
//			@Valid @RequestBody EnrollmentRequest enrollmentRequest,
//			@RequestBody ScholarshipRequest scholarshipRequest) {
//		Enrollment enrollment = new Enrollment();
//		enrollment = enrollmentService.addEnrollmentRequest(enrollmentRequest, enrollment);
//		enrollment = enrollmentService.paymentMethod(enrollment, enrollmentRequest);
//
//		if (!enrollment.getPaymentMethods().equals(EPaymentMethods.DIRECT_PAYMENT)) {
//			if (enrollmentService.checkScholarshipQuota(enrollment)) {
//				if (enrollmentService.checkStudentStatus(enrollment)) {
//
//				}
//
//				enrollmentService.save(enrollment);
//				try {
//					enrollmentService.updateMaxQuota(enrollment);
//				} catch (Exception e) {
//					return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//				}
//			}
//			return new ResponseEntity<>(enrollment, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}
//	@PutMapping("/{id}")
//	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable("id") int enrollmentId,
//			@RequestBody EnrollmentUpdated enrollmentUpdated) {
//		Optional<Enrollment> enrollmentInfo = enrollmentService.findById(enrollmentId);
//
//		if (enrollmentInfo.isPresent()) {
//			Enrollment enrollmentUpdate = enrollmentInfo.get();
//			enrollmentUpdate.setAccepted(enrollmentUpdated.getAccepted());
//
//			return new ResponseEntity<>(enrollmentService.save(enrollmentUpdate), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
