package com.flavio.ImageGallery.repositories;

import com.flavio.ImageGallery.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String imageName);
    Optional<Image> findByImageTitle(String imageTitle);
}
