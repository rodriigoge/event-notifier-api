package com.event_notifier_api.dtos;

import com.event_notifier_api.enums.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationRequest(
        @NotNull NotificationTypeEnum type,
        @NotBlank String payload
) {
}
