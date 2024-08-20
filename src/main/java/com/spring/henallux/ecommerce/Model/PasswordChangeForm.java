package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class PasswordChangeForm {
    @NotEmpty
    private String oldPassword;

    @NotEmpty
    private String newPassword;

    @NotEmpty
    private String newPasswordConfirm;
}