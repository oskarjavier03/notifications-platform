package notifications_lib.push.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import notifications_lib.common.model.NotificationDTO;
import notifications_lib.common.model.enums.NotificationType;


@Setter
@Getter
public class NotificationPush extends NotificationDTO {

    @Size(min = 10, max = 50, message = "el mensaje debe tener al menos 10 caracteres y no superar los 50 caracteres")
    private String message;
    @NotBlank(message = "El destino es obligatorio")
    private String destination;
    @NotBlank(message = "El contenido es obligatorio")
    private String sender;
    @NotBlank(message = "los permisos son obligatorio")
    private String permission;
    @NotBlank(message = "El titulo es requerido")
    private String title;

    public NotificationPush(NotificationType type, String message, String destination, String sender, String permission, String title) {
        super(type);
        this.message = message;
        this.destination = destination;
        this.sender = sender;
        this.permission = permission;
        this.title = title;
    }
}
