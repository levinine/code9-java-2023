package com.levi9.code9.monolithic.user;

import com.levi9.code9.monolithic.user.dto.UserDto;
import com.levi9.code9.monolithic.user.dto.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createUser(@RequestBody final UserDto registrationDetails) {
        userService.createUser(userMapper.dtoToEntity(registrationDetails));
    }

    @PutMapping
    public void updateUser(@RequestBody final UserDto updateDetails) {
        userService.updateUser(userMapper.dtoToEntity(updateDetails));
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable("email") final String email) {
        userService.deleteUserByEmail(email);
    }

}
