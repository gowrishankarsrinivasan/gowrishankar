package com.britishenglishcertificate.gowrishankar.service.impl;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEnrollRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseEnrollResponse;
import com.britishenglishcertificate.gowrishankar.model.CourseEnrolled;
import com.britishenglishcertificate.gowrishankar.repository.CourseEnrolledRepository;

import com.britishenglishcertificate.gowrishankar.service.CourseEnrolledService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseEnrolledServiceImpl implements CourseEnrolledService {

    @Autowired
    private CourseEnrolledRepository courseEnrolledRepository;

    @Override
    public CourseEnrollResponse enrollCourse(CourseEnrollRequest request) {
        CourseEnrolled courseEnrolled = new CourseEnrolled(request.getCourse(), request.getEmail(),
                request.getAmount());
        CourseEnrolled savedCourseEnrolled = courseEnrolledRepository.save(courseEnrolled);
        return new CourseEnrollResponse(savedCourseEnrolled.getId(), savedCourseEnrolled.getCourse(),
                savedCourseEnrolled.getEmail(), savedCourseEnrolled.getAmount());
    }

    @Override
    public CourseEnrolled getEnrolledCourse(Long id) {
        return courseEnrolledRepository.findById(id).orElse(null);
    }

    @Override
    public List<CourseEnrolled> getAllCoursesEnrolled() {
        return courseEnrolledRepository.findAll();
    }
}
