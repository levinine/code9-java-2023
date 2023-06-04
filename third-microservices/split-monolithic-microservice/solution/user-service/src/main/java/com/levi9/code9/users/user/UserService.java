package com.levi9.code9.users.user;

import com.levi9.code9.users.exception.AlreadyExistsException;
import com.levi9.code9.users.exception.NotFoundException;
import com.levi9.code9.users.messaging.MessageFactory;
import com.levi9.code9.users.messaging.MessageService;
import com.levi9.code9.users.messaging.UserUpdateMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final MessageService messageService;

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User is not found!"));
    }

    List<User> findAll() {
        return userRepository.findAll();
    }

    List<User> findAllSubscribedUsers() {
        return userRepository.findAllByReceiveUpdate(true);
    }
    public void createUser(User user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            publishMessageIfSubscribed(user, UserOperation.CREATE);
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
            publishMessageIfSubscribed(updatedUser, UserOperation.UPDATE);
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

    private void publishMessageIfSubscribed(User user, UserOperation operation) {
        if(Boolean.TRUE.equals(user.isReceiveUpdate())) {
            UserUpdateMessage userUpdateMessage = MessageFactory.createUserUpdateMessage(user, operation);
            messageService.sendMessageToUserTopic(userUpdateMessage);
        }
    }
}
