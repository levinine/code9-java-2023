package com.levi9.code9.monolithic.user;

import com.levi9.code9.monolithic.email.NotificationService;
import com.levi9.code9.monolithic.exception.AlreadyExistsException;
import com.levi9.code9.monolithic.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User is not found!"));
    }

    public User findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByEmailAndPassword(username, password)
                .orElseThrow(() -> new NotFoundException("User is not found!"));
    }

    List<User> findAll() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            notifyUserIfNeeded(user, "created");
        } else {
            throw new AlreadyExistsException(String.format("User with email '%s' already exists", user.getEmail()));
        }
    }

    public void updateUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            User updatedUser = User.builder()
                    .id(existingUser.getId())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .receiveUpdate(user.isReceiveUpdate())
                    .build();
            userRepository.save(updatedUser);
            notifyUserIfNeeded(user, "updated");
        } else {
            throw new AlreadyExistsException(String.format("User with email '%s' already exists", user.getEmail()));
        }
    }

    public void deleteUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
        } else {
            throw new AlreadyExistsException(String.format("User with email '%s' not exists", email));
        }
    }

    private void notifyUserIfNeeded(User user, String type) {
        if(user.isReceiveUpdate()) {
            notificationService.sendUserUpdate(user.getEmail(), type);
        }
    }
}
