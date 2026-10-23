package com.pinapp.notifications_api.notification.application.email;

import com.pinapp.notifications_api.common.application.Command;
import com.pinapp.notifications_api.common.application.VoidResponse;
import com.pinapp.notifications_api.notification.domain.entity.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationEmailCommand implements Command<VoidResponse> {
    private NotificationType type;
    private String message;
    private String email;
    private String subject;
}
