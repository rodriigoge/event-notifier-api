package com.event_notifier_api.processors;

import com.event_notifier_api.entities.Notification;
import com.event_notifier_api.enums.NotificationTypeEnum;

public interface NotificationChannel {
    boolean supports(NotificationTypeEnum type);
    void send(Notification notification);
}
