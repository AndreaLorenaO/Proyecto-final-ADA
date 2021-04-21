package com.ada.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Course;
import com.ada.model.EPaymentMethods;
import com.ada.model.Enrollment;
import com.ada.model.PaymentMethod;
import com.ada.model.Student;
import com.ada.payload.request.EnrollmentRequest;
import com.ada.repository.EnrollmentRepo;
import com.ada.repository.PaymentMethodRepo;

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

		strPaymentMethods.forEach(paymentMethod -> {
			switch (paymentMethod) {
			case "direct_payment":
				PaymentMethod pagoDirecPayMethod = paymentMethodRepo.findByName(EPaymentMethods.DIRECT_PAYMENT)
						.orElseThrow(() -> new RuntimeException("Error: Payment method is not found."));
				paymentMethods.add(pagoDirecPayMethod);
				break;
			case "scholarship_50":
				PaymentMethod pagoBeca50 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_50)
						.orElseThrow(() -> new RuntimeException("Error: Payment method is not found."));
				paymentMethods.add(pagoBeca50);
				break;
			case "scholarship_75":
				PaymentMethod pagoBeca75 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_75)
						.orElseThrow(() -> new RuntimeException("Error: Payment method is not found."));
				paymentMethods.add(pagoBeca75);
				break;
			case "scholarship_100":
				PaymentMethod pagoBeca100 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_100)
						.orElseThrow(() -> new RuntimeException("Error: Payment method is not found."));
				paymentMethods.add(pagoBeca100);
				break;
			}
		});
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

	// code in progress
	public boolean checkStudentStatus(Enrollment enrollment) {
		Student student = enrollment.getStudent();

		return false;
	}

	public void updateScholarshipQuota(Enrollment enrollment) {
		Course course = enrollment.getCourse();
		int scholarshipQuota = course.getScholarshipsQuota();
		course.setScholarshipsQuota(scholarshipQuota - 1);
		courseService.save(course);
	}

}
