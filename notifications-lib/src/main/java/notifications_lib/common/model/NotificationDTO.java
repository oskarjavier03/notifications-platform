package notifications_lib.common.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import notifications_lib.common.model.enums.NotificationType;

@Getter
@Setter
@AllArgsConstructor
public abstract class NotificationDTO {
    @NotNull(message = "El tipo de notificacion es requerido")
    private NotificationType type;


}
