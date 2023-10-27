package barber.shop.controller;

import barber.shop.controller.model.EmployeeData;
import barber.shop.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  // Create an Employee
  @PostMapping("/{barberShopId}")
  public EmployeeData createEmployee(@PathVariable Long barberShopId,
                                     @RequestBody EmployeeData employeeData) {
    log.info("Creating employee for barbershop with ID={}, data={}", barberShopId, employeeData);
    return employeeService.createEmployee(barberShopId, employeeData);
  }

  // Update an Employee
  @PutMapping("/{employeeId}")
  public EmployeeData updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeData employeeData) {
    log.info("Updating employee {} with ID={}", employeeData, employeeId);
    return employeeService.updateEmployee(employeeId, employeeData);
  }

  // Get an Employee by ID within a Barber Shop
  @GetMapping("/{employeeId}")
  public EmployeeData getEmployee(@PathVariable Long employeeId) {
    log.info("Fetching employee with ID={}", employeeId);
    return employeeService.getEmployee(employeeId);
  }

  // Delete an Employee by ID from a specific Barber Shop
  @DeleteMapping("/{employeeId}")
  public Map<String, String> deleteEmployee(@PathVariable Long employeeId) {
    log.info("Deleting employee with ID={}", employeeId);
    employeeService.deleteEmployee(employeeId);

    return Map.of("message", "Employee with ID=" + employeeId + " was successfully deleted.");
  }
}
