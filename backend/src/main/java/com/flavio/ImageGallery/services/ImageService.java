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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void uploadImage(String title, MultipartFile file) throws IOException {
        imageRepository.save(Image.builder()
                .imageTitle(title)
                .imageName(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
    }

    @Transactional
    public List<ImageDTO> getAll() {
        List<Image> dbImage = imageRepository.findAll();
        List<ImageDTO> listImageDTO = new ArrayList<>();
        dbImage.forEach(image ->  {
            byte[] imageData = ImageUtil.decompressImage(image.getImageData());
            String base64 = Base64.getEncoder().encodeToString(imageData);
            ImageDTO imageDTO = new ImageDTO(image, base64);
            listImageDTO.add(imageDTO);
        });
        return listImageDTO;
    }

    @Transactional
    public void deleteImage(Long id) {
        if(imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        }
    }
}
