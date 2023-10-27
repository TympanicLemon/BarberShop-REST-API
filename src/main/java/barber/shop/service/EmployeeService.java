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

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeDao employeeDao;

  @Autowired
  private BarberShopDao barberShopDao;

  @Transactional
  public EmployeeData createEmployee(Long barberShopId, EmployeeData employeeData) {
    Optional<Employee> optEmp = employeeDao.findByEmail(employeeData.getEmail());
    if (optEmp.isPresent()) {
      throw new DuplicateKeyException("Employee with email " + employeeData.getEmail() + " already exists");
    }

    BarberShop barberShop = findBarberShopById(barberShopId);
    Employee employee = employeeData.toEmployee();
    barberShop.getEmployees().add(employee);
    employee.setBarberShop(barberShop);

    return new EmployeeData(employeeDao.save(employee));
  }

  @Transactional
  public EmployeeData updateEmployee(Long employeeId, EmployeeData employeeData) {
    Employee employee = findEmployeeById(employeeId);

    if (!employee.getEmail().equals(employeeData.getEmail())) {
      Optional<Employee> duplicateEmployee = employeeDao.findByEmail(employeeData.getEmail());
      if (duplicateEmployee.isPresent()) {
        throw new DuplicateKeyException("Employee with email " + employeeData.getEmail() + " already exists");
      }
    }

    setFieldsInEmployee(employee, employeeData);
    return new EmployeeData(employeeDao.save(employee));
  }

  private void setFieldsInEmployee(Employee employee, EmployeeData employeeData) {
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

  @Transactional(readOnly = true)
  public EmployeeData getEmployee(Long employeeId) {
    return new EmployeeData(findEmployeeById(employeeId));
  }

  @Transactional
  public void deleteEmployee(Long employeeId) {
    Employee employee = findEmployeeById(employeeId);
    BarberShop barberShop = employee.getBarberShop();

    barberShop.getEmployees().remove(employee);
    employeeDao.delete(employee);
  }
}