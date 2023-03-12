package com.flavio.ImageGallery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDTO {

    private Long id;
    private String imageName;
    private String type;
    private byte[] imageData;
}
