package com.pahanaedubookshop.DAO;

import com.pahanaedubookshop.Model.Customer;
import java.util.List;

public interface CustomerDAO {
    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    Customer getCustomerByAccountNumber(String accountNumber);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
}