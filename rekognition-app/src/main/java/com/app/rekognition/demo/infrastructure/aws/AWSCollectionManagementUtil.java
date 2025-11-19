package com.app.rekognition.demo.infrastructure.aws;

import com.app.rekognition.demo.domain.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.util.List;

// classe auxilia na criação e listagem de coleções existentes

@Component
public class AWSCollectionManagementUtil {

    // Tenta criar a coleção caso não exista
    public static void createCollectionIfNotExists(RekognitionClient client, String collectionId) {
        System.out.println("CRIANDO COLEÇÃO: " + collectionId);

        try {
            CreateCollectionRequest collectionRequest = CreateCollectionRequest.builder()
                    .collectionId(collectionId)
                    .build();

            CreateCollectionResponse collectionResponse = client.createCollection(collectionRequest);
            System.out.println("Coleção criada com sucesso: " + collectionResponse.collectionArn() + " | " + collectionResponse.statusCode());
        } catch (ResourceAlreadyExistsException e) {
            System.out.println("A coleção já existe. Usando coleção existente...");
        } catch (RekognitionException e) {
            System.out.println("❌Erro ao criar coleção:  " + e.getMessage());
        }
    }

    public List<String> listCollections(RekognitionClient client) {
        System.out.println("LISTANDO COLEÇÕES...");

        try {
            ListCollectionsRequest listCollectionsRequest = ListCollectionsRequest.builder()
                    .maxResults(10)
                    .build();

            ListCollectionsResponse response = client.listCollections(listCollectionsRequest);
            return response.collectionIds();
        } catch (RekognitionException e) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Erro ao listar", e.getMessage());
        }
    }
}
