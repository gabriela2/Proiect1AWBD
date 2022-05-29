package com.awbdfirstproject.railwaystationapp.dto;

import com.awbdfirstproject.railwaystationapp.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotNull
    @Size(min = 2)
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    @Size(min = 4)
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;
    @NotNull
    private UserType userType;

}
