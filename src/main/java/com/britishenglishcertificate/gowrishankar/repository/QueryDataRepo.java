package com.britishenglishcertificate.gowrishankar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.britishenglishcertificate.gowrishankar.dto.request.QueryDataRequest;
// import com.britishenglishcertificate.gowrishankar.dto.response.QueryDataResponse;
import com.britishenglishcertificate.gowrishankar.model.QueryData;

public interface QueryDataRepo extends JpaRepository<QueryData,String>{

    // QueryDataResponse saveQueryData(QueryDataRequest enquiry);

    void save(QueryDataRequest enquiry);

    
} 