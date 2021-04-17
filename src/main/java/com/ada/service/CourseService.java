package com.ada.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.model.Course;
import com.ada.model.Organization;
import com.ada.repository.CourseRepo;

@Service
public class CourseService {

	@Autowired
	CourseRepo courseRepo;

	@Autowired
	OrganizationService orgService;

	public Iterable<Course> findAll() {
		return this.courseRepo.findAll();
	}

	public Optional<Course> findById(Long courseId) {
		return this.courseRepo.findById(courseId);
	}

	public List<Course> findByOrgAndCategory(List<Course> coursesByOrg, String courseCateg) {
		List<Course> coursesByOrgAndCateg = new ArrayList<Course>();
		Iterator<Course> listByCateg = coursesByOrg.iterator();
		while (listByCateg.hasNext()) {
			Course course = listByCateg.next();
			if (courseCateg.equals(course.getCourseCateg())) {
				coursesByOrgAndCateg.add(course);
			}
		}
		return coursesByOrgAndCateg;
	}

	public List<Course> findByCategory(String courseCateg) {
		Iterable<Course> courseList = courseRepo.findByCourseCategStartingWith(courseCateg);
		List<Course> coursesByOrg = new ArrayList<Course>();
		coursesByOrg.addAll((Collection<? extends Course>) courseList);
		return coursesByOrg;
	}

	public Course save(Course course) {
		return this.courseRepo.save(course);
	}

	public void deleteById(Long courseId) {
		Optional<Course> entity = courseRepo.findById(courseId);
		courseRepo.delete(entity.get());
	}

	public boolean acceptedOrganization(Course course) {
		Organization org = course.getOrganization();
		if (org.isAccepted()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Course> findByOrgId(Long orgId) {
		List<Course> coursesByOrg = new ArrayList<Course>();
		Optional<Organization> organization = orgService.findById(orgId);
		Organization orgCourse = organization.get();
		Collection<? extends Course> courses = orgCourse.getCourses();
		coursesByOrg.addAll(courses);
		return coursesByOrg;
	}

}
