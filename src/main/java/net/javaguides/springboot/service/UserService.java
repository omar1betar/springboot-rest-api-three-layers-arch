package net.javaguides.springboot.service;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
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

        //check if email is exists
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("EMAIL ALREADY EXISTS");
        }

        // Convert UserDto into User
        //Manual Mapping
        //User user = UserMapper.mapToUser(userDto);
        //-----
        //Model Mapper
        //User user = modelMapper.map(userDto, User.class);
        //----
        //map struct
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        //Convert User Into UserDto to return it (to return the id )
        //Manual Mapper
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        //------
        //model mapper
        //UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        //-------
        //map struct
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user= userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", id)
        );

        //-------------------------------------------------------
        //manual mapping
        //return  UserMapper.mapToUserDto(optionalUser.get());
        //-----
        //model mapper
        //return  modelMapper.map(optionalUser,UserDto.class);
        //-----
        //map struct
        return  AutoUserMapper.MAPPER.mapToUserDto(user);


    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        //manual mapping
        //return  users.stream().map(UserMapper::mapToUserDto)
         //       .collect(Collectors.toList());
        //------
        //model mapper
        //return  users.stream().map((user)->modelMapper.map(user,UserDto.class))
          //      .collect(Collectors.toList());
        //------
        //map struct
        return  users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser =userRepository.save(existingUser);
        //manual mapping
        //return UserMapper.mapToUserDto(updatedUser);
        //----
        //model mapper
       // return modelMapper.map(updatedUser,UserDto.class);
        //-----
        //map struct
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public boolean deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("User", "id", id)
        );
        userRepository.delete(existingUser);
        return true;
    }
}
