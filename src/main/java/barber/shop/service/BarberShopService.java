package barber.shop.service;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.controller.model.EmployeeData;
import barber.shop.dao.BarberShopDao;
import barber.shop.entity.BarberShop;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Transactional
  public BarberShopData saveBarberShop(BarberShopData barberShopData) {
    Long barberShopId = barberShopData.getBarberShopId();
    BarberShop barberShop = findOrCreateBarberShop(barberShopId);

    updateBarberShopDataFields(barberShop, barberShopData);

    return dataFactory.createBarberShopData(barberShop);
  }

  private void updateBarberShopDataFields(BarberShop barberShop, BarberShopData barberShopData) {
    barberShop.setName(barberShopData.getName());
    barberShop.setAddress(barberShopData.getAddress());
    barberShop.setCity(barberShopData.getCity());
    barberShop.setState(barberShopData.getState());
    barberShop.setZip(barberShopData.getZip());
    barberShop.setPhoneNumber(barberShopData.getPhoneNumber());
  }

  private BarberShop findOrCreateBarberShop(Long barberShopId) {
    BarberShop barberShop;

    if (Objects.isNull(barberShopId))
      barberShop = new BarberShop();
    else
      barberShop = findBarberShopById(barberShopId);

    return barberShop;
  }

  private BarberShop findBarberShopById(Long barberShopId) {
    return barberShopDao.findById(barberShopId).orElseThrow(() -> new NoSuchElementException(
            "Barbershop with ID=" + barberShopId + " was not found"));
  }

  @Transactional(readOnly = true)
  public List<BarberShopData> getAllBarberShops() {
    return barberShopDao.findAll().stream().map(dataFactory::createBarberShopData).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public BarberShopData getBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return dataFactory.createBarberShopData(barberShop);
  }

  @Transactional
  public void deleteBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    barberShopDao.delete(barberShop);
  }

  @Transactional
  public List<CustomerData> getAllCustomersByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return barberShop.getCustomers().stream().map(dataFactory::createCustomerData).collect(Collectors.toList());
  }

  @Transactional
  public List<EmployeeData> getAllEmployeesByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    return barberShop.getEmployees().stream().map(dataFactory::createEmployeeData).collect(Collectors.toList());
  }
}