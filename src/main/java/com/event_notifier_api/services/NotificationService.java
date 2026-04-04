package com.event_notifier_api.services;

import com.event_notifier_api.dtos.NotificationCreatedEvent;
import com.event_notifier_api.dtos.NotificationRequest;
import com.event_notifier_api.dtos.NotificationResponse;
import com.event_notifier_api.mappers.NotificationMapper;
import com.event_notifier_api.producers.NotificationProducer;
import com.event_notifier_api.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationProducer notificationProducer;

    public NotificationResponse createNotification(NotificationRequest request) {
        var notificationEntity = notificationMapper.toEntity(request);
        var notificationSaved = notificationRepository.save(notificationEntity);
        var event = new NotificationCreatedEvent(
                notificationSaved.getNotificationId(),
                notificationSaved.getType(),
                notificationSaved.getPayload()
        );
        notificationProducer.send(event);
        return notificationMapper.toResponse(notificationSaved);
    }
}
