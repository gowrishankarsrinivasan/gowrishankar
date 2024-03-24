package com.britishenglishcertificate.gowrishankar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntityRequest {

    private String courseName;
    private String duration;
    private String fees;
    private String examformat;
    private String noofpapers;

}