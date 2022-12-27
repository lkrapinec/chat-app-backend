package com.chatapp.backend.service;

public interface EmailService {
    void send(String to, String firstName, String link);
}
