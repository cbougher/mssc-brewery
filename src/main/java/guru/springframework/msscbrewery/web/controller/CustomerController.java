package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"/{customerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto getCustomer(@PathVariable UUID customerId){

        return customerService.getCustomerById(customerId);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        customerService.update(customerId, customerDto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> newBeer(@RequestBody CustomerDto customerDto) {
        CustomerDto savedCustomerDto = customerService.save(customerDto);

        String url = "/api/v1/beer/{beerId}";
        Map<String, UUID> params = new HashMap<String, UUID>();
        params.put("beerId", savedCustomerDto.getId());
        URI location = UriComponentsBuilder.fromUriString(url)
                .buildAndExpand(params)
                .toUri();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<CustomerDto>(savedCustomerDto, responseHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID customerId) {
        customerService.deleteById(customerId);
    }
}
