package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(UUID.randomUUID())
                .customerName("Stinky Pete")
                .build();
    }

    @Override
    public void update(UUID customerId, CustomerDto customerDto) {
        // @Todo update the customer
        log.debug("Updating Customer {}", customerId);
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .customerName("Norm")
                .build();
    }

    @Override
    public void deleteById(UUID customerId) {
        // @Todo delete the customer
        log.debug("Deleting Customer {}", customerId);
    }
}
