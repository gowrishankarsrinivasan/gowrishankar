package com.britishenglishcertificate.gowrishankar.service;

import java.util.List;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseEntityRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseGetResponse;
import com.britishenglishcertificate.gowrishankar.dto.response.CoursePostResponse;

public interface CourseEntity {

    CoursePostResponse saveCourse(CourseEntityRequest request);

    List<CourseGetResponse> getAllCourses();
    // CourseDeleteResponse deleteCourseByName(String courseName);
    // CoursePostResponse updateCourse(String courseId, UpdateCourseRequest
    // request);
}
