package notifications_lib.sms.application;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.sms.application.send.SendNotificationSmsHandler;
import notifications_lib.sms.application.send.SendNotificationSmsRequest;
import notifications_lib.sms.domain.entity.Sms;
import notifications_lib.sms.domain.port.SmsRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class SendNotificationSmsHandlerTest {
    @Test
    void transformaRequestYLlamaAlRepositorio() {
        SmsRepository repository = mock(SmsRepository.class);
        NotificationResult expected = new NotificationResult("id-2", NotificationChannel.SMS,
                NotificationStatus.SENT, "test", "provider-2", LocalTime.now(), LocalTime.now(), null);
        when(repository.sendSmsNotificacion(any(Sms.class))).thenReturn(expected);
        SendNotificationSmsHandler handler = new SendNotificationSmsHandler(repository);

        NotificationResult actual = handler.handler(new SendNotificationSmsRequest(
                "Mensaje SMS de prueba", "+1 212-555-1234")).join();

        assertEquals(expected, actual);
        verify(repository).sendSmsNotificacion(argThat(sms ->
                "Mensaje SMS de prueba".equals(sms.getMessage())
                && "+1 212-555-1234".equals(sms.getNumberCelPhone())));
    }
}
