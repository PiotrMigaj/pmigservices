package pl.migibud.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.migibud.clients.notification.NotificationRequest;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
class NotificationService {

    private final NotificationRepository notificationRepository;

    void send(NotificationRequest notificationRequest){
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.getToCustomerId())
                        .toCustomerEmail(notificationRequest.getToCustomerName())
                        .sender("PMIG")
                        .message(notificationRequest.getMessage())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }


}
