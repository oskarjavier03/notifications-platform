package notifications_lib.common.strategy;

import notifications_lib.common.model.enums.NotificationType;
import notifications_lib.email.infrastructure.dto.NotificationEmail;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NotificationStrategyRegistryTest {
    @Test
    void devuelveEstrategiaParaTipoRegistrado() {
        NotificationStrategy<NotificationEmail> emailStrategy = mock(NotificationStrategy.class);
        when(emailStrategy.getNotificationType()).thenReturn(NotificationEmail.class);
        NotificationStrategyRegistry registry = new NotificationStrategyRegistry(List.of(emailStrategy));

        NotificationEmail dto = new NotificationEmail(
                "Mensaje de prueba suficientemente largo", "test@example.com",
                "Asunto válido", NotificationType.email);

        assertSame(emailStrategy, registry.getStrategy(dto));
    }

    @Test
    void rechazaTipoNoRegistrado() {
        NotificationStrategyRegistry registry = new NotificationStrategyRegistry(List.of());
        NotificationEmail dto = new NotificationEmail(
                "Mensaje de prueba suficientemente largo", "test@example.com",
                "Asunto válido", NotificationType.email);

        assertThrows(IllegalArgumentException.class, () -> registry.getStrategy(dto));
    }

    @Test
    void rechazaEstrategiasDuplicadasParaElMismoTipo() {
        NotificationStrategy<NotificationEmail> first = mock(NotificationStrategy.class);
        NotificationStrategy<NotificationEmail> second = mock(NotificationStrategy.class);
        when(first.getNotificationType()).thenReturn(NotificationEmail.class);
        when(second.getNotificationType()).thenReturn(NotificationEmail.class);

        assertThrows(IllegalArgumentException.class,
                () -> new NotificationStrategyRegistry(List.of(first, second)));
    }
}
