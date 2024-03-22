package com.britishenglishcertificate.gowrishankar.dto.response;

import com.britishenglishcertificate.gowrishankar.dto.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllUserDataResponse {
    private List<RegisterRequest> users;
}
