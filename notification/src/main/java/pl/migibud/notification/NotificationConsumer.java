package pl.migibud.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.migibud.clients.notification.NotificationRequest;

@Slf4j
@Component
@RequiredArgsConstructor
class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    void consumer(NotificationRequest notificationRequest){
        log.info("Consumed {} from queue",notificationRequest);
        notificationService.send(notificationRequest);
    }

}
