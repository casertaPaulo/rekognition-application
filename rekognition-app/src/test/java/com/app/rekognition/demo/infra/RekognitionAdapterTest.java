package com.app.rekognition.demo.infra;

import com.app.rekognition.demo.api.dto.ModerationLabels;
import com.app.rekognition.demo.infrastructure.aws.RekognitionAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RekognitionAdapterTest {

    @Autowired
    private RekognitionAdapter rekognitionAdapter;

    @Test
    public void shouldReturnModerationList() throws IOException {
        FileInputStream inputStream = new FileInputStream("imgs/faca.jpg");
        MultipartFile file = new MockMultipartFile(
                "file",
                "faca.jpg",
                "image/jpeg",
                inputStream
        );

        ModerationLabels response = rekognitionAdapter.detectModeration(file.getBytes());

        System.out.println(response.labels());
        assertFalse(response.labels().get(0).confidence().isNaN());

    }


}
