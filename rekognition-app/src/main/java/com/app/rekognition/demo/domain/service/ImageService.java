package com.app.rekognition.demo.domain.service;

import com.app.rekognition.demo.api.dto.ImageListDTO;
import com.app.rekognition.demo.domain.model.ImageEntity;
import com.app.rekognition.demo.domain.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private final ImagesRepository repository;

    public ImageService(ImagesRepository repository) {
        this.repository = repository;
    }

    public ImageListDTO getAll() {
        List<String> result = repository.findAll().stream()
                .map(ImageEntity::getImageUrl).toList();

        return new ImageListDTO(result);
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
