package com.flavio.ImageGallery.controllers;

import com.flavio.ImageGallery.dto.ImageDTO;
import com.flavio.ImageGallery.entities.Image;
import com.flavio.ImageGallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String response = imageService.uploadImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String name) {
        Image image = imageService.getInfoByImageName(name);

        return ResponseEntity.status(HttpStatus.OK).body(image);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getImageByName(@PathVariable("name") String name) {
        byte[] image = imageService.getImage(name);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
