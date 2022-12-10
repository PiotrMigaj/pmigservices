package pl.migibud.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
}
