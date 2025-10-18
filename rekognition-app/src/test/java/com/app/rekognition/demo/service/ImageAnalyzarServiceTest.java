package com.app.rekognition.demo.service;

import com.app.rekognition.demo.domain.service.ContentModerationService;
import com.app.rekognition.demo.domain.service.ImageAnalyzerService;

import com.app.rekognition.demo.domain.service.StorageService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

/**
 * This is a unit test
 *
 *
 */
@ExtendWith(MockitoExtension.class)
public class ImageAnalyzarServiceTest {
    @Mock
    private StorageService storageService;

    @Mock
    private ContentModerationService moderationService;

    @InjectMocks
    private ImageAnalyzerService imageAnalyzerService;

    // Todo: rewrite this from scratch [ new implementation ]
//    @Test
//    public void shouldUploadImageWhenModerationIsApproved() throws IOException {
//        MultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "bytes".getBytes());
//
//        when(moderationService.detectModeration(file))
//                .thenReturn(new ModerationResultDTO(false, null));
//
//        when(storageService.uploadImage(file))
//                .thenReturn("http://s3.aws.com/jorge.jpg");
//
//        ImageResponseDTO response = imageAnalyzerService.analyzeImage(file);
//
//        assertNotNull(response);
//        assertEquals("http://s3.aws.com/jorge.jpg", response.urlToImage());
//
//        verify(moderationService).detectModeration(file);
//        verify(storageService).uploadImage(file);
//    }
}
