package notifications_lib.email.application.customerStrategy;

import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import notifications_lib.common.exception.NotificationBadRequestException;
import notifications_lib.common.strategy.NotificationStrategy;
import notifications_lib.common.validation.ValidationConfiguration;
import notifications_lib.email.application.send.SendNotificationEmailRequest;
import notifications_lib.email.infrastructure.dto.NotificationEmail;
import notifications_lib.email.infrastructure.mapper.NotificationEmailMapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.StringJoiner;

@RequiredArgsConstructor
public class EmailNotificationStrategyImpl implements NotificationStrategy<NotificationEmail> {
    private final ValidationConfiguration validate;
    private NotificationEmailMapper mapper= Mappers.getMapper(NotificationEmailMapper.class);


    @Override
    public Class<NotificationEmail> getNotificationType() {
        return NotificationEmail.class;
    }

    @Override
    public void validate(NotificationEmail notification) {
        Set<ConstraintViolation<NotificationEmail>> violaciones = validate.getValidator().validate(notification);
        if(!violaciones.isEmpty()){
            StringJoiner errors= new StringJoiner("\n");
            for (ConstraintViolation<NotificationEmail> violacion : violaciones){
                errors.add(violacion.getPropertyPath()+ ": "+violacion.getMessage());
            }
            throw new NotificationBadRequestException(errors.toString()) ;
        }
    }

    @Override
    public SendNotificationEmailRequest toRequest(NotificationEmail notification) {
        return mapper.mapToSendNotificationRequest(notification);
    }
}
