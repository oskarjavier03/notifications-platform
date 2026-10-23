package notifications_lib.push.infrastructure.mapper;

import notifications_lib.push.application.send.SendNotificationPushRequest;
import notifications_lib.push.infrastructure.dto.NotificationPush;
import org.mapstruct.Mapper;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper
public interface NotificationPushMapper {
    SendNotificationPushRequest mapToSendNotificationPushRequest(NotificationPush notification);
}
