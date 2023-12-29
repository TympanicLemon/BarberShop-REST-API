package barber.shop.service;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.controller.model.EmployeeData;
import barber.shop.dao.BarberShopDao;
import barber.shop.entity.BarberShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class BarberShopService {
  @Autowired
  private BarberShopDao barberShopDao;

  @Autowired
  private DataFactory dataFactory;

  // Create
  @Transactional
  public BarberShopData createBarberShop(BarberShopData barberShopData) {
    Long barberShopId = barberShopData.getBarberShopId();
    if(!Objects.isNull(barberShopId))
        throw new DuplicateKeyException("Barbershop with ID=" + barberShopId + " already exists");

    BarberShop barberShop = dataFactory.convertToBarberShop(barberShopData);
    return dataFactory.convertToBarberShopData(barberShopDao.save(barberShop));
  }

  // Update
  @Transactional
  public BarberShopData updateBarberShop(Long barberShopId, BarberShopData barberShopData) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    updateBarberShopDataFields(barberShop, barberShopData);
    return dataFactory.convertToBarberShopData(barberShopDao.save(barberShop));
  }

  private void updateBarberShopDataFields(BarberShop barberShop, BarberShopData barberShopData) {
    barberShop.setName(barberShopData.getName());
    barberShop.setAddress(barberShopData.getAddress());
    barberShop.setCity(barberShopData.getCity());
    barberShop.setState(barberShopData.getState());
    barberShop.setZip(barberShopData.getZip());
    barberShop.setPhoneNumber(barberShopData.getPhoneNumber());
  }


  private BarberShop findBarberShopById(Long barberShopId) {
    return barberShopDao.findById(barberShopId).orElseThrow(
            () -> new NoSuchElementException("Barbershop with ID=" + barberShopId + " was not found"));
  }

  // Get
  @Transactional(readOnly = true)
  public BarberShopData getBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return dataFactory.convertToBarberShopData(barberShop);
  }

  // Get all
  @Transactional(readOnly = true)
  public List<BarberShopData> getAllBarberShops() {
    return barberShopDao.findAll().stream().map(dataFactory::convertToBarberShopData).collect(Collectors.toList());
  }

  // Get all employees
  @Transactional
  public List<EmployeeData> getAllEmployeesByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return barberShop.getEmployees().stream().map(dataFactory::convertToEmployeeData).collect(Collectors.toList());
  }

  // Get all customers
  @Transactional
  public List<CustomerData> getAllCustomersByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return barberShop.getCustomers().stream().map(dataFactory::convertToCustomerData).collect(Collectors.toList());
  }

  // Delete
  @Transactional
  public void deleteBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    barberShopDao.delete(barberShop);
  }
}