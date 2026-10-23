package com.pinapp.notifications_client.notification.infrastructure.api;


import com.pinapp.notifications_client.notification.domain.port.NotificationRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationClientContorller {
    private final NotificationRepository notificationRepository;

    @GetMapping("/created/{amount}")
    public void creteNotificationes(@PathVariable("amount") Integer amount){
        for (int i = 0; i < amount; i++) {
            notificationRepository.generateNotificationEmailAsync(i);
        }

        for (int i = 0; i < amount; i++) {
            notificationRepository.generateNotificationPushAsync(i);
        }

        for (int i = 0; i < amount; i++) {
            notificationRepository.generateNotificationSmsAsync(i);
        }

    }


}
