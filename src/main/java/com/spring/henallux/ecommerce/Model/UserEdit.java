package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserEdit {
    private String email;
    private String firstName;
    private String lastName;
    private String deliveryAddress;
    private String phoneNumber;
}
