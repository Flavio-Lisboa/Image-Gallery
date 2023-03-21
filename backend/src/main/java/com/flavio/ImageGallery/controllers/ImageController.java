package com.flavio.ImageGallery.controllers;

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
    @CrossOrigin
    public ResponseEntity<?> uploadImage(@RequestParam("title") String title, @RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(title, file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String name) {
        Image image = imageService.getInfoByImageName(name);

        return ResponseEntity.status(HttpStatus.OK).body(image);
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getImageByTitle(@PathVariable("title") String title) {
        byte[] image = imageService.getImage(title);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(image);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }
}
