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
public class ProfileRequestDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
    private String state;
    private String city;
    private String postalCode;
    private String aboutMe;
}
