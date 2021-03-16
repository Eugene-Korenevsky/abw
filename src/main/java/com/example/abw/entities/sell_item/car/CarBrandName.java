package com.example.abw.entities.sell_item.car;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "carBrands")
@Table(name = "car_brand_name")//audi
public class CarBrandName implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, message = "carBrandName min size must be 2")
    private String name;


    @ToString.Exclude
    @OneToMany(mappedBy = "carBrandName", fetch = FetchType.EAGER)
    private Set<CarBrand> carBrands;



}
