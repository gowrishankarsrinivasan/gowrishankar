package com.britishenglishcertificate.gowrishankar.service;

import java.util.List;
import com.britishenglishcertificate.gowrishankar.dto.request.QueryDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.QueryDataResponse;

public interface QueryDataService {
    String saveQuery(QueryDataRequest data);

    List<QueryDataResponse> getQueryDatas();

    String updateByEmail(String email, QueryDataRequest data);

    List<QueryDataResponse> getQueryDatasByEmail(String email);
}
