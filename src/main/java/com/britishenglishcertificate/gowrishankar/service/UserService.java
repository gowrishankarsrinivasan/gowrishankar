package com.britishenglishcertificate.gowrishankar.service;

import java.security.Principal;

import com.britishenglishcertificate.gowrishankar.dto.request.PasswordRequest;

public interface UserService {

    void forgotPassword(PasswordRequest request, Principal principal);

}
