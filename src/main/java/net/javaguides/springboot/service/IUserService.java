package net.javaguides.springboot.service;

import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;

import java.util.List;

public interface IUserService {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    boolean deleteUserById(Long id);
}
