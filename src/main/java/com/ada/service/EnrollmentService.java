package com.ada.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Course;
import com.ada.model.EPaymentMethods;
import com.ada.model.Enrollment;
import com.ada.model.PaymentMethod;
import com.ada.model.Scholarship;
import com.ada.model.Student;
import com.ada.payload.request.EnrollmentRequest;
import com.ada.repository.EnrollmentRepo;
import com.ada.repository.PaymentMethodRepo;
import com.ada.repository.ScholarshipRepo;

@Service
public class EnrollmentService {

	@Autowired
	EnrollmentRepo enrollmentRepo;

	@Autowired
	PaymentMethod paymentMethod;

	@Autowired
	PaymentMethodRepo paymentMethodRepo;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	ScholarshipService scholarshipService;

	@Autowired
	ScholarshipRepo scholarshipRepo;

	Log log = LogFactory.getLog(EnrollmentService.class);

	public Iterable<Enrollment> findAll() {
		return this.enrollmentRepo.findAll();
	}

	public Optional<Enrollment> findById(int enrollmentId) {
		return this.enrollmentRepo.findById(enrollmentId);
	}

	public Enrollment save(Enrollment enrollment) {
		return this.enrollmentRepo.save(enrollment);
	}

	public void deleteById(int enrollmentId) {
		Optional<Enrollment> entity = enrollmentRepo.findById(enrollmentId);
		enrollmentRepo.delete(entity.get());
	}

	public Enrollment createEnrollmentRequest(EnrollmentRequest enrollmentRequest) {
		Optional<Student> studentOp = studentService.findById(enrollmentRequest.getStudentId());
		Student student = studentOp.get();

		Optional<Course> courseOp = courseService.findById(enrollmentRequest.getCourseId());
		Course course = courseOp.get();

		Enrollment enrollment = new Enrollment();
		enrollment.setStudent(student);
		enrollment.setCourse(course);

		Set<String> strPaymentMethods = enrollmentRequest.getPaymentMethod();
		Set<PaymentMethod> paymentMethods = new HashSet<>();

		if (strPaymentMethods == null) {
			PaymentMethod pagoDirecPayMethod = paymentMethodRepo.findByName(EPaymentMethods.DIRECT_PAYMENT)
					.orElseThrow(() -> new RuntimeException("Error: Payment method is not found"));
		} else {
			PaymentMethod pagoDirecPayMethod = paymentMethodRepo.findByName(EPaymentMethods.DIRECT_PAYMENT)
					.orElseThrow(() -> new RuntimeException("Error: Payment method is not found."));
			paymentMethods.add(pagoDirecPayMethod);
		}

		enrollment.setPaymentMethods(paymentMethods);
// 		can't save enrollment here because I still have to check the course's quota
//		enrollmentRepo.save(enrollment);
		return enrollment;
	}

	public boolean checkMaxQuota(Enrollment enrollment) {
		Course course = enrollment.getCourse();
		if (course.getMaxQuota() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public void updateMaxQuota(Enrollment enrollment) {
		Course course = enrollment.getCourse();
		int maxQuota = course.getMaxQuota();
		course.setMaxQuota(maxQuota - 1);
		courseService.save(course);
	}

	public boolean checkScholarshipQuota(Enrollment enrollment) {
		Course course = enrollment.getCourse();
		if (course.getScholarshipsQuota() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkStudentStatus(Enrollment enrollment) {
		Student student = enrollment.getStudent();

		Optional<Scholarship> scholarshipOp = scholarshipRepo.findByStudent(student);
		if (scholarshipOp.isPresent()) {
			return false;
		}
		return true;
	}

	public void updateScholarshipQuota(Enrollment enrollment) {
		Course course = enrollment.getCourse();
		int scholarshipQuota = course.getScholarshipsQuota();
		course.setScholarshipsQuota(scholarshipQuota - 1);
		courseService.save(course);
	}

	public boolean acceptedScholarship(EnrollmentRequest enrollmentRequest) {
		int scholarshipId = enrollmentRequest.getScholarshipId();
		Optional<Scholarship> scholarshipOp = scholarshipService.findById(scholarshipId);
		Scholarship scholarship = scholarshipOp.get();

		if (scholarship.isApproved()) {
			return true;
		}
		return false;
	}

	public Enrollment createEnrollmentRequestWithScholarship(@Valid EnrollmentRequest enrollmentRequest) {
		Optional<Student> studentOp = studentService.findById(enrollmentRequest.getStudentId());
		Student student = studentOp.get();

		Optional<Course> courseOp = courseService.findById(enrollmentRequest.getCourseId());
		Course course = courseOp.get();

		Optional<Scholarship> scholarshipOp = scholarshipService.findById(enrollmentRequest.getScholarshipId());
		Scholarship scholarship = scholarshipOp.get();
		Set<PaymentMethod> payMethod = scholarship.getPaymentMethods();
		Set<PaymentMethod> payMethodNew = new HashSet();
		payMethodNew.addAll(payMethod);

		Enrollment enrollment = new Enrollment();
		enrollment.setStudent(student);
		enrollment.setCourse(course);
		enrollment.setPaymentMethods(payMethodNew);
		return enrollment;
	}

}
