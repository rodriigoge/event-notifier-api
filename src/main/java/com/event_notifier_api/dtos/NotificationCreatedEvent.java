package com.event_notifier_api.dtos;

import com.event_notifier_api.enums.NotificationTypeEnum;

import java.util.UUID;

public record NotificationCreatedEvent(
        UUID notificationId,
        NotificationTypeEnum type,
        String payload
) {
}
