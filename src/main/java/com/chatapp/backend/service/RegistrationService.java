package com.chatapp.backend.service;

import com.chatapp.backend.model.dto.RegistrationRequest;

public interface RegistrationService {
  String register(RegistrationRequest request);
}
