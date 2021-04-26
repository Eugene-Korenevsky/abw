package com.example.abw.entities.advertisement;


import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.entities.sell_item.SellItem;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.model.advertisement.Status;
import com.example.abw.model.currency.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = {"carImages"})
@Data
@Table(name = "car_ad")
public class CarAdvertisement implements Serializable, Advertisement {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "carAd carBrand must not be null")
    @ManyToOne
    @JoinColumn(name = "car_brand_id", referencedColumnName = "id")
    private CarBrand carBrand;

    @NotNull(message = "carAd user must not be null")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull(message = "carAd price must not be null")
    @Min(value = 1, message = "min price value must be 1")
    private BigDecimal price;

    @NotNull(message = "carAd publicationDate must not be null")
    private Timestamp publicationDate;

    private Timestamp endPublicationDate;


    @Size(max = 1000, message = "max symbols in description is 1000")
    @Column(columnDefinition = "varchar(1000)")
    private String descriptions;


    @ToString.Exclude
    @OneToMany(mappedBy = "carAd", fetch = FetchType.EAGER)
    private Set<CarImage> carImages;

    @NotNull(message = "status must not be null")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "currency must not be null")
    @Enumerated(EnumType.STRING)
    private Currency priceCurrency;

    @Override
    public SellItem getSellItem() {
        return this.carBrand;
    }

    @Override
    public void sellItem(SellItem sellItem) {
        this.carBrand = (CarBrand) sellItem;
    }


    @Override
    public Set<? extends Image> getImages() {
        return this.carImages;
    }
}
