package pl.migibud.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.migibud.clients.fraud.FraudCheckResponse;
import pl.migibud.clients.fraud.FraudClient;
import pl.migibud.clients.notification.NotificationClient;
import pl.migibud.clients.notification.NotificationRequest;

@Slf4j
@Service
@RequiredArgsConstructor
class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    Customer registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
        Customer save = customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());
        log.info("received data from fraud microservice: {}",fraudCheckResponse);
        if (fraudCheckResponse.getIsFraudster()){
            throw new IllegalStateException("fraudster");
        }

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to Amigoscode...",
                                customer.getFirstName())
                )
        );

        return save;
    }
}
