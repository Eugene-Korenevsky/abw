package com.example.abw.entities.advertisement;


import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.entities.sell_item.SellItem;
import com.example.abw.entities.user.User;
import com.example.abw.model.advertisement.Status;
import com.example.abw.model.currency.Currency;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

public interface Advertisement {
    public long getId();

    public User getUser();

    public void setUser(User user);

    public SellItem getSellItem();

    public void sellItem(SellItem sellItem);

    public BigDecimal getPrice();

    public void setPrice(BigDecimal price);

    public String getDescriptions();

    public void setDescriptions(String descriptions);

    public Set<? extends Image> getImages();

    public Timestamp getPublicationDate();

    public void setPublicationDate(Timestamp publicationDate);

    public Timestamp getEndPublicationDate();

    public void setEndPublicationDate(Timestamp endPublicationDate);

    public Status getStatus();

    public void setStatus(Status status);

    public Currency getPriceCurrency();

    public void setPriceCurrency(Currency priceCurrency);
}
