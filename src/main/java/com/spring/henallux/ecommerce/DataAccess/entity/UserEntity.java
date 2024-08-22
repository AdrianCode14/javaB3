package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank
    @Email
    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$")
    @Size(max = 255)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String lastName;

    @Column(name = "delivery_address", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String deliveryAddress;

    @Column(name = "phone_number", nullable = false)
    @NotBlank
    @Pattern(regexp = "^04[0-9]{2}/[0-9]{2}\\.[0-9]{2}\\.[0-9]{2}$")
    @Size(max = 20)
    private String phoneNumber;

    @Column(name = "authorities", nullable = false)
    @NotBlank
    @Size(max = 255)
    private String authorities;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "favorite_color")
    @Size(max = 255)
    private String favoriteColor;

    @Column(name = "nickname")
    @Size(max = 255)
    private String nickname;

}