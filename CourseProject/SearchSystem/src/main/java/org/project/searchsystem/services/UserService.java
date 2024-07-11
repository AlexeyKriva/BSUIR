package org.project.searchsystem.services;

import org.project.searchsystem.models.User;
import org.project.searchsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String nickname, String email, String password) {
        User user = new User();
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.saveUser(user);
    }

    public boolean checkUser(String email, String password) {
        User currentUser = new User();
        currentUser.setEmail(email);
        currentUser.setPassword(password);
        return userRepository.isUserExist(currentUser);
    }
}
