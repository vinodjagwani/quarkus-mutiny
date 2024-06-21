package com.mutiny.example.rest;

import com.mutiny.example.dto.Customer;
import com.mutiny.example.service.CustomerService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient
public class CustomerRestClient {

    @Inject
    CustomerService customerService;

    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }
}
