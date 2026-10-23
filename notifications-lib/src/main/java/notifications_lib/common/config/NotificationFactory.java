package notifications_lib.common.config;


import notifications_lib.common.Notification.NotificationActionImpl;
import notifications_lib.common.Notification.NotificationActions;
import notifications_lib.common.meiator.Mediator;
import notifications_lib.common.meiator.RequestHandler;
import notifications_lib.common.model.NotificationResult;
import notifications_lib.common.strategy.NotificationStrategy;
import notifications_lib.common.strategy.NotificationStrategyRegistry;
import notifications_lib.common.validation.ValidationConfiguration;
import notifications_lib.email.application.customerStrategy.EmailNotificationStrategyImpl;
import notifications_lib.email.application.send.SendNotificationEmailHandler;
import notifications_lib.email.application.send.SendNotificationEmailRequest;
import notifications_lib.email.domain.port.EmailRepository;
import notifications_lib.email.infrastructure.dto.NotificationEmail;
import notifications_lib.push.application.customerStrategy.PushNotificationStrategyImpl;
import notifications_lib.push.application.send.SendNotificationPushHandler;
import notifications_lib.push.application.send.SendNotificationPushRequest;
import notifications_lib.push.domain.port.PushRepository;
import notifications_lib.push.infrastructure.dto.NotificationPush;
import notifications_lib.sms.application.customerStrategy.SmsNotificationStrategyImpl;
import notifications_lib.sms.application.send.SendNotificationSmsHandler;
import notifications_lib.sms.application.send.SendNotificationSmsRequest;
import notifications_lib.sms.domain.port.SmsRepository;
import notifications_lib.sms.infrastructure.DTO.NotificationSms;

import java.util.List;


public final class NotificationFactory {

    private NotificationFactory() {
    }

    public static NotificationLibrary create(EmailRepository emailRepository, SmsRepository smsRepository,
            PushRepository pushRepository) {

        ValidationConfiguration validation = new ValidationConfiguration();

        try {

            NotificationStrategy<NotificationEmail> emailStrategy = new EmailNotificationStrategyImpl(validation);

            NotificationStrategy<NotificationSms> smsStrategy = new SmsNotificationStrategyImpl(validation);

            NotificationStrategy<NotificationPush> pushStrategy = new PushNotificationStrategyImpl(validation);

            NotificationStrategyRegistry strategyRegistry = new NotificationStrategyRegistry(
                            List.of(emailStrategy, smsStrategy, pushStrategy));

            RequestHandler<SendNotificationEmailRequest, NotificationResult> emailHandler =
                    new SendNotificationEmailHandler(emailRepository);

            RequestHandler<SendNotificationSmsRequest, NotificationResult> smsHandler =
                    new SendNotificationSmsHandler(smsRepository);

            RequestHandler<SendNotificationPushRequest, NotificationResult> pushHandler =
                    new SendNotificationPushHandler(pushRepository);

            Mediator mediator = new Mediator(List.of(emailHandler, smsHandler, pushHandler));

            NotificationActions actions = new NotificationActionImpl(strategyRegistry, mediator);

            return new NotificationLibrary(actions, validation);

        } catch (RuntimeException e) {
            validation.close();
            throw e;
        }
    }
}

