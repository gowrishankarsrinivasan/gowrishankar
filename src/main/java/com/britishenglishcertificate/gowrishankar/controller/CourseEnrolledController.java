package com.britishenglishcertificate.gowrishankar.controller;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEnrollRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseEnrollResponse;
import com.britishenglishcertificate.gowrishankar.model.CourseEnrolled;
import com.britishenglishcertificate.gowrishankar.service.CourseEnrolledService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class CourseEnrolledController {

    @Autowired
    private CourseEnrolledService courseEnrolledService;

    @PostMapping("/enroll")
    public ResponseEntity<CourseEnrollResponse> enrollCourse(@RequestBody CourseEnrollRequest request) {
        CourseEnrollResponse response = courseEnrolledService.enrollCourse(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEnrolled> getEnrolledCourse(@PathVariable Long id) {
        CourseEnrolled courseEnrolled = courseEnrolledService.getEnrolledCourse(id);
        if (courseEnrolled == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseEnrolled, HttpStatus.OK);
    }

    @GetMapping("/course")
    public List<CourseEnrolled> getAllCoursesEnrolled() {
        return courseEnrolledService.getAllCoursesEnrolled();
    }
}
