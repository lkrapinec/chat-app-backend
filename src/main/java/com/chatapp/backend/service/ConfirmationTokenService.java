package com.chatapp.backend.service;

import com.chatapp.backend.model.ConfirmationToken;

import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken confirmationToken);

    Optional<ConfirmationToken> getToken(String token);

    void setConfirmedAt(String token);
}
