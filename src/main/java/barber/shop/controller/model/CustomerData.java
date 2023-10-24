package barber.shop.controller.model;

import barber.shop.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
  private Long customerId;
  private String firstName;
  private String lastName;
  private String email;

  public CustomerData(Customer customer) {
    this.customerId = customer.getCustomerId();
    this.firstName = customer.getFirstName();
    this.lastName = customer.getLastName();
    this.email = customer.getEmail();
  }

  public Customer toCustomer() {
    Customer customer = new Customer();

    customer.setCustomerId(customerId);
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setEmail(email);

    return customer;
  }
}
