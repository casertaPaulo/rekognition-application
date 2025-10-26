package com.app.rekognition.demo.application.service;

import com.app.rekognition.demo.infrastructure.web.dto.ImageListResponse;
import com.app.rekognition.demo.domain.model.ImageEntity;
import com.app.rekognition.demo.application.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersistenceService {

    private final ImagesRepository repository;

    public PersistenceService(ImagesRepository repository) {
        this.repository = repository;
    }

    public ImageListResponse getAll() {
        List<String> result = repository.findAll().stream()
                .map(ImageEntity::getImageUrl).toList();

        return new ImageListResponse(result);
    }

    // Salva o link retornado da imagem ‘online’ (S3) no banco de dados
    public void saveImageUrl(String url) {
        repository.save(new ImageEntity(url));
    }

    public void deleteAll() {
        // Todo: throw exception if data non exists
        repository.deleteAll();
    }
}
