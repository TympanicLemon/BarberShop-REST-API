package barber.shop.service;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.controller.model.EmployeeData;
import barber.shop.entity.BarberShop;
import barber.shop.entity.Customer;
import barber.shop.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DataFactory {
    public BarberShopData convertToBarberShopData(BarberShop barberShop) {
        if (Objects.isNull(barberShop))
            throw new IllegalArgumentException("BarberShop object you are trying to convert is null.");

        BarberShopData barberShopData = new BarberShopData();

        barberShopData.setBarberShopId(barberShop.getBarberShopId());
        barberShopData.setName(barberShop.getName());
        barberShopData.setAddress(barberShop.getAddress());
        barberShopData.setCity(barberShop.getCity());
        barberShopData.setState(barberShop.getState());
        barberShopData.setZip(barberShop.getZip());
        barberShopData.setPhoneNumber(barberShop.getPhoneNumber());

        return barberShopData;
    }

    public EmployeeData convertToEmployeeData(Employee employee) {
        if (Objects.isNull(employee))
            throw new IllegalArgumentException("Employee object you are trying to convert is null.");

        EmployeeData employeeData = new EmployeeData();

        employeeData.setEmployeeId(employee.getEmployeeId());
        employeeData.setEmail(employee.getEmail());
        employeeData.setFirstName(employee.getFirstName());
        employeeData.setLastName(employee.getLastName());
        employeeData.setPhoneNumber(employee.getPhoneNumber());

        return employeeData;
    }

    public CustomerData convertToCustomerData(Customer customer) {
        if (Objects.isNull(customer))
            throw new IllegalArgumentException("Customer object you are trying to convert is null.");

        CustomerData customerData = new CustomerData();

        customerData.setCustomerId(customer.getCustomerId());
        customerData.setEmail(customer.getEmail());
        customerData.setFirstName(customer.getFirstName());
        customerData.setLastName(customer.getLastName());

        return customerData;
    }


    public BarberShop convertToBarberShop(BarberShopData barberShopData) {
        if (Objects.isNull(barberShopData))
            throw new IllegalArgumentException("BarberShop object you are trying to convert is null.");

        BarberShop barberShop = new BarberShop();

        barberShop.setBarberShopId(barberShopData.getBarberShopId());
        barberShop.setName(barberShopData.getName());
        barberShop.setAddress(barberShop.getAddress());
        barberShop.setCity(barberShopData.getCity());
        barberShop.setState(barberShopData.getState());
        barberShop.setZip(barberShopData.getZip());
        barberShop.setPhoneNumber(barberShopData.getPhoneNumber());

        return barberShop;
    }

    public Employee converToEmployee(EmployeeData employeeData) {
        if (Objects.isNull(employeeData))
            throw new IllegalArgumentException("EmployeeData object you are trying to convert is null.");

        Employee employee = new Employee();

        employee.setEmployeeId(employeeData.getEmployeeId());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employee.getEmail());

        return employee;
    }

    public Customer convertToCustomer(CustomerData customerData) {
        if (Objects.isNull(customerData))
            throw new IllegalArgumentException("CustomerData object you are trying to convert is null.");

        Customer customer = new Customer();

        customer.setCustomerId(customerData.getCustomerId());
        customer.setEmail(customerData.getEmail());
        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());

        return customer;
    }
}