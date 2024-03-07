package com.britishenglishcertificate.gowrishankar.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDataResponse {
    private String course_name;
    private String course_hour;
    private String course_mode;
    private String course_fees;
}