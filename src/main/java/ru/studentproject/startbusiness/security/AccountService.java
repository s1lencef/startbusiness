package ru.studentproject.startbusiness.security;

import ru.studentproject.startbusiness.customer.Customer;

public interface AccountService {
    UserAccount createUserAccount(Customer customer);
}
