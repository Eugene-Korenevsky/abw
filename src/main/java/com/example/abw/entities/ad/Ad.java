package com.example.abw.entities.ad;


import com.example.abw.entities.ad.image.Image;
import com.example.abw.entities.ad.image.car.CarImage;
import com.example.abw.entities.sell_item.SellItem;
import com.example.abw.entities.user.User;

import java.sql.Timestamp;
import java.util.Set;

public interface Ad {
    public long getId();

    public User getUser();

    public void setUser(User user);

    public SellItem getSellItem();

    public void sellItem(SellItem sellItem);

    public long getPrice();

    public void setPrice(long price);

    public boolean isSold();

    public void setSold(boolean sold);

    public String getDescriptions();

    public void setDescriptions(String descriptions);

    public Set<? extends Image> getImages();

    public Timestamp getPublicationDate();

    public void setPublicationDate(Timestamp publicationDate);

    public Timestamp getEndPublicationDate();

    public void setEndPublicationDate(Timestamp endPublicationDate);
}
