package com.ada.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ada.model.Course;
import com.ada.model.Organization;
import com.ada.payload.request.CourseRequest;
import com.ada.service.CourseService;
import com.ada.service.OrganizationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/course")
public class CourseController {

	@Autowired
	CourseService courseService;

	@Autowired
	OrganizationService orgService;

	@Autowired
	Course course;

	@GetMapping("/")
	public Iterable<Course> getAllCourse() {
		return courseService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") Long courseId) {
		Optional<Course> courseList = courseService.findById(courseId);
		if (courseList.isPresent()) {
			return new ResponseEntity<>(courseList.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/category")
	public ResponseEntity<List<Course>> getCourseByCategory(@RequestParam String courseCateg) {
		try {
			List<Course> coursesByCateg = courseService.findByCategory(courseCateg);
			return new ResponseEntity<>(coursesByCateg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/org/")
	public ResponseEntity<List<Course>> getCourseByOrganization(@RequestParam Long orgId) {
		try {
			List<Course> coursesByOrg = courseService.findByOrgId(orgId);
			return new ResponseEntity<>(coursesByOrg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/org/categ")
	public ResponseEntity<List<Course>> getCourseByOrgAndCateg(@RequestParam Long orgId,
			@RequestParam String courseCateg) {
		try {
			List<Course> coursesByOrg = courseService.findByOrgId(orgId);
			List<Course> coursesByOrgAndCateg = courseService.findByOrgAndCategory(coursesByOrg, courseCateg);
			return new ResponseEntity<>(coursesByOrgAndCateg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/")
//	@PreAuthorize("hasRole('AGENT')")
	public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
		try {
			Optional<Organization> org = orgService.findById(courseRequest.getOrgId());
			Organization orgRelated = org.get();
			Course newCourse = new Course(courseRequest.getCourseName(), courseRequest.getCourseDescrip(),
					courseRequest.getCourseMode(), courseRequest.getCoursePrice(), courseRequest.getCourseHours(),
					courseRequest.getCourseCateg(), courseRequest.getMaxQuota(), courseRequest.getScholarshipsQuota(),
					courseRequest.getCourseYear(), courseRequest.setOrg(orgRelated));
			if (courseService.acceptedOrganization(newCourse)) {
				courseService.save(newCourse);
				return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(newCourse, HttpStatus.METHOD_NOT_ALLOWED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") Long courseId,
			@RequestBody CourseRequest courseRequest) {
		Optional<Course> courseInfo = courseService.findById(courseId);
		if (courseInfo.isPresent()) {
			Course courseUpdate = courseInfo.get();
			courseUpdate.setCourseName(courseRequest.getCourseName());
			courseUpdate.setCourseDescrip(courseRequest.getCourseDescrip());
			courseUpdate.setCourseMode(courseRequest.getCourseMode());
			courseUpdate.setCoursePrice(courseRequest.getCoursePrice());
			courseUpdate.setCourseHours(courseRequest.getCourseHours());
			courseUpdate.setCourseCateg(courseRequest.getCourseCateg());
			courseUpdate.setMaxQuota(courseRequest.getMaxQuota());
			courseUpdate.setScholarshipsQuota(courseRequest.getScholarshipsQuota());
			courseUpdate.setCourseYear(courseRequest.getCourseYear());
			return new ResponseEntity<>(courseService.save(courseUpdate), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") Long courseId) {
		try {
			courseService.deleteById(courseId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
