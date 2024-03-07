package com.britishenglishcertificate.gowrishankar.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.britishenglishcertificate.gowrishankar.dto.request.QueryDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.PaymentResponseDTO;
import com.britishenglishcertificate.gowrishankar.dto.response.QueryDataResponse;
import com.britishenglishcertificate.gowrishankar.model.PaymentModel;
import com.britishenglishcertificate.gowrishankar.model.QueryData;
import com.britishenglishcertificate.gowrishankar.repository.QueryDataRepo;
import com.britishenglishcertificate.gowrishankar.service.QueryDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueryDataImpl implements QueryDataService {

    private final QueryDataRepo repo;

    @Override
    public String saveQuery(QueryDataRequest request) {
        // return repo.save(data);
        var query = QueryData.builder()
                .course_name(request.getCourse_name())
                .email(request.getEmail())
                .enquiry_type(request.getEnquiry_type())
                .message(request.getMessage()).build();
        repo.save(query);

        return "saved";
    }

    public List<QueryDataResponse> getQueryDatas() {
        List<QueryData> queryDataList = repo.findAll();
        return queryDataList.stream()
                .map((QueryData queryData) -> QueryDataResponse.builder()
                        .course_name(queryData.getCourse_name())
                        .email(queryData.getEmail())
                        .enquiry_type(queryData.getEnquiry_type())
                        .message(queryData.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

}
