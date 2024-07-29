package com.spring.henallux.ecommerce.DataAccess.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "label_en")
    @Length(min = 1, max = 50)
    @NotNull
    private String labelEn;

    @Column(name = "label_fr")
    @NotNull
    @Length(min = 1, max = 50)
    private String labelFr;

    @Column(name = "description_en")
    @NotNull
    @Length(min = 1, max = 100)
    private String descriptionEn;

    @Column(name = "description_fr")
    @NotNull
    @Length(min = 1, max = 100)
    private String descriptionFr;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryId;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    @Null
    private PromotionEntity promotionId;

    @NotNull
    @Column(name = "dimension")
    @Length(min = 1, max = 50)
    private String dimension;

    @NotNull
    @Column(name = "weight")
    @Min(0)
    private double weight;

    @NotNull
    @Column(name = "price")
    @Min(0)
    private double price;

    @NotNull
    @Column(name = "stock")
    @Min(0)
    private int stock;

    @NotNull
    @Column(name = "date_added")
    private Date dateAdded;
}
