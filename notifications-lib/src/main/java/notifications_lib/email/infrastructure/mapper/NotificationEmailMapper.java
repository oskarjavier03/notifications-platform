package notifications_lib.email.infrastructure.mapper;

import notifications_lib.email.application.send.SendNotificationEmailRequest;
import notifications_lib.email.infrastructure.dto.NotificationEmail;
import org.mapstruct.Mapper;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper
public interface NotificationEmailMapper {
    SendNotificationEmailRequest mapToSendNotificationRequest(NotificationEmail notification);
}
