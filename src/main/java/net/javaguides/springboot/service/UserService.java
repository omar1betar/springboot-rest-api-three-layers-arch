package net.javaguides.springboot.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService{


    private IUserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User
        //Manual Mapping
        //User user = UserMapper.mapToUser(userDto);
        //-----
        //Model Mapper
        User user = modelMapper.map(userDto, User.class);

        User savedUser = userRepository.save(user);
        //Convert User Into UserDto to return it (to return the id )
        //Manual Mapper
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        //------
        //model mapper
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser= userRepository.findById(id);
        //manual mapping
        //return  UserMapper.mapToUserDto(optionalUser.get());
        //-----
        //model mapper
        return  modelMapper.map(optionalUser,UserDto.class);


    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //manual mapping
        //return  users.stream().map(UserMapper::mapToUserDto)
         //       .collect(Collectors.toList());
        //------
        //model mapper
        return  users.stream().map((user)->modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser =userRepository.save(existingUser);
        //manual mapping
        //return UserMapper.mapToUserDto(updatedUser);
        //----
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public boolean deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).get();
        userRepository.delete(existingUser);
        return true;
    }
}
