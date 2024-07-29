package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PasswordChangeForm {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
}
