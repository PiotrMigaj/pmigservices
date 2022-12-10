package pl.migibud.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.migibud.clients.fraud.FraudCheckResponse;

@Slf4j
@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
class FraudController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("/{customerId}")
    ResponseEntity<FraudCheckResponse> isFraudster(@PathVariable Integer customerId){
        log.info("got request for customer with id: {}",customerId);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new FraudCheckResponse(isFraudulentCustomer));
    }
}
