package com.britishenglishcertificate.gowrishankar.service;

import java.util.List;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEnrollRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseEnrollResponse;
import com.britishenglishcertificate.gowrishankar.model.CourseEnrolled;

public interface CourseEnrolledService {
    CourseEnrollResponse enrollCourse(CourseEnrollRequest request);

    CourseEnrolled getEnrolledCourse(Long id);
    List<CourseEnrolled> getAllCoursesEnrolled();
}
