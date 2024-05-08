package net.javaguides.springboot.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService{


    private IUserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        //Convert User Into UserDto to return it (to return the id )
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser= userRepository.findById(id);
        return  UserMapper.mapToUserDto(optionalUser.get());
        //return optionalUser.get();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return  users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
        //return users;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser =userRepository.save(existingUser);
        return UserMapper.mapToUserDto(updatedUser);
        //return updatedUser;
    }

    @Override
    public boolean deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).get();
        userRepository.delete(existingUser);
        return true;
    }
}
