package pl.migibud.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    boolean isFraudulentCustomer(Integer customerId){
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerId(customerId)
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
