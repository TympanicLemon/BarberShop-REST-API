package barber.shop.service;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.controller.model.EmployeeData;
import barber.shop.entity.BarberShop;
import barber.shop.entity.Customer;
import barber.shop.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class is responsible for converting entities into DTOs (Data Transfer Objects)
 * for the BarberShop application.
 */
@Service
public class DataFactory {

    public EmployeeData createEmployeeData(Employee employee) {
        if(Objects.isNull(employee))
            throw new IllegalArgumentException("Employee object you are trying to convert is null.");

        EmployeeData employeeData = new EmployeeData();

        employeeData.setEmployeeId(employee.getEmployeeId());
        employeeData.setEmail(employee.getEmail());
        employeeData.setFirstName(employee.getFirstName());
        employeeData.setLastName(employee.getLastName());
        employeeData.setPhoneNumber(employee.getPhoneNumber());

        return employeeData;
    }

    public CustomerData createCustomerData(Customer customer) {
        if(Objects.isNull(customer))
            throw new IllegalArgumentException("Customer object you are trying to convert is null.");

        CustomerData customerData = new CustomerData();

        customerData.setCustomerId(customer.getCustomerId());
        customerData.setEmail(customer.getEmail());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());

        return customerData;
    }

    public BarberShopData createBarberShopData(BarberShop barberShop) {
        if(Objects.isNull(barberShop))
            throw new IllegalArgumentException("BarberShop object you are trying to convert is null.");

        BarberShopData barberShopData = new BarberShopData();

        barberShopData.setBarberShopId(barberShop.getBarberShopId());
        barberShopData.setName(barberShop.getName());
        barberShopData.setAddress(barberShop.getAddress());
        barberShopData.setCity(barberShop.getCity());
        barberShopData.setState(barberShop.getState());
        barberShopData.setZip(barberShop.getZip());
        barberShopData.setPhoneNumber(barberShop.getPhoneNumber());

        Set<EmployeeData> employeeDataSet = barberShop.getEmployees().stream()
                .map(this::createEmployeeData).collect(Collectors.toSet());
        barberShopData.setEmployees(employeeDataSet);

        Set<CustomerData> customerDataSet = barberShop.getCustomers().stream()
                .map(this::createCustomerData).collect(Collectors.toSet());
        barberShopData.setCustomers(customerDataSet);

        return barberShopData;
    }
}