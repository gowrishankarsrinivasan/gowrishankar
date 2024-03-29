package com.britishenglishcertificate.gowrishankar.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEntityRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseGetResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.CoursePostResponse;
import com.britishenglishcertificate.gowrishankar.model.Course;
import com.britishenglishcertificate.gowrishankar.repository.CourseRepo;
import com.britishenglishcertificate.gowrishankar.service.CourseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseEntity {

    private final CourseRepo courseRepo;

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
                        .id(course.getId())
                        .courseName(course.getCourseName())
                        .duration(course.getDuration())
                        .fees(course.getFees())
                        .examformat(course.getExamformat())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CoursePostResponse deleteCourse(String id) {
        // Perform deletion logic
        courseRepo.deleteById(id);
        return CoursePostResponse.builder().message("Course deleted successfully").build();
    }
}