package com.codecool.mastery.showthisoutfitbackend.showthisoutfit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Clothing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String genderENG;
    private String classificationENG;
    private String subclassificationENG;
    private String genderHUN;
    private String classificationHUN;
    private String subclassificationHUN;
    private Long fashionDaysProductId;
    private String similarProductsLink;
    private String mainProductLink;
    private String fashionDaysProductName;
    private Long priceHUF;
    private Float priceEUR;
    private String brand;
    private String stockStatusENG;

    @Builder.Default
    @OneToMany(mappedBy = "clothing",cascade = CascadeType.MERGE)
    @EqualsAndHashCode.Exclude
    private List<ImageLink> images = new LinkedList<>();

    private String color;
    private String catalogId;

    @Lob
    private String productDetails;

}
