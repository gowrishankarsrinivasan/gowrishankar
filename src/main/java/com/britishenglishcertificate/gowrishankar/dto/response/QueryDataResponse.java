package com.britishenglishcertificate.gowrishankar.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryDataResponse {
    private String course_name;

    private String email;

    private String enquiry_type;

    private String message;
}
