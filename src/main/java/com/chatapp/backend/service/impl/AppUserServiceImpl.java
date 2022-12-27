package com.chatapp.backend.service.impl;

import com.chatapp.backend.model.AppUser;
import com.chatapp.backend.model.ConfirmationToken;
import com.chatapp.backend.model.SecurityUser;
import com.chatapp.backend.repository.AppUserRepository;
import com.chatapp.backend.service.AppUserService;
import com.chatapp.backend.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserServiceImpl implements UserDetailsService, AppUserService {
    private static final String USER_NOT_FOUND_MSG = "User with email %s not found";

    private final AppUserRepository appUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final ConfirmationTokenService confirmationTokenService;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                              ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    @Override
    public String signUpUser(AppUser appUser) {
        Optional<AppUser> newUser = appUserRepository.findByEmail(appUser.getEmail());
        if (newUser.isPresent() && newUser.get().getEnabled()) {
            throw new IllegalStateException("email already taken");
        } else if (newUser.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
            appUser.setPassword(encodedPassword);

            appUserRepository.save(appUser);
        } else {
            appUser = newUser.get();
        }

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Override
    public void enableAppUser(String email) {
        if (appUserRepository.findByEmail(email).isPresent()) {
            appUserRepository.enableAppUser(email);
        } else {
            throw new IllegalStateException("User with email " + email + " not found");
        }
    }
}
