package com.britishenglishcertificate.gowrishankar.service.impl;

import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseDataResponse;
import com.britishenglishcertificate.gowrishankar.model.CourseData;
import com.britishenglishcertificate.gowrishankar.repository.CourseDataRepo;
import com.britishenglishcertificate.gowrishankar.service.CourseDataService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseDataImpl implements CourseDataService {
    private final CourseDataRepo repo;

    @Override
    public String saveCourse(CourseDataRequest request) {
        var courseData = CourseData.builder()
                .course_name(request.getCourse_name())
                .course_hour(request.getCourse_hour())
                .course_mode(request.getCourse_mode())
                .course_fees(request.getCourse_fees()).build();

        repo.save(courseData);

        return "saved successfully";
    }

    @Override
    public List<CourseDataResponse> getAllCourses() {
        List<CourseData> courses = repo.findAll();
        return courses.stream()
                .map((CourseData courseData) -> CourseDataResponse.builder()
                        .course_name(courseData.getCourse_name())
                        .build())
                .collect(Collectors.toList());
    }
}
