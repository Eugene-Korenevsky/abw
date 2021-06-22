package com.example.abw.controllers.image;

import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.repositories.advertisement.CarImageRepository;
import com.example.abw.servicies.CarImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("images")
public class ImageController {
    @Autowired
    private CarImageRepository carImageRepository;
    @Autowired
    private CarImageService carImageService;

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadImage(@PathVariable Long imageId) throws ResourceNotFoundException {
        Image image = carImageService.findById(imageId);
        return new ResponseEntity<>(new ByteArrayResource(image.getImage()), HttpStatus.OK);
    }

    @GetMapping("/byCarAdvertisement/{carAdId}")
    public ResponseEntity<?> getAllByCarAd(@PathVariable Long carAdId) throws ResourceNotFoundException {
        return new ResponseEntity<>(carImageService.getImagesByCarAdvertisement(carAdId), HttpStatus.OK);
    }


}
