package com.flavio.ImageGallery.controllers;

import com.flavio.ImageGallery.dto.ImageDTO;
import com.flavio.ImageGallery.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> uploadImage(@RequestParam("imageTitle") String title, @RequestParam("imageName") MultipartFile file) throws IOException {
        imageService.uploadImage(title, file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    @CrossOrigin
    public List<ImageDTO> getAll() {
        return imageService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        imageService.deleteImage(id);
    }
}