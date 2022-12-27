package com.chatapp.backend.service;

import com.chatapp.backend.model.AppUser;

public interface AppUserService {
    String signUpUser(AppUser appUser);

    void enableAppUser(String email);
}
