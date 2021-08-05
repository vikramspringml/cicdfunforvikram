package com.springml.cicdsampleapp.service;

import com.springml.cicdsampleapp.model.BackendRequest;
import com.springml.cicdsampleapp.model.BackendResponse;
import org.springframework.stereotype.Service;

@Service
public class BackendService {
    public BackendResponse getresponse(BackendRequest req) {
        return new BackendResponse(req.getUuid());
    }
}
