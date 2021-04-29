package com.ada.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Course;
import com.ada.model.EPaymentMethods;
import com.ada.model.Enrollment;
import com.ada.model.PaymentMethod;
import com.ada.model.Scholarship;
import com.ada.model.Student;
import com.ada.payload.request.ScholarshipRequest;
import com.ada.repository.PaymentMethodRepo;
import com.ada.repository.ScholarshipRepo;

@Service
public class ScholarshipService {

	@Autowired
	ScholarshipRepo scholarshipRepo;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@Autowired
	PaymentMethodRepo paymentMethodRepo;

	public Iterable<Scholarship> findAll() {
		return this.scholarshipRepo.findAll();
	}

	public Optional<Scholarship> findById(int scholarshipId) {
		return this.scholarshipRepo.findById(scholarshipId);
	}

	public Scholarship save(Scholarship scholarship) {
		return this.scholarshipRepo.save(scholarship);
	}

	public void deleteById(int scholarshipId) {
		Optional<Scholarship> entity = scholarshipRepo.findById(scholarshipId);
		scholarshipRepo.delete(entity.get());
	}

	public Scholarship createScholarshipRequest(@Valid ScholarshipRequest scholarshipRequest) {
		Optional<Student> studentOp = studentService.findById(scholarshipRequest.getStudentId());
		Student student = studentOp.get();

		Optional<Course> courseOp = courseService.findById(scholarshipRequest.getCourseId());
		Course course = courseOp.get();

		Scholarship scholarship = new Scholarship();
		scholarship.setStudent(student);
		scholarship.setCourse(course);
		scholarship.setStudies(scholarshipRequest.isStudies());
		scholarship.setWorks(scholarshipRequest.isWorks());
		scholarship.setIncome(scholarshipRequest.isIncome());
		scholarship.setMonthlyincome(scholarshipRequest.getMonthlyincome());
		scholarship.setDependents(scholarshipRequest.isDependents());
		scholarship.setNumberOfDependents(scholarshipRequest.getNumberOfDependents());

		Set<String> strPaymentMethods = scholarshipRequest.getPaymentMethod();
		Set<PaymentMethod> paymentMethods = new HashSet<>();

		if (strPaymentMethods == null) {
			PaymentMethod paymentScholarship50 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_50)
					.orElseThrow(() -> new RuntimeException("Error: Payment method is not found"));
			paymentMethods.add(paymentScholarship50);
		} else {
			strPaymentMethods.forEach(role -> {
				switch (role) {
				case "scholarship_50":
					PaymentMethod paymentScholarship50 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_50)
							.orElseThrow(() -> new RuntimeException("Error: Payment method is not found"));
					paymentMethods.add(paymentScholarship50);

					break;
				case "scholarship_75":
					PaymentMethod paymentScholarship75 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_75)
							.orElseThrow(() -> new RuntimeException("Error: Payment method is not found"));
					paymentMethods.add(paymentScholarship75);

					break;
				default:
					PaymentMethod paymentScholarship100 = paymentMethodRepo.findByName(EPaymentMethods.SCHOLARSHIP_100)
							.orElseThrow(() -> new RuntimeException("Error: Payment method is not found"));
					paymentMethods.add(paymentScholarship100);
				}
			});
		}
		scholarship.setPaymentMethods(paymentMethods);
		scholarshipRepo.save(scholarship);
		return scholarship;
	}

	public Scholarship updateScholarship(Optional<Scholarship> scholarshipInfo, ScholarshipRequest scholarshipRequest) {
		Scholarship scholarshipUpdate = scholarshipInfo.get();
		scholarshipUpdate.setStudies(scholarshipRequest.isStudies());
		scholarshipUpdate.setWorks(scholarshipRequest.isWorks());
		scholarshipUpdate.setIncome(scholarshipRequest.isIncome());
		scholarshipUpdate.setMonthlyincome(scholarshipRequest.getMonthlyincome());
		scholarshipUpdate.setDependents(scholarshipRequest.isDependents());
		scholarshipUpdate.setNumberOfDependents(scholarshipRequest.getNumberOfDependents());

		scholarshipRepo.save(scholarshipUpdate);
		return scholarshipUpdate;
	}

	public boolean checkStudentStatus(Enrollment enrollment) {
		Student student = enrollment.getStudent();

		Optional<Scholarship> scholarshipOp = scholarshipRepo.findByStudent(student);
		if (scholarshipOp.isPresent()) {
			return false;
		}
		return true;
	}

}
