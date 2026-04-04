package com.event_notifier_api.dtos;

import com.event_notifier_api.enums.NotificationStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        NotificationStatusEnum status,
        LocalDateTime createdAt
) {}
