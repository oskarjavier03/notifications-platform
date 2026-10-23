package notifications_lib.sms.infrastructure.mapper;

import notifications_lib.sms.application.send.SendNotificationSmsRequest;
import notifications_lib.sms.infrastructure.DTO.NotificationSms;
import org.mapstruct.Mapper;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
@Mapper
public interface NotificationSmsMapper {
    SendNotificationSmsRequest mapToSendNotificationSmsRequest(NotificationSms notification);
}
