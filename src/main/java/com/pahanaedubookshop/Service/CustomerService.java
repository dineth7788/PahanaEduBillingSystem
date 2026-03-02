package com.pahanaedubookshop.Service;

import com.pahanaedubookshop.DAO.CustomerDAO;
import com.pahanaedubookshop.DAO.CustomerDAOImpl;
import com.pahanaedubookshop.Model.Customer;

import java.util.List;

public class CustomerService {

    private final CustomerDAO clientDao = new CustomerDAOImpl();

    public boolean registerClient(Customer client) {
        return clientDao.addCustomer(client);
    }

    public List<Customer> retrieveAllClients() {
        return clientDao.getAllCustomers();
    }

    public Customer fetchClientById(int clientId) {
        return clientDao.getCustomerById(clientId);
    }

    public Customer fetchClientByAccNo(String accNumber) {
        return clientDao.getCustomerByAccountNumber(accNumber);
    }

    public boolean modifyClient(Customer client) {
        return clientDao.updateCustomer(client);
    }

    public boolean dropClient(int clientId) {
        return clientDao.deleteCustomer(clientId);
    }
}