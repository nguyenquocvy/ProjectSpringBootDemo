package com.example.apidemo.Springboot.models;

import com.example.apidemo.Springboot.ErrorCode.ErrorString;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {
    private @Valid @NotEmpty(message = ErrorString.USERNAME_NOT_EMPTY) String username;

    private @Valid @NotNull(message = ErrorString.PASSWORD_NOT_EMPTY) String password;
}
