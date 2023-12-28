package barber.shop.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeData {
  private Long employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
}

