package com.imageflowmeta.service.registration;

import com.imageflowmeta.service.registration.token.ConfirmationToken;
import com.imageflowmeta.service.registration.token.ConfirmationTokenService;
import com.imageflowmeta.service.user.User;
import com.imageflowmeta.service.user.UserRole;
import com.imageflowmeta.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new User(api/v1/registration
                        request.getName(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getDob(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }

    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpires();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail()
        );
        return "confirmed";

    }
}
