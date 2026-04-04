package com.event_notifier_api.processors;

import com.event_notifier_api.entities.Notification;
import com.event_notifier_api.enums.NotificationTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class WebhookChannel implements NotificationChannel {

    @Override
    public boolean supports(NotificationTypeEnum type) {
        return type == NotificationTypeEnum.WEBHOOK;
    }

    @Override
    public void send(Notification notification) {
        System.out.println("WEBHOOK: " + notification.getPayload());
    }
}
