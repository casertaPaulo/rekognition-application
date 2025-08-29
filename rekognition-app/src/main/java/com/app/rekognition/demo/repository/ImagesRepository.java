package com.app.rekognition.demo.repository;

import com.app.rekognition.demo.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<ImageEntity, Long> {
}
