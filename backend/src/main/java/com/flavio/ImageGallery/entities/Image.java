package com.flavio.ImageGallery.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageTitle;
    private String imageName;
    @JsonIgnore
    private String type;

    @Lob
    @Column(length = 1000)
    @JsonIgnore
    private byte[] imageData;
}
