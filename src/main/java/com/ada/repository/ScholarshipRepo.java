package com.ada.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Scholarship;
import com.ada.model.Student;

@Repository
public interface ScholarshipRepo extends CrudRepository<Scholarship, Integer> {

	Optional<Scholarship> findByStudent(Student student);

}
