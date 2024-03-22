package com.britishenglishcertificate.gowrishankar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEntityRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseGetResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.CoursePostResponse;
import com.britishenglishcertificate.gowrishankar.model.Course;
import com.britishenglishcertificate.gowrishankar.repository.CourseRepo;
import com.britishenglishcertificate.gowrishankar.service.CourseEntity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseEntity {

    private final CourseRepo courseRepo;

    @SuppressWarnings("null")
    @Override
    public CoursePostResponse saveCourse(CourseEntityRequest request) {

        Course course = Course.builder()
                .courseName(request.getCourseName())
                .duration(request.getDuration())
                .fees(request.getFees())
                .examformat(request.getExamformat())

                .build();

        courseRepo.save(course);

        return CoursePostResponse.builder().message("Course saved successfully").build();

    }

    @Override
    public List<CourseGetResponse> getAllCourses() {
        List<Course> courses = courseRepo.findAll();
        return courses.stream()
                .map(course -> CourseGetResponse.builder()
                        .courseName(course.getCourseName())
                        .duration(course.getDuration())
                        .fees(course.getFees())
                        .examformat(course.getExamformat())
                        // .image(course.getImage())
                        // .message("Course details retrieved successfully")
                        .build())
                .collect(Collectors.toList());
    }

}