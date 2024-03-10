package com.britishenglishcertificate.gowrishankar.controller;

import static com.britishenglishcertificate.gowrishankar.utils.MyConstant.AUTH;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.britishenglishcertificate.gowrishankar.dto.request.QueryDataRequest;
import com.britishenglishcertificate.gowrishankar.dto.response.QueryDataResponse;
// import com.britishenglishcertificate.gowrishankar.service.QueryDataService;
import com.britishenglishcertificate.gowrishankar.service.impl.QueryDataImpl;

@RestController
@RequestMapping(AUTH)
// @PreAuthorize("hasRole('ADMIN')")
public class QueryDataController {

    private final QueryDataImpl service;

    public QueryDataController(QueryDataImpl service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/save/enquiry")
    public String saveEnquiry(@RequestBody QueryDataRequest request) {
        return service.saveQuery(request);
    }

    @GetMapping("/getAllQuery")
    public List<QueryDataResponse> getQueryData() {
        return service.getQueryDatas();
    }

    @PutMapping("/querydata/{email}")
    public String updateQueryDataByEmail(@PathVariable String email, @RequestBody QueryDataRequest requestData) {
        return service.updateByEmail(email, requestData);
    }

    @GetMapping("/querydata/email/{email}") // New endpoint to get data by email
    public ResponseEntity<?> getQueryDataByEmail(@PathVariable String email) {
        List<QueryDataResponse> queryDataResponses = service.getQueryDatasByEmail(email);
        if (!queryDataResponses.isEmpty()) {
            return ResponseEntity.ok(queryDataResponses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
