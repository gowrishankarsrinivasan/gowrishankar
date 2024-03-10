package com.britishenglishcertificate.gowrishankar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEnrollResponse {
    private Long id;
    private String course;
    private String email;
    private double amount;

    // Constructors, getters, and setters
    // ...
}
