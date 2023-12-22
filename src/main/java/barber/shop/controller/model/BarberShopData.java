package barber.shop.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO class for the BarberShop entity.
 */
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
}
