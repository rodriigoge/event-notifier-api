package com.event_notifier_api.mappers;

import com.event_notifier_api.dtos.NotificationRequest;
import com.event_notifier_api.dtos.NotificationResponse;
import com.event_notifier_api.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "notificationId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Notification toEntity(NotificationRequest request);

    @Mapping(target = "id", source = "notificationId")
    NotificationResponse toResponse(Notification notification);
}
