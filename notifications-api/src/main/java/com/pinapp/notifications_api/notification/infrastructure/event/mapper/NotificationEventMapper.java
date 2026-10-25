package com.pinapp.notifications_api.notification.infrastructure.event.mapper;


import com.pinapp.notifications_contracts.events.notification.NotificationEmailEvent;
import com.pinapp.notifications_contracts.events.notification.NotificationPushCreatedEvent;
import com.pinapp.notifications_contracts.events.notification.NotificationSmsCreatedEvent;
import com.pinapp.notifications_api.common.infrastructure.event.util.MapperUtils;
import com.pinapp.notifications_api.notification.application.email.NotificationEmailCommand;
import com.pinapp.notifications_api.notification.application.push.NotificationPushCommand;
import com.pinapp.notifications_api.notification.application.sms.NotificationSmsCommand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NotificationEventMapper extends MapperUtils {

    NotificationSmsCommand mapToNotificationSmsCommand(NotificationSmsCreatedEvent event);

    NotificationEmailCommand mapToNotificationEmailCommand(NotificationEmailEvent event);

    NotificationPushCommand mapToNotificationPushCommand(NotificationPushCreatedEvent event);

}
