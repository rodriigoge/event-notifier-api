package com.event_notifier_api.repositories;

import com.event_notifier_api.entities.Notification;
import com.event_notifier_api.enums.NotificationStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByStatus(NotificationStatusEnum status);
}
