package com.example.abw.controllers.user.car_advertisement;

import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.servicies.CarImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("userImageController")
@RequestMapping("user/images")
public class ImageController {
    @Autowired
    private CarImageService carImageService;

    @PostMapping
    ResponseEntity<?> uploadImage(@RequestParam MultipartFile multipartImage,
                                  @RequestParam Long carAdId) throws IOException,
            ValidationException, ResourceNotFoundException, PrivacyViolationException {
        return new ResponseEntity<>(carImageService.
                createAdvertisementImage(multipartImage.getBytes(), carAdId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable long imageId)
            throws ResourceNotFoundException, PrivacyViolationException {
        carImageService.deleteCarAdvertisementImage(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
