package com.springml.cicdsampleapp;

import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springml.cicdsampleapp.model.BackendRequest;
import com.springml.cicdsampleapp.model.BackendResponse;
import com.springml.cicdsampleapp.service.BackendService;

@RestController
public class APIController {

  private static Logger sLogger = LoggerFactory.getLogger(APIController.class);

  @Autowired
  private BackendService service;

  @PostMapping("/")
  public BackendResponse getResponse(@RequestBody BackendRequest req) throws URISyntaxException {
    sLogger.info("uuid: "+req.getUuid());
    return service.getresponse(req);
  }

    
}

