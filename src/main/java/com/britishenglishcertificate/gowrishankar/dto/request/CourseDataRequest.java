package com.britishenglishcertificate.gowrishankar.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDataRequest {

    private String id;

    private String course_name;

    private String course_hour;

    private String course_mode;

    private String course_fees;
}
