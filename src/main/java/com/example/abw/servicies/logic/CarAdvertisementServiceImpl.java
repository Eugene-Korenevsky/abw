package com.example.abw.servicies.logic;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.car_advertisement.CarAdvertisementRequest;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.pagination.advertisement.car_advertisement.CarAdvertisementPaginationRepository;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.security.utils.UserUtil;
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

    private final CarAdvertisementRepository carAdvertisementRepository;
    private final CarBrandService carBrandServiceImpl;
    private final UserService userServiceImpl;
    private final CarAdvertisementPaginationRepository carAdvertisementPaginationRepository;
    private final UserUtil userUtil;

    public CarAdvertisementServiceImpl(CarAdvertisementRepository carAdvertisementRepository, UserService userServiceImpl,
                                       CarBrandService carBrandServiceImpl,
                                       CarAdvertisementPaginationRepository carAdvertisementPaginationRepository,
                                       UserUtil userUtil
    ) {
        super(carAdvertisementRepository, CarAdvertisement.class);
        this.carAdvertisementRepository = carAdvertisementRepository;
        this.userServiceImpl = userServiceImpl;
        this.carBrandServiceImpl = carBrandServiceImpl;
        this.carAdvertisementPaginationRepository = carAdvertisementPaginationRepository;
        this.userUtil = userUtil;
    }

    @Override
    public CarAdvertisement createCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest)
            throws ValidationException, IOException, ResourceNotFoundException {
        CustomUserDetails customUserDetails = userUtil.getCustomUserDetails();
        User user = userServiceImpl.findByEmail(customUserDetails.getUsername());
        if (user != null) {
            Set<CarImage> carImages = new HashSet<>();
            CarAdvertisement carAd = new CarAdvertisement();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            CarBrand carBrand = findCarBrand(carAdvertisementRequest.getCarBrandId());
            carAd.setPublicationDate(timestamp);
            carAd.setCarBrand(carBrand);
            carAd.setDescriptions(carAdvertisementRequest.getDescriptions());
            carAd.setPrice(carAdvertisementRequest.getPrice());
            carAd.setUser(user);
            carAd = create(carAd);
            for (MultipartFile multipartImage : carAdvertisementRequest.getImages()) {
                if (multipartImage != null) {
                    CarImage carImage = new CarImage();
                    carImage.setContent(multipartImage.getBytes());
                    carImage.setCarAd(carAd);
                    carImages.add(carImage);
                }
            }
            carAd.setCarImages(carImages);
            return create(carAd);
        }
        throw new ResourceNotFoundException("user not found");
    }

    @Transactional
    @Override
    public CarAdvertisement updateCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest, long id)
            throws ValidationException, ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAd = findById(id);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAd.getUser().getEmail())) {
            CarBrand carBrand = findCarBrand(carAdvertisementRequest.getCarBrandId());
            carAd.setCarBrand(carBrand);
            carAd.setDescriptions(carAdvertisementRequest.getDescriptions());
            carAd.setPrice(carAdvertisementRequest.getPrice());
            update(carAd, id);
            return findById(id);
        }
        throw new PrivacyViolationException("privacy violation");

    }

    @Transactional
    @Override
    public List<Advertisement> findAllByCarBrand(String carBrand, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin)
            return new ArrayList<>(carAdvertisementPaginationRepository.findByCarBrand_Name(carBrand, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_NameAndSold(carBrand, false, pageable));
    }


    @Transactional
    @Override
    public List<Advertisement> findAllByCarBrandName(String carBrandName, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_CarBrandName_Name(carBrandName, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository
                .findByCarBrand_CarBrandName_NameAndSold(carBrandName, false, pageable));
    }

    @Transactional
    @Override
    public List<Advertisement> findAllByPrice(Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return new ArrayList<>(carAdvertisementPaginationRepository.
                readAllByPriceBetween(startPrice, endPrice, pageable));
        else return new ArrayList<>(carAdvertisementPaginationRepository.
                readAllByPriceBetweenAndSold(startPrice, endPrice, false, pageable));
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public List<Advertisement> findAll(boolean isAdmin, PageableParams pageableParams) {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        if (isAdmin) return findAllCarAdvertisements(pageable);
        return new ArrayList<>(carAdvertisementPaginationRepository.readAllBySold(false, pageable));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void softDelete(long id)
            throws ResourceNotFoundException, ValidationException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(id);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            carAdvertisement.setSold(true);
            Date date = new Date();
            Timestamp endPublicationDate = new Timestamp(date.getTime());
            carAdvertisement.setEndPublicationDate(endPublicationDate);
            carAdvertisementRepository.save(carAdvertisement);
        } else throw new PrivacyViolationException("privacy violation");
    }


    @Override
    public CarAdvertisement findAdvertisement(long advertisementId) throws ResourceNotFoundException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (carAdvertisement.isSold()) throw new ResourceNotFoundException("advertisement not found");
        return carAdvertisement;
    }

    @Override
    public CarAdvertisement findUserAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail()))
            return carAdvertisement;
        throw new PrivacyViolationException("privacy violation");
    }

    @Transactional
    @Override
    public List<CarAdvertisement> findAllByUser(PageableParams pageableParams) throws ResourceNotFoundException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        CustomUserDetails customUserDetails = userUtil.getCustomUserDetails();
        if (customUserDetails != null) {
            User user = userServiceImpl.findByEmail(customUserDetails.getUsername());
            return carAdvertisementPaginationRepository.readAllByUser(user, pageable);
        }
        throw new ResourceNotFoundException("user not found");
    }

    @Override
    public CarAdvertisement refreshCarAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            Date date = new Date();
            Timestamp currentTime = new Timestamp(date.getTime());
            carAdvertisement.setPublicationDate(currentTime);
            carAdvertisement.setEndPublicationDate(null);
            carAdvertisement.setSold(false);
            return carAdvertisementRepository.save(carAdvertisement);
        }
        throw new PrivacyViolationException("privacy violation");
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
