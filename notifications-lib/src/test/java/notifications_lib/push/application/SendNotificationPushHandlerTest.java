package notifications_lib.push.application;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.push.application.send.SendNotificationPushHandler;
import notifications_lib.push.application.send.SendNotificationPushRequest;
import notifications_lib.push.domain.entity.Push;
import notifications_lib.push.domain.port.PushRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class SendNotificationPushHandlerTest {
    @Test
    void transformaRequestYLlamaAlRepositorio() {
        PushRepository repository = mock(PushRepository.class);
        NotificationResult expected = new NotificationResult("id-3", NotificationChannel.PUSH,
                NotificationStatus.SENT, "test", "provider-3", LocalTime.now(), LocalTime.now(), null);
        when(repository.sendPushNotificacion(any(Push.class))).thenReturn(expected);
        SendNotificationPushHandler handler = new SendNotificationPushHandler(repository);

        NotificationResult actual = handler.handler(new SendNotificationPushRequest(
                "Mensaje push de prueba", "device-123", "Aplicacion", "alertas", "Título válido")).join();

        assertEquals(expected, actual);
        verify(repository).sendPushNotificacion(argThat(push ->
                "Mensaje push de prueba".equals(push.getMessage())
                && "device-123".equals(push.getDestination())
                && "Aplicacion".equals(push.getSender())
                && "alertas".equals(push.getPermission())
                && "Título válido".equals(push.getTitle())));
    }
}
