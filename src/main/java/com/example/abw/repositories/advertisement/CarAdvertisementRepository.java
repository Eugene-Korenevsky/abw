package com.example.abw.repositories.advertisement;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.user.User;
import com.example.abw.model.advertisement.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.net.ssl.SSLEngineResult;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CarAdvertisementRepository extends CrudRepository<CarAdvertisement, Long> {
    public List<CarAdvertisement> readAllByCarBrand_CarBrandName_Name(String name);

    public List<CarAdvertisement> readAllByStatusAndPublicationDateLessThan(Status status, Timestamp timestamp);

    public List<CarAdvertisement> readAllByUser(User user);

    public List<CarAdvertisement> readAllByStatus(Status status);
}
