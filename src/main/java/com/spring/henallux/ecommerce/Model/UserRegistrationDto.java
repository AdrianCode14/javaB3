package com.spring.henallux.ecommerce.Model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Veuillez fournir une adresse email valide")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[^@]+$", message = "Format d'email invalide")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;

    @NotBlank(message = "La confirmation du mot de passe est obligatoire")
    private String passwordConfirm;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 255, message = "Le prénom ne peut pas dépasser 255 caractères")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255, message = "Le nom ne peut pas dépasser 255 caractères")
    private String lastName;

    @NotBlank(message = "L'adresse de livraison est obligatoire")
    @Size(max = 255, message = "L'adresse ne peut pas dépasser 255 caractères")
    private String deliveryAddress;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "^\\d{4}/\\d{2}\\.\\d{2}\\.\\d{2}$", message = "Le format du numéro de téléphone doit être XXXX/XX.XX.XX")
    private String phoneNumber;
    @Size(max = 255, message = "La couleur préférée ne peut pas dépasser 255 caractères")
    private String favoriteColor;

    @Size(max = 255, message = "Le surnom ne peut pas dépasser 255 caractères")
    private String nickname;
}