package com.app.rekognition.demo.service;

import com.app.rekognition.demo.DTO.ImageListDTO;
import com.app.rekognition.demo.entity.ImageEntity;
import com.app.rekognition.demo.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImagesRepository repository;

    public ImageListDTO getAll() {
        List<String> result = repository.findAll().stream().map(
                ImageEntity::getImageUrl
        ).toList();

        return new ImageListDTO(result);
    }

    public void saveImageUrl(String url) {
        repository.save(new ImageEntity(url));
    }

    public void deleteAll() {
        // todo: lançar exceção não existir registros
        repository.deleteAll();
    }


}
