package barber.shop.controller.model;

import barber.shop.entity.Employee;
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

  public EmployeeData(Employee employee) {
    this.employeeId = employee.getEmployeeId();
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.email = employee.getEmail();
    this.phoneNumber = employee.getPhoneNumber();
  }

  public Employee toEmployee() {
    Employee employee = new Employee();

    employee.setEmployeeId(employeeId);
    employee.setEmail(email);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setEmail(email);
    employee.setPhoneNumber(phoneNumber);

    return employee;
  }
}

