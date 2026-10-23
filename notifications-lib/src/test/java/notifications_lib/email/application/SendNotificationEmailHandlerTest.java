package notifications_lib.email.application;

import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.email.application.send.SendNotificationEmailHandler;
import notifications_lib.email.application.send.SendNotificationEmailRequest;
import notifications_lib.email.domain.entity.Email;
import notifications_lib.email.domain.port.EmailRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class SendNotificationEmailHandlerTest {
    @Test
    void transformaRequestYLlamaAlRepositorio() {
        EmailRepository repository = mock(EmailRepository.class);
        NotificationResult expected = new NotificationResult("id-1", NotificationChannel.EMAIL,
                NotificationStatus.SENT, "test", "provider-1", LocalTime.now(), LocalTime.now(), null);
        when(repository.sendEmailNotificacion(any(Email.class))).thenReturn(expected);
        SendNotificationEmailHandler handler = new SendNotificationEmailHandler(repository);

        NotificationResult actual = handler.handler(new SendNotificationEmailRequest(
                "Mensaje de prueba", "persona@example.com", "Asunto de prueba")).join();

        assertEquals(expected, actual);
        verify(repository).sendEmailNotificacion(argThat(email ->
                "Mensaje de prueba".equals(email.getMessage())
                && "persona@example.com".equals(email.getEmail())
                && "Asunto de prueba".equals(email.getSubject())));
    }
}
