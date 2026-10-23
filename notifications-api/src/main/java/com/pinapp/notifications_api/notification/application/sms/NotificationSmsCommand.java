package com.pinapp.notifications_api.notification.application.sms;


import com.pinapp.notifications_api.common.application.Command;
import com.pinapp.notifications_api.common.application.VoidResponse;
import com.pinapp.notifications_api.notification.domain.entity.NotificationType;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class NotificationSmsCommand implements Command<VoidResponse> {
    private NotificationType type;
    private String message;
    private String numberCelPhone;
}
