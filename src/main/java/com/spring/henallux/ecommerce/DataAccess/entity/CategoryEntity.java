package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "label_fr")
    @NotBlank(message = "Le label en français ne peut pas être vide")
    @Size(min = 1, max = 50, message = "Le label en français doit avoir entre 1 et 50 caractères")
    private String labelFr;

    @Column(name = "label_en")
    @NotBlank(message = "Le label en anglais ne peut pas être vide")
    @Size(min = 1, max = 50, message = "Le label en anglais doit avoir entre 1 et 50 caractères")
    private String labelEn;
}