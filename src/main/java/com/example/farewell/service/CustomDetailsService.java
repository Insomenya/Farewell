
package com.example.farewell.service;

import com.example.farewell.domain.*;
import com.example.farewell.repository.CustomerRepo;
import com.example.farewell.repository.OperatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private OperatorRepo operatorRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Operator operator = operatorRepo.findByLogin(username);
        if (operator != null) {
            return new CustomUserDetails(operator.getLogin(), operator.getPassword(), operator.getRole(), operator);
        } else {
            Customer customer = customerRepo.findByPhoneNum(username);
            if (customer != null) {
                return new CustomUserDetails(customer.getPhoneNum(), customer.getPassword(), customer.getRole(), customer);
            }
        }
        throw new UsernameNotFoundException("Пользователь '" + username + "' не найден.");
    }

    public boolean addCustomer(CustomUserDetails user, String fullName) {
        Customer customerFromDB = customerRepo.findByPhoneNum(user.getUsername());
        if (customerFromDB != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Customer newCustomer = new Customer();
        newCustomer.setPhoneNum(user.getUsername());
        newCustomer.setPassword(user.getPassword());
        newCustomer.setPersonalDiscount(0.0d);
        newCustomer.setFullName(fullName);
        customerRepo.save(newCustomer);
        return true;
    }

    public boolean addOperator(CustomUserDetails user, String fullName) {
        Operator operatorFromDB = operatorRepo.findByLogin(user.getUsername());
        if (operatorFromDB != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Operator newOperator = new Operator();
        newOperator.setLogin(user.getUsername());
        newOperator.setPassword(user.getPassword());
        newOperator.setFullName(fullName);
        operatorRepo.save(newOperator);
        return true;
    }
}

