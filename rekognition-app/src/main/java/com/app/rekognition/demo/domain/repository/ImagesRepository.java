package com.app.rekognition.demo.domain.repository;

import com.app.rekognition.demo.domain.model.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<ImageEntity, Long> {
}
