package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID customerId);

    void update(UUID customerId, CustomerDto customerDto);

    CustomerDto save(CustomerDto customerDto);

    void deleteById(UUID customerId);
}
