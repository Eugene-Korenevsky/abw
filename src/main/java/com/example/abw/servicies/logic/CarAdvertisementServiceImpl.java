package com.example.abw.servicies.logic;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.car_advertisement.CarAdvertisementRequest;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.pagination.advertisement.car_advertisement.CarAdvertisementPaginationRepository;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.CarBrandService;
import com.example.abw.servicies.UserService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.utils.pageable.PageableUtil;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CarAdvertisementServiceImpl extends GenericServiceImpl<CarAdvertisement> implements CarAdvertisementService {

    private CarAdvertisementRepository carAdvertisementRepository;
    private CarBrandService carBrandServiceImpl;
    private UserService userServiceImpl;
    private CarAdvertisementPaginationRepository carAdvertisementPaginationRepository;

    public CarAdvertisementServiceImpl(CarAdvertisementRepository carAdvertisementRepository, UserService userServiceImpl,
                                       CarBrandService carBrandServiceImpl, CarAdvertisementPaginationRepository carAdvertisementPaginationRepository
    ) {
        super(carAdvertisementRepository, CarAdvertisement.class);
        this.carAdvertisementRepository = carAdvertisementRepository;
        this.userServiceImpl = userServiceImpl;
        this.carBrandServiceImpl = carBrandServiceImpl;
        this.carAdvertisementPaginationRepository = carAdvertisementPaginationRepository;
    }

    @Override
    public CarAdvertisement createCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest) throws ValidationException, IOException {
        Set<CarImage> carImages = new HashSet<>();
        CarAdvertisement carAd = new CarAdvertisement();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        User user = isCorrectUser(carAdvertisementRequest.getUserId());
        CarBrand carBrand = findCarBrand(carAdvertisementRequest.getCarBrandId());
        carAd.setPublicationDate(timestamp);
        carAd.setCarBrand(carBrand);
        carAd.setDescriptions(carAdvertisementRequest.getDescriptions());
        carAd.setPrice(carAdvertisementRequest.getPrice());
        carAd.setUser(user);
        carAd = create(carAd);
        for (MultipartFile multipartImage : carAdvertisementRequest.getImages()) {
            CarImage carImage = new CarImage();
            carImage.setContent(multipartImage.getBytes());
            carImage.setCarAd(carAd);
            carImages.add(carImage);
        }
        carAd.setCarImages(carImages);
        return create(carAd);
    }

    @Transactional
    @Override
    public CarAdvertisement updateCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest, long id) throws ValidationException, ResourceNotFoundException {
        CarAdvertisement carAd = findById(id);
        isCorrectUser(carAdvertisementRequest.getUserId());
        User user = isCorrectUser(carAdvertisementRequest.getUserId());
        CarBrand carBrand = findCarBrand(carAdvertisementRequest.getCarBrandId());
        carAd.setCarBrand(carBrand);
        carAd.setDescriptions(carAdvertisementRequest.getDescriptions());
        carAd.setPrice(carAdvertisementRequest.getPrice());
        carAd.setUser(user);
        update(carAd, id);
        return findById(id);
    }

    @Override
    public List<Advertisement> findAllByCarBrand(String carBrand, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin)
            return new ArrayList<>(carAdvertisementPaginationRepository.findByCarBrand_Name(carBrand, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_NameAndSold(carBrand, false, pageable));
    }

    @Override
    public List<Advertisement> findAllByCarBrandName(String carBrandName, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_CarBrandName_Name(carBrandName, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_CarBrandName_NameAndSold(carBrandName, false, pageable));
    }

    @Override
    public List<Advertisement> findAllByPrice(Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return new ArrayList<>(carAdvertisementPaginationRepository.
                readAllByPriceBetween(startPrice, endPrice, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository.
                readAllByPriceBetweenAndSold(startPrice, endPrice, false, pageable));
    }

    @Override
    public List<Advertisement> findAllByPriceAndCarBrand(Long startPrice, Long endPrice, String carBrand,
                                                         boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) {
            return new ArrayList<>(carAdvertisementPaginationRepository
                    .readAllByPriceBetweenAndCarBrand_Name(startPrice, endPrice, carBrand, pageable));
        } else return new ArrayList<>(carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndSoldAndCarBrand_Name(startPrice, endPrice,
                        false, carBrand, pageable));
    }

    @Override
    public List<Advertisement> findAllByPriceAndCarBrandName(Long startPrice, Long endPrice, String carBrandName,
                                                             boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return new ArrayList<>(carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndCarBrand_CarBrandName_Name(startPrice, endPrice,
                        carBrandName, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndSoldAndCarBrand_CarBrandName_Name(startPrice, endPrice,
                        false, carBrandName, pageable));
    }

    @Override
    public List<Advertisement> findAll(boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return findAllCarAdvertisements(pageable);
        return new ArrayList<>(carAdvertisementPaginationRepository.readAllBySold(false, pageable));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void softDelete(long id) throws ResourceNotFoundException, ValidationException {
        CarAdvertisement carAd = findById(id);
        isCorrectUser(carAd.getUser().getId());
        carAd.setSold(true);
        Date date = new Date();
        Timestamp endPublicationDate = new Timestamp(date.getTime());
        carAd.setEndPublicationDate(endPublicationDate);
        carAdvertisementRepository.save(carAd);
    }

    private User isCorrectUser(long id) throws ValidationException {
        User user;
        try {
            user = userServiceImpl.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ValidationException("user not found");
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!user.getEmail().equals(customUserDetails.getUsername()))
            throw new ValidationException("not Correct user");
        return user;

    }

    private CarBrand findCarBrand(long id) throws ValidationException {
        try {
            return carBrandServiceImpl.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ValidationException("carBrand not found");
        }
    }

    private List<Advertisement> findAllCarAdvertisements(Pageable pageable) {
        List<Advertisement> result = new ArrayList<>();
        Iterable<CarAdvertisement> iterable = carAdvertisementPaginationRepository.findAll(pageable);
        Iterator<CarAdvertisement> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
