package com.ada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long> {

//	public boolean existsById(Long studentId);

}
