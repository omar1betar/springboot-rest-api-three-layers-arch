package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
       // return  ResponseEntity.ok(savedUser);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return  ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return  ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        UserDto updatedUser =userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        if(isDeleted){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
