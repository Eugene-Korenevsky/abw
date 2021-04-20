package com.example.abw;


import com.example.abw.entities.ad.Ad;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import com.example.abw.servicies.CarAdService;
import com.example.abw.model.pageable.sort_kind.SortKind;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            CarAdService carAdServiceImpl,
            PageableParamsUtil pageableParamsUtilImpl
    ) {
        return args -> {
            PageableParams pageableParams;
            pageableParams = pageableParamsUtilImpl.getPageableParams(1, 1, null, "desc");
            pageableParams.setSortKind(SortKind.DESC);
            pageableParams.setPage(0);
            pageableParams.setSize(10);
            System.out.println("byBrand");
            List<Ad> carAds = carAdServiceImpl.findAllByCarBrand("100", false, pageableParams);
            for (Ad carAd : carAds) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " " +
                        carAd.getId() + " sold " + carAd.isSold());
            }
            System.out.println("byBrandName");
            List<Ad> carAds1 = carAdServiceImpl.findAllByCarBrandName("Audi", false, pageableParams);
            for (Ad carAd : carAds1) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " id : " +
                        carAd.getId() + " sold " + carAd.isSold());
            }
            System.out.println("byPrice");
            List<Ad> carAds2 = carAdServiceImpl.findAllByPrice(250000L, 400000L, false, pageableParams);
            for (Ad carAd : carAds2) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " id : " +
                        carAd.getId() + " price" + carAd.getPrice() + " sold " + carAd.isSold());
            }
            System.out.println("byBrandAndPrice");
            List<Ad> carAds3 = carAdServiceImpl.findAllByPriceAndCarBrand(
                    250000L, 400000L, "100", false, pageableParams);
            for (Ad carAd : carAds3) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " id : " +
                        carAd.getId() + " price" + carAd.getPrice() + " sold " + carAd.isSold());
            }
            System.out.println("byBrandNameAndPrice");
            List<Ad> carAds4 = carAdServiceImpl.findAllByPriceAndCarBrandName(200000L, 400000L,
                    "Audi", false, pageableParams);
            for (Ad carAd : carAds4) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " id : " +
                        carAd.getId() + " price" + carAd.getPrice() + " sold " + carAd.isSold());
            }

        };
    }
}
