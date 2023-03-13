package com.flavio.ImageGallery.services;

import com.flavio.ImageGallery.dto.ImageDTO;
import com.flavio.ImageGallery.entities.Image;
import com.flavio.ImageGallery.repositories.ImageRepository;
import com.flavio.ImageGallery.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        imageRepository.save(Image.builder()
                .imageName(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return "Image Uploaded successfully: " + file.getOriginalFilename();
    }

    @Transactional
    public Image getInfoByImageName(String name) {
        Optional<Image> dbImage = imageRepository.findByImageName(name);

        return Image.builder()
                .id(dbImage.get().getId())
                .imageName(dbImage.get().getImageName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<Image> dbImage = imageRepository.findByImageName(name);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }
}
