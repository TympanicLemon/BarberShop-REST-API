package barber.shop.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for the Customer Entity
 */
@Data
@NoArgsConstructor
public class CustomerData {
  private Long customerId;
  private String firstName;
  private String lastName;
  private String email;
}
