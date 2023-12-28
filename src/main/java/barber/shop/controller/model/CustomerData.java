package barber.shop.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
  private Long customerId;
  private String firstName;
  private String lastName;
  private String email;
}
