package com.pinapp.notifications_api.notification.application.push;


import com.pinapp.notifications_api.common.application.CommandHandler;
import com.pinapp.notifications_api.common.application.VoidResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notifications_lib.common.config.NotificationLibrary;
import notifications_lib.common.exception.NotificationException;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.common.model.enums.NotificationType;
import notifications_lib.push.infrastructure.dto.NotificationPush;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPushHandler implements CommandHandler<NotificationPushCommand, VoidResponse> {
    private final NotificationLibrary notificationLibrary;

    @Override
    public VoidResponse handle(NotificationPushCommand command) {

        NotificationType type = command.getType() != null
                ? NotificationType.valueOf(command.getType().toString().trim().toLowerCase(Locale.ROOT))
                : NotificationType.email;

        NotificationPush notification = new NotificationPush(
                type, command.getMessage(), command.getDestination(), command.getSender(), command.getPermission(),
                command.getTitle());

        NotificationResult result = notificationLibrary
                .sendNotification(notification)
                .join();

        log.info("Resultado de la notificación: id {}, canal {}, proveedor {}, status {}",
                result.notificationId(),
                result.channel(),
                result.provider(),
                result.status()
        );

        if((result.status()!=null && result.status().equals(NotificationStatus.FAILED)) || result.error()!=null ){
            throw new NotificationException("se produjo un error al generar la notificacion! ");
        }

        return new VoidResponse();
    }

    @Override
    public Class<NotificationPushCommand> getCommandType() {
        return NotificationPushCommand.class;
    }
}
