package com.mutiny.example.service;

import com.mutiny.example.dto.Customer;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Arrays;

@ApplicationScoped
public class CustomerService {

    // mutiny api -- https://smallrye.io/smallrye-mutiny/latest/tutorials/getting-mutiny/

    public Multi<Customer> findAllCustomers() {
        return Multi.createFrom()
                .iterable(Arrays.asList(buildCustomer(), buildCustomer()))
                .onFailure()
                .retry()
                .withBackOff(Duration.ofMillis(100), Duration.ofSeconds(1))
                .withJitter(0.2)
                .atMost(2);
    }

    private Customer buildCustomer() {
        final Customer customer = new Customer();
        customer.setAddress("ABC");
        customer.setName("Vinod");
        return customer;
    }

}
