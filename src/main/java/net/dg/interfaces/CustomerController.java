package net.dg.interfaces;

import net.dg.domain.movie.Customer;
import net.dg.domain.movie.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/customer/retrives")
    public List<Customer> retrives() {
        return customerRepository.findAll();
    }
}
