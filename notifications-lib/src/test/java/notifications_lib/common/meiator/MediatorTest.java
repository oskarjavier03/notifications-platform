package notifications_lib.common.meiator;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

class MediatorTest {
    record TestRequest(String value) implements Request<String> {}

    static class TestHandler implements RequestHandler<TestRequest, String> {
        @Override public CompletableFuture<String> handler(TestRequest request) {
            return CompletableFuture.completedFuture("procesado:" + request.value());
        }
        @Override public Class<TestRequest> getRequestType() { return TestRequest.class; }
    }

    @Test
    void dispatchEncuentraHandlerYDevuelveResultado() throws Exception {
        Mediator mediator = new Mediator(List.of(new TestHandler()));
        assertEquals("procesado:hola", mediator.dispatch(new TestRequest("hola")).get());
    }

    @Test
    void dispatchSinHandlerLanzaExcepcion() {
        Mediator mediator = new Mediator(List.of());
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> mediator.dispatch(new TestRequest("hola")));
        assertTrue(error.getMessage().contains(TestRequest.class.getName()));
    }
}
