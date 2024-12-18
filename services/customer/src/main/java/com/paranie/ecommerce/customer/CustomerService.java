package com.paranie.ecommerce.customer;

import com.paranie.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private  final CustomerRepository repository;
    private final CustomerMapper mapper;
    public Integer createCustomer(@Valid CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        Customer customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot update the customer :: No customer with the provided ID::" + request.id()
                ));
        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        // address
        if(request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::toCustomerResponse)
                .toList();
    }

    public Boolean existsByIdById(Integer id) {
        return repository.findById(id).isPresent();
    }

    public CustomerResponse findById(Integer customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot find the customer :: No customer with the provided ID::" + customerId
                ));
        return mapper.toCustomerResponse(customer);
    }

    public void deleteCustomer(Integer customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot delete the customer :: No customer with the provided ID::" + customerId
                ));
        repository.delete(customer);
    }
}
