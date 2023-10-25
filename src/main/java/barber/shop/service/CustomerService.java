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

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  private CustomerDao customerDao;

  @Autowired
  private BarberShopDao barberShopDao;

  @Transactional
  public CustomerData createCustomer(Long barberShopId, CustomerData customerData) {
    Optional<Customer> existingCustomer = customerDao.findByEmail(customerData.getEmail());
    if(existingCustomer.isPresent()) {
      throw new DuplicateKeyException("Customer with email " + customerData.getEmail() + " already exists");
    }

    BarberShop barberShop = findBarberShopById(barberShopId);
    Customer newCustomer = customerData.toCustomer();

    barberShop.getCustomers().add(newCustomer);

    return new CustomerData(customerDao.save(newCustomer));
  }

  @Transactional
  public CustomerData updateCustomer(Long barberShopId, Long customerId, CustomerData customerData) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    Customer customer = findCustomerById(customerId);

    if(!customer.getEmail().equals(customerData.getEmail())) {
      Optional<Customer> duplicateCustomer = customerDao.findByEmail(customerData.getEmail());
      if(duplicateCustomer.isPresent()) {
        throw new DuplicateKeyException("Customer with email " + customerData.getEmail() + " already exists");
      }
    }

    setFieldsInCustomer(customer, customerData);

    addCustomerToBarberShop(barberShop, customer);

    return new CustomerData(customerDao.save(customer));
  }

  private void addCustomerToBarberShop(BarberShop barberShop, Customer customer) {
    boolean isAssociated = false;
    for (BarberShop associatedShop : customer.getBarberShops()) {
      if (associatedShop.getBarberShopId().equals(barberShop.getBarberShopId())) {
        isAssociated = true;
        break;
      }
    }

    if (!isAssociated) {
      customer.getBarberShops().add(barberShop);
    }
  }

  private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
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

  @Transactional(readOnly = true)
  public CustomerData getCustomer(Long customerId) {
    Customer customer = findCustomerById(customerId);
    return new CustomerData(customer);
  }

  @Transactional
  public void deleteCustomer(Long customerId) {
    Customer customer = findCustomerById(customerId);

    // Clear associations on both sides
    for (BarberShop barberShop : customer.getBarberShops()) {
      barberShop.getCustomers().remove(customer);
    }
    customer.getBarberShops().clear();

    customerDao.delete(customer);
  }
}


