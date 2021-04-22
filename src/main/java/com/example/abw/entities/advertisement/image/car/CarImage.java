package com.example.abw.entities.advertisement.image.car;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.advertisement.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "car_image1")
public class CarImage implements Serializable, Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Lob
    @NotNull
    byte[] content;

    @JsonIgnore
    @NotNull(message = "Image carAd must not be null")
    @ManyToOne
    @JoinColumn(name = "car_ad_id", referencedColumnName = "id")
    private CarAdvertisement carAd;

    @Override
    public byte[] getImage() {
        return this.content;
    }

    @Override
    public void setImage(byte[] content) {
        this.content = content;
    }
}
