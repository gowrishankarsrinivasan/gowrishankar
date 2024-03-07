package com.britishenglishcertificate.gowrishankar.controller;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseDataResponse;
import com.britishenglishcertificate.gowrishankar.service.CourseDataService;

@RestController
@RequestMapping(AUTH)
public class CourseDataController {

    private final CourseDataService courseDataService;

    public CourseDataController(CourseDataService courseDataService) {
        this.courseDataService = courseDataService;
    }

    @PostMapping("/save/data")
    public ResponseEntity<String> saveCourse(@RequestBody CourseDataRequest request) {
        String response = courseDataService.saveCourse(request);
        return ResponseEntity.ok(response);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/all")
    public List<CourseDataResponse> getAllCourses() {
        return courseDataService.getAllCourses();
    }
}
