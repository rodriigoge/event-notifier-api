package com.event_notifier_api.api;

import com.event_notifier_api.dtos.NotificationRequest;
import com.event_notifier_api.dtos.NotificationResponse;
import com.event_notifier_api.services.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody NotificationRequest notificationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(notificationRequest));
    }
}
