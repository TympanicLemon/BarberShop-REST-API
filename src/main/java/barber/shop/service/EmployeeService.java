package barber.shop.service;

import barber.shop.controller.model.EmployeeData;
import barber.shop.dao.EmployeeDao;
import barber.shop.dao.BarberShopDao;
import barber.shop.entity.BarberShop;
import barber.shop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
  @Autowired
  private EmployeeDao employeeDao;

  @Autowired
  private BarberShopDao barberShopDao;

  @Autowired
  private DataFactory dataFactory;

  // Create
  @Transactional
  public EmployeeData createEmployee(Long barberShopId, EmployeeData employeeData) {
    employeeDao.findByEmail(employeeData.getEmail()).ifPresent(c -> {
      throw new DuplicateKeyException("Employee with email " + employeeData.getEmail() + " already exists");
    });

    BarberShop barberShop = findBarberShopById(barberShopId);
    Employee employee = dataFactory.converToEmployee(employeeData);
    barberShop.getEmployees().add(employee);
    employee.setBarberShop(barberShop);

    return dataFactory.convertToEmployeeData(employeeDao.save(employee));
  }

  // Update
  @Transactional
  public EmployeeData updateEmployee(Long employeeId, EmployeeData employeeData) {
    Employee employee = findEmployeeById(employeeId);

    if (!employee.getEmail().equals(employeeData.getEmail())) {
      Optional<Employee> duplicateEmployee = employeeDao.findByEmail(employeeData.getEmail());
      if (duplicateEmployee.isPresent()) {
        throw new DuplicateKeyException("Employee with email " + employeeData.getEmail() + " already exists");
      }
    }

    updateEmployeeDataFields(employee, employeeData);
    return dataFactory.convertToEmployeeData(employee);
  }

  private void updateEmployeeDataFields(Employee employee, EmployeeData employeeData) {
    employee.setFirstName(employeeData.getFirstName());
    employee.setLastName(employeeData.getLastName());
    employee.setEmail(employeeData.getEmail());
    employee.setPhoneNumber(employeeData.getPhoneNumber());
  }

  private Employee findEmployeeById(Long employeeId) {
    return employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException(
      "Employee with ID=" + employeeId + " was not found"));
  }

  private BarberShop findBarberShopById(Long barberShopId) {
    return barberShopDao.findById(barberShopId).orElseThrow(() -> new NoSuchElementException(
      "Barbershop with ID=" + barberShopId + " was not found"));
  }

  // Get
  @Transactional(readOnly = true)
  public EmployeeData getEmployee(Long employeeId) {
    Employee employee = findEmployeeById(employeeId);
    return dataFactory.convertToEmployeeData(employee);
  }

  // Delete
  @Transactional
  public void deleteEmployee(Long employeeId) {
    Employee employee = findEmployeeById(employeeId);
    BarberShop barberShop = employee.getBarberShop();

    barberShop.getEmployees().remove(employee);
    employeeDao.delete(employee);
  }

  public Set<EmployeeData> getAllEmployeesByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return barberShop.getEmployees().stream().map(dataFactory::convertToEmployeeData).collect(Collectors.toSet());
  }
}