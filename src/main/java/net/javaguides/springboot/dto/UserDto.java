package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;
    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "User First Name Should Not Be Empty")
    private String firstName;

    @Schema(
            description = "User Last Name"
    )
    @NotEmpty
    private String lastName;

    @Schema(
            description = "User Email"
    )
    @Email
    @NotEmpty
    private String email;
}
