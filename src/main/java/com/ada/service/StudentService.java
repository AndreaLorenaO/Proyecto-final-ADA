package com.ada.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Student;
import com.ada.repository.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;

	public Iterable<Student> findAll() {
		return this.studentRepo.findAll();
	}

	public Optional<Student> findById(Long id) {
		return this.studentRepo.findById(id);
	}

	public Student save(Student student) {
		return this.studentRepo.save(student);
	}

	public void deleteById(Long id) {
		Optional<Student> entity = studentRepo.findById(id);
		studentRepo.delete(entity.get());
	}

}
