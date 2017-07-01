package com.msioja.thesisresv.service;

import com.msioja.thesisresv.model.Role;
import com.msioja.thesisresv.model.User;
import com.msioja.thesisresv.repository.UserRepository;
import com.msioja.thesisresv.security.PasswordEncoderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addPromoter(User user) {
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword(PasswordEncoderGenerator.generate(user.getPassword()));
        userRepository.save(user);
    }

    public void addStudent(User user) {
        user.setRole(Role.ROLE_USER);
        user.setPassword(PasswordEncoderGenerator.generate(user.getPassword()));
        userRepository.save(user);
    }

    public boolean isUsernameExists(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }
}
