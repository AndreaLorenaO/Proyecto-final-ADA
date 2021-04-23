package com.ada.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Course;
import com.ada.model.Scholarship;
import com.ada.model.Student;
import com.ada.payload.request.ScholarshipRequest;
import com.ada.repository.ScholarshipRepo;

@Service
public class ScholarshipService {

	@Autowired
	ScholarshipRepo scholarshipRepo;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

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

}
