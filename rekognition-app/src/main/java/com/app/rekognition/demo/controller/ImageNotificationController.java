package com.app.rekognition.demo.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ImageNotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public ImageNotificationController (SimpMessagingTemplate template) {
        this.messagingTemplate = template;
    }

    public void sendImageNotification(String imageUrl) {
        System.out.println("Sending...");
        messagingTemplate.convertAndSend("/topic/images", imageUrl);
    }
}
