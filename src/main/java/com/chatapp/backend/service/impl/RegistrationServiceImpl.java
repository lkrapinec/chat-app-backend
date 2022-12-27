package com.chatapp.backend.service.impl;

import com.chatapp.backend.model.AppUser;
import com.chatapp.backend.model.ConfirmationToken;
import com.chatapp.backend.model.dto.RegistrationRequest;
import com.chatapp.backend.service.AppUserService;
import com.chatapp.backend.service.ConfirmationTokenService;
import com.chatapp.backend.service.EmailService;
import com.chatapp.backend.service.RegistrationService;
import com.chatapp.backend.utils.EmailValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailValidator emailValidator;

    private final AppUserService appUserService;

    private final ConfirmationTokenService confirmationTokenService;

    private final EmailService emailService;

    public RegistrationServiceImpl(EmailValidator emailValidator, AppUserService appUserService, ConfirmationTokenService confirmationTokenService,
                                   EmailService emailService) {
        this.emailValidator = emailValidator;
        this.appUserService = appUserService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    @Override
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        AppUser newAppUser = new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getPassword(),
                request.getEmail()
        );

        String token = appUserService.signUpUser(newAppUser);
        String link = "http://localhost:8080/api/register/confirm?token=" + token;
        emailService.send(request.getEmail(), request.getFirstName(), link);

        return token;
    }

    @Transactional
    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Confirmed";
    }
}
