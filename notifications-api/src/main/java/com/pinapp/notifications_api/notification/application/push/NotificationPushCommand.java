package com.pinapp.notifications_api.notification.application.push;


import com.pinapp.notifications_api.common.application.Command;
import com.pinapp.notifications_api.common.application.VoidResponse;
import com.pinapp.notifications_api.notification.domain.entity.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationPushCommand implements Command<VoidResponse> {
    private NotificationType type;
    private String message;
    private String destination;
    private String sender;
    private String permission;
    private String title;
}
