package barber.shop.service;

import barber.shop.controller.model.CustomerData;
import barber.shop.dao.BarberShopDao;
import barber.shop.dao.CustomerDao;
import barber.shop.entity.BarberShop;
import barber.shop.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private BarberShopDao barberShopDao;

    @Autowired
    private DataFactory dataFactory;

    // Create
    @Transactional
    public CustomerData createCustomer(Long barberShopId, CustomerData customerData) {
        customerDao.findByEmail(customerData.getEmail()).ifPresent(c -> {
                    throw new DuplicateKeyException("Customer with email " + customerData.getEmail() + " already exists.");
        });

        BarberShop barberShop = findBarberShopById(barberShopId);
        Customer customer = dataFactory.convertToCustomer(customerData);
        barberShop.getCustomers().add(customer);
        customer.getBarberShops().add(barberShop);

        return dataFactory.convertToCustomerData(customerDao.save(customer));
    }

    // Update
    @Transactional
    public CustomerData updateCustomer(Long customerId, CustomerData customerData) {
        Customer customer = findCustomerById(customerId);

        if (!customer.getEmail().equals(customerData.getEmail())) {
            Optional<Customer> optCustomer = customerDao.findByEmail(customerData.getEmail());
            if (optCustomer.isPresent()) {
                throw new DuplicateKeyException("Customer with email " + customerData.getEmail() + " already exists");
            }
        }

        updateCustomerDataFields(customer, customerData);
        dataFactory.convertToCustomer(customerData);

        return dataFactory.convertToCustomerData(customerDao.save(customer));
    }

    private void updateCustomerDataFields(Customer customer, CustomerData customerData) {
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
    }

    private Customer findCustomerById(Long customerId) {
        return customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException(
                "Customer with ID=" + customerId + " was not found"));
    }

    private BarberShop findBarberShopById(Long barberShopId) {
        return barberShopDao.findById(barberShopId).orElseThrow(() -> new NoSuchElementException(
                "Barbershop with ID=" + barberShopId + " was not found"));
    }

    // Get
    @Transactional(readOnly = true)
    public CustomerData getCustomer(Long customerId) {
        Customer customer = findCustomerById(customerId);
        return dataFactory.convertToCustomerData(customer);
    }

    // Get all
    @Transactional
    public Set<CustomerData> getAllCustomersByShopId(Long barberShopId) {
        BarberShop barberShop = findBarberShopById(barberShopId);

        return barberShop.getCustomers().stream().map(dataFactory::convertToCustomerData).collect(Collectors.toSet());
    }

    // Delete
    @Transactional
    public void deleteCustomer(Long customerId) {
        Customer customer = findCustomerById(customerId);

        for (BarberShop barberShop : customer.getBarberShops()) {
            barberShop.getCustomers().remove(customer);
        }
        customer.getBarberShops().clear();

        customerDao.delete(customer);
    }
}


