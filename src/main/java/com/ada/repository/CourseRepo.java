package com.ada.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ada.model.Course;

@Repository
public interface CourseRepo extends CrudRepository<Course, Long> {

	public List<Course> findByCourseCategStartingWith(String courseCateg);

//	public boolean existsById(Long courseId);

}
