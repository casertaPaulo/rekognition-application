package com.app.rekognition.demo.application.service;

import com.app.rekognition.demo.domain.exception.AppException;
import com.app.rekognition.demo.infrastructure.aws.AWSCollectionManagementUtil;
import com.app.rekognition.demo.infrastructure.web.dto.faces.FaceResponse;
import com.app.rekognition.demo.infrastructure.web.dto.faces.SearchFaceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
public class FaceDetectionService {
    private final RekognitionClient client;
    private final String COLLECTION_ID = "MyCollection";

    public FaceDetectionService(RekognitionClient rekognitionClient) {
        this.client = rekognitionClient;
    }

    public List<SearchFaceResponse> searchFaceInCollection(MultipartFile file) {
        try {
            SdkBytes imageBytes = SdkBytes.fromByteArray(file.getBytes());
            Image image = Image.builder().bytes(imageBytes).build();

            SearchFacesByImageRequest request = SearchFacesByImageRequest.builder()
                    .image(image)
                    .maxFaces(10)
                    .faceMatchThreshold(80F)
                    .collectionId(COLLECTION_ID)
                    .build();

            SearchFacesByImageResponse response = client.searchFacesByImage(request);

            List<FaceMatch> faceMatchList = response.faceMatches();
            for (FaceMatch faceMatch: faceMatchList) {
                System.out.println("The similarity level is: " + faceMatch.similarity());
                System.out.println();
            }

            return faceMatchList.stream().map(
                    faceMatch -> new SearchFaceResponse(new FaceResponse(
                            faceMatch.face().faceId(),
                            faceMatch.face().confidence()
                    ), faceMatch.similarity())
            ).toList();

        } catch (RekognitionException | IOException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro", e.getMessage());
        }
    }

    public Map<String, String> addToCollection(MultipartFile file) {
        AWSCollectionManagementUtil.createCollectionIfNotExists(client, COLLECTION_ID);
        try {
            SdkBytes imageBytes = SdkBytes.fromByteArray(file.getBytes());
            Image image = Image.builder().bytes(imageBytes).build();

            IndexFacesRequest facesRequest = IndexFacesRequest.builder()
                    .collectionId(COLLECTION_ID)
                    .image(image)
                    .maxFaces(1)
                    .qualityFilter(QualityFilter.AUTO)
                    .detectionAttributes(Attribute.DEFAULT)
                    .build();

            IndexFacesResponse response = client.indexFaces(facesRequest);
            System.out.println("Results for the image");
            System.out.println("\n Faces indexed: ");

            List<FaceRecord> faceRecords = response.faceRecords();
            String faceId = "";
            for (FaceRecord faceRecord : faceRecords) {
                faceId = faceRecord.face().faceId();
                System.out.println("  Face ID: " + faceRecord.face().faceId());
                System.out.println("  Location:" + faceRecord.faceDetail().boundingBox().toString());
            }

            return Map.of("faceId", faceId);

        } catch (RekognitionException | IOException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro", e.getMessage());
        }
    }

    public List<FaceResponse> listFacesCollection() {
        try {
            ListFacesRequest facesRequest = ListFacesRequest.builder()
                    .collectionId(COLLECTION_ID)
                    .maxResults(5)
                    .build();

            ListFacesResponse response = client.listFaces(facesRequest);

            return response.faces().stream().map(
                    face -> new FaceResponse(face.faceId(), face.confidence())
            ).toList();


        } catch (RekognitionException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro", e.getMessage());
        }
    }

    public void deleteFacesCollection() {
        try {
            List<FaceResponse> faceResponseList = listFacesCollection();
            List<String> facesId = faceResponseList.stream()
                    .map(FaceResponse::faceId)
                    .toList();

            DeleteFacesRequest deleteFacesRequest = DeleteFacesRequest.builder()
                    .collectionId(COLLECTION_ID)
                    .faceIds(facesId)
                    .build();

            client.deleteFaces(deleteFacesRequest);
            System.out.println("All faces was deleted from the collection.");

        } catch(RekognitionException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro", e.getMessage());
        }
    }


}
