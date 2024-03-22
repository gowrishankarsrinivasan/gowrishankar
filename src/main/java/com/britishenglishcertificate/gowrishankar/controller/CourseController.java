package com.britishenglishcertificate.gowrishankar.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEntityRequest;
import com.britishenglishcertificate.gowrishankar.dto.request.CourseRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseGetResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.CoursePostResponse;
import com.britishenglishcertificate.gowrishankar.service.CourseEntity;

import java.util.List;

@RestController

@RequestMapping("/api/v1/auth")
public class CourseController {

    private final CourseEntity courseService;

    public CourseController(CourseEntity courseService) {
        this.courseService = courseService;
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save/courses")
    public CoursePostResponse saveCourse(@RequestBody CourseRequest request) {
        CourseEntityRequest entityRequest = CourseEntityRequest.builder()
                .courseName(request.getCourseName())
                .duration(request.getDuration())
                .fees(request.getFees())
                .build();
        return courseService.saveCourse(entityRequest);
    }

    @GetMapping("/get/all")
    public List<CourseGetResponse> getAllCourses() {
        return courseService.getAllCourses();
    }
}