package com.britishenglishcertificate.gowrishankar.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.model.CourseData;

@EntityScan(basePackages = "com.britishenglishcertificate.gowrishankar.model")
public interface CourseDataRepo extends JpaRepository<CourseData, String> {

}
