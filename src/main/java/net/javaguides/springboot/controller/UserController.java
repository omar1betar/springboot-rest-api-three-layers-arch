package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.exception.ErrorDetails;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
@Tag(
        name = "CRUD REST API for User",
        description = "create,update,getById,getAllUsers,delete"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private IUserService userService;

    @Operation(
            summary = "Create User API",
            description = "Use This Endpoint to create new user"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CODE 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
       // return  ResponseEntity.ok(savedUser);
        return  new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User By Id API",
            description = "Use This Endpoint to get user by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS CODE 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return  ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Get All Users API",
            description = "Use This Endpoint to Get All users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS CODE 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return  ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Update User API",
            description = "Use This Endpoint to Update user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS CODE 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto user) {
        user.setId(id);
        UserDto updatedUser =userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Delete User API",
            description = "Use This Endpoint to delete user"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS CODE 200 OK"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        if(isDeleted){
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    //handle error from the controller
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
//    }
}
