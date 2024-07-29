package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Column(name = "delivery_address")
    private String deliveryAddress;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "authorities")
    private String authorities;

    @NotNull
    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @NotNull
    @Column(name = "account_non_locked")
    private Boolean accountNonLocked;

    @NotNull
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @NotNull
    @Column(name = "enabled")
    private Boolean enabled;

}