package com.britishenglishcertificate.gowrishankar.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseGetResponse {
    private String courseName;
    private String duration;
    private String fees;
    private String level;
    private byte[] image;
}