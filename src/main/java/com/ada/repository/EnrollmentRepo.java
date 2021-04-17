package com.ada.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Enrollment;

@Repository
public interface EnrollmentRepo extends CrudRepository<Enrollment, Integer> {

}
