package com.pinapp.notifications_api.notification.infrastructure.event.consumer.specific;

import com.pinapp.notifications_contracts.events.notification.NotificationEmailEvent;
import com.pinapp.notifications_api.common.application.Mediator;
import com.pinapp.notifications_api.common.infrastructure.event.consumer.EventSpecificConsumer;
import com.pinapp.notifications_api.notification.application.email.NotificationEmailCommand;
import com.pinapp.notifications_api.notification.infrastructure.event.mapper.NotificationEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationEmailSpecificConsumer implements EventSpecificConsumer<NotificationEmailEvent> {

    private final Mediator mediator;
    private final NotificationEventMapper userEventMapper;

    @Override
    public void accept(Message<NotificationEmailEvent> genericRecordMessage) {
        log.info("UserCreatedSpecificConsumer message: {}", genericRecordMessage.toString());
        NotificationEmailCommand command = userEventMapper.mapToNotificationEmailCommand(genericRecordMessage.getPayload());
        mediator.dispatch(command);
    }

    @Override
    public String getSchema() {
        return NotificationEmailEvent.getClassSchema().getFullName();
    }
}
