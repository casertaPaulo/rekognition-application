package com.app.rekognition.demo.service;

import com.app.rekognition.demo.application.dto.ModerationLabels;
import com.app.rekognition.demo.application.ports.out.ContentModerationPort;
import com.app.rekognition.demo.application.service.ContentModerationService;
import com.app.rekognition.demo.domain.exception.FileInvalidTypeException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ContentModerationServiceTest {

    private final ContentModerationPort contentModerationPort = Mockito.mock(ContentModerationPort.class);
    private final ContentModerationService moderationService = new ContentModerationService(contentModerationPort);

    @Test
    public void shouldThrowExceptionWhenFileIsDifferentFromImage() {
        MockMultipartFile file = new MockMultipartFile("file", "document.pdf", "application/pdf", "content".getBytes());

        assertThrows(FileInvalidTypeException.class, () -> moderationService.getModerationLabels(file));

        verifyNoInteractions(contentModerationPort);
    }


    @Test
    public void shouldCallsPortsWhenFileIsImage() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "bytes".getBytes());

        ModerationLabels mockedResult = new ModerationLabels(List.of());
        when(contentModerationPort.detectModeration(any())).thenReturn(mockedResult);


        ModerationLabels result = moderationService.getModerationLabels(file);
        assertNotNull(result);
        verify(contentModerationPort, times(1)).detectModeration(any());
    }



//    @Test
//    public void shouldCallsPortsWhenFileIsImage() throws IOException {
//        MockMultipartFile file = new MockMultipartFile("file", "image.jpg", "image/jpeg", "bytes".getBytes());
//
//        ModerationLabels fakeResult = new ModerationLabels(List.of());
//        when(contentModerationPort.detectModeration(any())).thenReturn(fakeResult);
//
//        // action
//        ModerationLabels result = moderationService.analyze(file);
//
//        assertNotNull(result);
//        verify(contentModerationPort, times(1)).detectModeration(any());
//
//    }
}
