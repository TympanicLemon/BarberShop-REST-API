package barber.shop.controller;

import barber.shop.controller.model.EmployeeData;
import barber.shop.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  // Create
  @PostMapping("/{barberShopId}")
  public EmployeeData createEmployee(@PathVariable Long barberShopId,
                                     @RequestBody EmployeeData employeeData) {
    log.info("Creating employee for barbershop with ID={}, data={}", barberShopId, employeeData);
    return employeeService.createEmployee(barberShopId, employeeData);
  }

  // Update
  @PutMapping("/{employeeId}")
  public EmployeeData updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeData employeeData) {
    log.info("Updating employee {} with ID={}", employeeData, employeeId);
    return employeeService.updateEmployee(employeeId, employeeData);
  }

  // Get
  @GetMapping("/{employeeId}")
  public EmployeeData getEmployee(@PathVariable Long employeeId) {
    log.info("Fetching employee with ID={}", employeeId);
    return employeeService.getEmployee(employeeId);
  }

  // Get all
  @GetMapping("/{barberShopId}/employees")
  public List<EmployeeData> getAllEmployeesByShopId(@PathVariable Long barberShopId) {
    log.info("Fetching all employees in barber shop with ID={}", barberShopId);
    return employeeService.getAllEmployeesByShopId(barberShopId);
  }

  // Delete
  @DeleteMapping("/{employeeId}")
  public Map<String, String> deleteEmployee(@PathVariable Long employeeId) {
    log.info("Deleting employee with ID={}", employeeId);
    employeeService.deleteEmployee(employeeId);

    return Map.of("message", "Employee with ID=" + employeeId + " was successfully deleted.");
  }
}
