package com.flavio.ImageGallery.dto;

import com.flavio.ImageGallery.entities.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDTO {
    private Image image;
    private String imageData;

    public ImageDTO(Image image, String imageData) {
        this.image = image;
        this.imageData = imageData;
    }
}
