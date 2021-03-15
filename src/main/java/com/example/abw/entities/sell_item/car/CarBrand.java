package com.example.abw.entities.sell_item.car;

import com.example.abw.entities.sell_item.SellItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@EqualsAndHashCode
@Data
@Table(name = "car_brand")//80
public class CarBrand implements Serializable, SellItem {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_brand_name_id", referencedColumnName = "id")
    private CarBrandName carBrandName;

    @Override
    public String getFullName() {
        return this.name + " " + this.carBrandName.getName();
    }
}
