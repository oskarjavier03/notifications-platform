package notifications_lib.common.config;

import notifications_lib.common.exception.NotificationBadRequestException;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.model.enums.NotificationChannel;
import notifications_lib.common.model.enums.NotificationStatus;
import notifications_lib.common.model.enums.NotificationType;
import notifications_lib.email.domain.entity.Email;
import notifications_lib.email.domain.port.EmailRepository;
import notifications_lib.email.infrastructure.dto.NotificationEmail;
import notifications_lib.push.domain.entity.Push;
import notifications_lib.push.domain.port.PushRepository;
import notifications_lib.push.infrastructure.dto.NotificationPush;
import notifications_lib.sms.domain.entity.Sms;
import notifications_lib.sms.domain.port.SmsRepository;
import notifications_lib.sms.infrastructure.DTO.NotificationSms;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class NotificationLibraryTest {
    private NotificationResult result(NotificationChannel channel) {
        return new NotificationResult("test-id", channel, NotificationStatus.SENT,
                "mock-provider", "mock-message-id", LocalTime.now(), LocalTime.now(), null);
    }

    @Test
    void enviaEmailPorLaApiPublica() {
        EmailRepository email = mock(EmailRepository.class);
        SmsRepository sms = mock(SmsRepository.class);
        PushRepository push = mock(PushRepository.class);
        NotificationResult expected = result(NotificationChannel.EMAIL);
        when(email.sendEmailNotificacion(any(Email.class))).thenReturn(expected);

        try (NotificationLibrary library = NotificationFactory.create(email, sms, push)) {
            NotificationResult actual = library.sendNotification(new NotificationEmail(
                    "Este es un mensaje de prueba para email", "persona@example.com",
                    "Asunto de prueba", NotificationType.email)).join();

            assertEquals(expected, actual);
            verify(email).sendEmailNotificacion(argThat(e ->
                    "persona@example.com".equals(e.getEmail())
                    && "Asunto de prueba".equals(e.getSubject())));
            verifyNoInteractions(sms, push);
        }
    }

    @Test
    void enviaSmsPorLaApiPublica() {
        EmailRepository email = mock(EmailRepository.class);
        SmsRepository sms = mock(SmsRepository.class);
        PushRepository push = mock(PushRepository.class);
        NotificationResult expected = result(NotificationChannel.SMS);
        when(sms.sendSmsNotificacion(any(Sms.class))).thenReturn(expected);

        try (NotificationLibrary library = NotificationFactory.create(email, sms, push)) {
            NotificationResult actual = library.sendNotification(new NotificationSms(
                    "Este es un mensaje SMS", "+1 212-555-1234", NotificationType.SMS)).join();

            assertEquals(expected, actual);
            verify(sms).sendSmsNotificacion(argThat(s ->
                    "+1 212-555-1234".equals(s.getNumberCelPhone())));
            verifyNoInteractions(email, push);
        }
    }

    @Test
    void enviaPushPorLaApiPublica() {
        EmailRepository email = mock(EmailRepository.class);
        SmsRepository sms = mock(SmsRepository.class);
        PushRepository push = mock(PushRepository.class);
        NotificationResult expected = result(NotificationChannel.PUSH);
        when(push.sendPushNotificacion(any(Push.class))).thenReturn(expected);

        try (NotificationLibrary library = NotificationFactory.create(email, sms, push)) {
            NotificationResult actual = library.sendNotification(new NotificationPush(
                    NotificationType.pushNotification, "Este mensaje push es válido",
                    "device-123", "MiApp", "alertas", "Título válido")).join();

            assertEquals(expected, actual);
            verify(push).sendPushNotificacion(argThat(p ->
                    "device-123".equals(p.getDestination())
                    && "Título válido".equals(p.getTitle())));
            verifyNoInteractions(email, sms);
        }
    }

    @Test
    void notificaErrorDeValidacionYNoLlamaAlRepositorio() {
        EmailRepository email = mock(EmailRepository.class);
        SmsRepository sms = mock(SmsRepository.class);
        PushRepository push = mock(PushRepository.class);

        try (NotificationLibrary library = NotificationFactory.create(email, sms, push)) {
            NotificationEmail invalid = new NotificationEmail(
                    "corto", "no-es-email", "", NotificationType.email);

            assertThrows(NotificationBadRequestException.class,
                    () -> library.sendNotification(invalid));
            verifyNoInteractions(email, sms, push);
        }
    }
}
