package com.britishenglishcertificate.gowrishankar.repository;

import com.britishenglishcertificate.gowrishankar.model.CourseEnrolled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEnrolledRepository extends JpaRepository<CourseEnrolled, Long> {
}
