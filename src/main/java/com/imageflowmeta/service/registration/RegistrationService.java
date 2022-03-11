package com.imageflowmeta.service.registration;

import com.imageflowmeta.service.user.User;
import com.imageflowmeta.service.user.UserRole;
import com.imageflowmeta.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getName(),
                        request.getUsername(),
                        request.getEmail(),
                        request.getDob(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}
