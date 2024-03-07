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
public class QueryDataRequest {
    private String id;

    private String course_name;

    private String email;

    private String enquiry_type;

    private String message;
}
