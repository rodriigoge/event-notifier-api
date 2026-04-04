package com.event_notifier_api.consumers;

import com.event_notifier_api.configurations.RabbitMQConfig;
import com.event_notifier_api.dtos.NotificationCreatedEvent;
import com.event_notifier_api.processors.NotificationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationProcessor processor;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void consume(NotificationCreatedEvent event) {
        processor.process(event);
    }
}
