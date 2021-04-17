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

import com.ada.model.Student;
import com.ada.model.User;
import com.ada.payload.request.StudentRequest;
import com.ada.repository.UserRepo;
import com.ada.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/student")
public class StudentController {

	@Autowired
	UserRepo userRepo;

	@Autowired
	StudentService studentService;

	@Autowired
	Student student;

	@GetMapping("/")
	@Operation(description = "List of all the students registered in the app")
	public @ResponseBody Iterable<Student> getAllStudent() {
		return studentService.findAll();
	}

	@GetMapping("/{id}")
	@Operation(description = "This method return a student according to a given id")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
		Optional<Student> studentList = studentService.findById(id);

		if (studentList.isPresent()) {
			return new ResponseEntity<>(studentList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	@Operation(description = "This method records an object-student in the database")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
		try {
			Optional<User> userStudent = userRepo.findById(studentRequest.getUserId());
			User user = userStudent.get();
			Student newStudent = studentService.save(new Student(studentRequest.getStudentName(),
					studentRequest.getStudentLastname(), studentRequest.getStudentBirthDate(),
					studentRequest.getStudentCountry(), studentRequest.getStudentDocumentType(),
					studentRequest.getStudentDocumentNumber(), studentRequest.setUser(user)));
			return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	@Operation(description = "This method updates the information related to an object-student in the database according to a given id")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
			@RequestBody StudentRequest studentRequest) {
		Optional<Student> studentInfo = studentService.findById(id);
		if (studentInfo.isPresent()) {
			Student studentUpdate = studentInfo.get();
			studentUpdate.setStudentName(studentRequest.getStudentName());
			studentUpdate.setStudentLastname(studentRequest.getStudentLastname());
			studentUpdate.setStudentBirthDate(studentRequest.getStudentBirthDate());
			studentUpdate.setStudentCountry(studentRequest.getStudentCountry());
			studentUpdate.setStudentDocumentType(studentRequest.getStudentDocumentType());
			studentUpdate.setStudentDocumentNumber(studentRequest.getStudentDocumentNumber());

			return new ResponseEntity<>(studentService.save(studentUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(description = "This method delete a student but no the related user")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
		try {
			studentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
