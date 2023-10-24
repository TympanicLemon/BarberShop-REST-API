package barber.shop.controller.model;

import barber.shop.entity.BarberShop;
import barber.shop.entity.Customer;
import barber.shop.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class BarberShopData {
  private Long barberShopId;
  private String name;
  private String address;
  private String city;
  private String state;
  private String zip;
  private String phoneNumber;
  private Set<EmployeeData> employees = new HashSet<>();
  private Set<CustomerData> customers = new HashSet<>();

  public BarberShopData(BarberShop barberShop) {
    this.barberShopId = barberShop.getBarberShopId();
    this.name = barberShop.getName();
    this.address = barberShop.getAddress();
    this.city = barberShop.getCity();
    this.state = barberShop.getState();
    this.zip = barberShop.getZip();
    this.phoneNumber = barberShop.getPhoneNumber();

    for(Employee employee: barberShop.getEmployees()) {
      employees.add(new EmployeeData(employee));
    }

    for(Customer customer: barberShop.getCustomers()) {
      customers.add(new CustomerData(customer));
    }
  }

  public BarberShopData(Long barberShopId, String name, String address, String city,
                        String state, String zip, String phoneNumber) {
    this.barberShopId = barberShopId;
    this.name = name;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.phoneNumber = phoneNumber;
  }

  public BarberShop toBarberShop() {
    BarberShop barberShop = new BarberShop();

    barberShop.setBarberShopId(barberShopId);
    barberShop.setName(name);
    barberShop.setAddress(address);
    barberShop.setCity(city);
    barberShop.setState(state);
    barberShop.setZip(zip);
    barberShop.setPhoneNumber(phoneNumber);

    for(CustomerData customerData: customers) {
      barberShop.getCustomers().add(customerData.toCustomer());
    }

    for(EmployeeData employeeData: employees) {
      barberShop.getEmployees().add(employeeData.toEmployee());
    }

    return barberShop;
  }
}
