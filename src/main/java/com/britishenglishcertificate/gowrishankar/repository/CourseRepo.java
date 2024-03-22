package com.britishenglishcertificate.gowrishankar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.dto.request.CourseRequest;
import com.britishenglishcertificate.gowrishankar.model.Course;

public interface CourseRepo extends JpaRepository<Course, String> {
    void save(CourseRequest enquiry);

}