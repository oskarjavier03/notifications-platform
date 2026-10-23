# Notifications Platform

Librería Java para gestionar el envío de notificaciones desde distintas aplicaciones, centralizando la lógica de notificación y permitiendo extender el sistema para soportar diferentes canales de comunicación.

## 1. Arquitectura del proyecto

El proyecto está organizado como un proyecto Maven multimódulo:

```text
notifications-platform/
├── pom.xml
├── notifications-lib/
├── notifications-api/
└── notifications-client/
```

### Módulos

- **`notifications-lib`**: contiene la librería reutilizable de notificaciones, sus estrategias de envío, validaciones y mecanismos de despacho.
- **`notifications-api`**: módulo destinado a exponer las funcionalidades de la plataforma mediante una API.
- **`notifications-client`**: módulo cliente que permite consumir las funcionalidades expuestas por la plataforma.

La librería busca desacoplar las aplicaciones consumidoras de los detalles internos de procesamiento de las notificaciones.

## 2. Requisitos

Para utilizar el proyecto se requiere:

- Java 21.
- Apache Maven.
- Una aplicación Java compatible con las dependencias de la librería.
- La configuración correspondiente al canal de notificación que se quiera utilizar.

## 3. Compilar e instalar la librería

Clonar el repositorio:

```bash
git clone https://github.com/oskarjavier03/notifications-platform.git
cd notifications-platform
```

Compilar todos los módulos e instalar sus artefactos en el repositorio local de Maven:

```bash
mvn clean install
```

Este comando compila los módulos, ejecuta las pruebas configuradas e instala los artefactos para que puedan ser utilizados por otras aplicaciones locales.

Para compilar únicamente la librería y sus dependencias dentro del proyecto:

```bash
mvn -pl notifications-lib -am clean install
```

## 4. Incorporar la librería en otra aplicación

Si la aplicación consumidora utiliza Maven, agregar la siguiente dependencia a su `pom.xml`:

```xml
<dependency>
    <groupId>com.pinapp</groupId>
    <artifactId>notifications-lib</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

La versión debe coincidir con la declarada en el `pom.xml` de `notifications-lib`.

Para una aplicación independiente, primero es necesario que el artefacto esté instalado en el repositorio local de Maven o publicado en un repositorio Maven accesible por la aplicación.

## 5. Enviar una notificación por email

La librería proporciona una API común para solicitar el envío de notificaciones.

Una aplicación consumidora puede utilizar `NotificationLibrary` para enviar una notificación sin tener que invocar directamente las estrategias internas ni los handlers.

### Ejemplo de uso

```java
import java.util.concurrent.CompletableFuture;

import notifications_lib.NotificationLibrary;
import notifications_lib.NotificationEmail;
import notifications_lib.NotificationResult;
import notifications_lib.NotificationType;

public class EmailNotificationExample {

    private final NotificationLibrary notificationLibrary;

    public EmailNotificationExample(
            NotificationLibrary notificationLibrary) {
        this.notificationLibrary = notificationLibrary;
    }

    public CompletableFuture<NotificationResult> sendEmail() {

        NotificationEmail notification = new NotificationEmail(
                "Este es el contenido del mensaje",
                "destinatario@example.com",
                "Asunto de la notificación",
                NotificationType.email
        );

        return notificationLibrary
                .sendNotification(notification);
    }
}
```

**Nota:** los paquetes de los imports deben ajustarse a los declarados realmente por las clases públicas de `notifications-lib`. La instancia de `NotificationLibrary` debe inicializarse previamente con la configuración y las implementaciones necesarias para el canal seleccionado.

### Procesar el resultado

El envío devuelve un `CompletableFuture<NotificationResult>`, por lo que el resultado puede procesarse de forma asíncrona.

Por ejemplo:

```java
notificationLibrary
    .sendNotification(notification)
    .thenAccept(result -> {
        if (result.getStatus() == NotificationStatus.FAILED
                || result.getError() != null) {
            // Registrar el fallo y gestionar el error.
            return;
        }

        // Registrar el resultado del envío.
    });
```

Si se necesita esperar el resultado de forma síncrona, se puede utilizar `.join()`:

```java
NotificationResult result = notificationLibrary
        .sendNotification(notification)
        .join();
```

El uso de `join()` bloquea el hilo que lo ejecuta hasta que se complete la operación. Para aplicaciones que gestionan muchas notificaciones, se recomienda preferir el procesamiento asíncrono cuando sea apropiado.

## 6. Inicialización de la librería

La aplicación consumidora debe disponer de una instancia configurada de `NotificationLibrary`.

La inicialización debe proporcionar las dependencias necesarias para los canales que se vayan a utilizar, como los repositorios o adaptadores responsables del envío.

En una aplicación Java sin Spring, esta configuración puede realizarse durante el arranque de la aplicación. En una aplicación Spring, la instancia puede exponerse como un bean e inyectarse en los servicios consumidores.

La aplicación que utiliza la librería no debería necesitar conocer el funcionamiento interno del Mediator, el registro de estrategias o los handlers de cada tipo de notificación.

## 7. Extensibilidad

La arquitectura está orientada a permitir incorporar nuevos canales de notificación sin tener que reescribir la lógica común de consumo.

Entre los canales contemplados en el diseño se encuentran:

- Email.
- SMS.
- Push.
- Slack.

La disponibilidad efectiva de cada canal depende de que su estrategia, sus validaciones, sus handlers y sus dependencias estén implementados y registrados en la configuración de la librería.

Para incorporar un canal nuevo, es necesario implementar su lógica específica y registrarla en el mecanismo de configuración correspondiente.

## 8. Patrones de diseño

La implementación utiliza patrones de diseño para separar responsabilidades:

- **Strategy:** encapsula la lógica específica de cada canal de notificación.
- **Mediator:** desacopla la solicitud de una operación de su handler correspondiente.
- **Factory:** centraliza la creación y configuración de los componentes de la librería.

Esta organización facilita la incorporación de nuevos canales, el mantenimiento del código y la realización de pruebas unitarias.

## 9. Pruebas

Ejecutar las pruebas de todos los módulos:

```bash
mvn clean verify
```

Para ejecutar las pruebas de la librería y sus dependencias:

```bash
mvn -pl notifications-lib -am clean verify
```

Se recomienda cubrir, mediante pruebas unitarias, las validaciones de las notificaciones, la selección de estrategias, el despacho de solicitudes y el tratamiento de resultados y errores.

## 10. Contribuciones

Para proponer cambios:

1. Crear una rama a partir de `main`.
2. Implementar los cambios correspondientes.
3. Ejecutar las pruebas.
4. Enviar un pull request con una descripción de los cambios.

## 11. Repositorio

Código fuente: https://github.com/oskarjavier03/notifications-platform
