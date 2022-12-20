package com.chatapp.backend.service.impl;

import com.chatapp.backend.model.dto.RegistrationRequest;
import com.chatapp.backend.service.RegistrationService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

  @Override
  public String register(RegistrationRequest request) {
    return "works";
  }
}
