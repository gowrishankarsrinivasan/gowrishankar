package com.britishenglishcertificate.gowrishankar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseGetResponse {
    private String id;
    private String courseName;
    private String duration;
    private String fees;
    private String examformat;
    private String noofpapers;
}