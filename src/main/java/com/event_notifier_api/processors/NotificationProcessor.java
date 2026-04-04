package com.event_notifier_api.processors;

import com.event_notifier_api.dtos.NotificationCreatedEvent;
import com.event_notifier_api.enums.NotificationStatusEnum;
import com.event_notifier_api.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationProcessor {

    private final NotificationRepository repository;
    private final List<NotificationChannel> channels;

    public void process(NotificationCreatedEvent event) {
        var notification = repository.findById(event.notificationId()).orElseThrow();
        try {
            channels.stream()
                    .filter(channel -> channel.supports(event.type()))
                    .forEach(channel -> channel.send(notification));
            notification.setStatus(NotificationStatusEnum.SENT);
        } catch (Exception e) {
            notification.setStatus(NotificationStatusEnum.FAILED);
        }
        repository.save(notification);
    }
}
