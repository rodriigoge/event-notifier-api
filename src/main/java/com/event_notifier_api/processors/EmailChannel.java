package com.event_notifier_api.processors;

import com.event_notifier_api.entities.Notification;
import com.event_notifier_api.enums.NotificationTypeEnum;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class EmailChannel implements NotificationChannel {

    @Override
    public boolean supports(NotificationTypeEnum type) {
        return type == NotificationTypeEnum.EMAIL;
    }

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000, multiplier = 2.0)
    )
    @Override
    public void send(Notification notification) {
        System.out.println("Tentando EMAIL...");
        throw new RuntimeException("Falha simulada");
    }

    @Recover
    public void recover(Exception exception, Notification notification) {
        System.out.println("EMAIL falhou apos retries");
        throw new RuntimeException("Falha ao enviar notificacao por email", exception);
    }
}
