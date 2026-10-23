package notifications_lib.common.model;

import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;

import java.time.LocalTime;



public record NotificationResult (
        String notificationId,
        NotificationChannel channel,
        NotificationStatus status,
        String provider,
        String providerMessageId,
        LocalTime createdAt,
        LocalTime completedAt,
        NotificationError error
) {}
