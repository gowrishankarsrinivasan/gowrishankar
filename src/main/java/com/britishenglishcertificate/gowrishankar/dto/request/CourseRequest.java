package com.britishenglishcertificate.gowrishankar.dto.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private String duration;
    private String fees;
    private String examformat;
    private String Noofpapers;

}
