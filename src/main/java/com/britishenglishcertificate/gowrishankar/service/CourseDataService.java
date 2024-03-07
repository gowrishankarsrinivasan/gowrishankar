package com.britishenglishcertificate.gowrishankar.service;

import java.util.List;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.CourseDataResponse;

public interface CourseDataService {
    String saveCourse(CourseDataRequest data);

    List<CourseDataResponse> getAllCourses();
}
