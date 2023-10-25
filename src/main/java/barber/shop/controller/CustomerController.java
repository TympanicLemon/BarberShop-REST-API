package barber.shop.controller;

import barber.shop.controller.model.CustomerData;
import barber.shop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
  @Autowired
  private CustomerService customerService;

  @PostMapping("/{barberShopId}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public CustomerData createCustomer(@RequestBody CustomerData customerData, @PathVariable Long barberShopId) {
    log.info("Inserting customer {} into barber shop wit ID={}", customerData, barberShopId);
    return customerService.createCustomer(barberShopId, customerData);
  }

  @GetMapping("/{customerId}")
  public CustomerData getCustomer(@PathVariable Long customerId) {
    log.info("Retrieving customer with customer ID={}", customerId);
    return customerService.getCustomer(customerId);
  }

  @PutMapping("/{barberShopId}/{customerId}")
  public CustomerData updateCustomer(@RequestBody CustomerData customerData, @PathVariable Long customerId, @PathVariable Long barberShopId) {
    customerData.setCustomerId(customerId);
    log.info("Updating customer with ID={}", customerId);
    return customerService.updateCustomer(barberShopId, customerId, customerData);
  }

  @DeleteMapping("/{customerId}")
  public Map<String, String> deleteCustomer(@PathVariable Long customerId) {
    log.info("Deleting customer with ID={}", customerId);
    customerService.deleteCustomer(customerId);

    return Map.of("message", "Deletion of customer with ID=" + customerId + " was successful");
  }
}
