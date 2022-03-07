package com.imageflowmeta.service.user;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "unable to find resource"
                        )
                );
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        // TODO: add checks for request body

        userRepository
                .findUserByEmail(user.getEmail()).ifPresent(
                        (alreadyPresentUser) ->
                        {
                            throw new ResponseStatusException(
                                    HttpStatus.BAD_REQUEST,
                                    "user already present with email"
                            );
                        });

        return userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, String name, LocalDate dob) {
        User userById = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "user does not exist"
                        )
                );

        userById.setName(name);
        userById.setDob(dob);
    }


    public void deleteUser(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "user not found"
                        )
                );

        userRepository.deleteById(user.getId());
    }

}
