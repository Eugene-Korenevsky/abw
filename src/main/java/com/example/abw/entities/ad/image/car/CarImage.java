package com.example.abw.entities.ad.image.car;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.entities.ad.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "car_image")
public class CarImage implements Serializable, Image {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Image resource must not be null")
    @Column(columnDefinition = "text")
    private String image;

    @NotNull(message = "Image carAd must not be null")
    @ManyToOne
    @JoinColumn(name = "car_ad_id", referencedColumnName = "id")
    private CarAd carAd;

}
