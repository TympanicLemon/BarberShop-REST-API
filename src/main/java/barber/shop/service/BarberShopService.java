package barber.shop.service;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.dao.BarberShopDao;
import barber.shop.dao.CustomerDao;
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

  // Save or updatea a barber shop
  @Transactional
  public BarberShopData saveBarberShop(BarberShopData barberShopData) {
    Long barberShopId = barberShopData.getBarberShopId();
    BarberShop barberShop = findOrCreateBarberShop(barberShopId);
    setFieldsInBarberShop(barberShop, barberShopData);

    return new BarberShopData(barberShopDao.save(barberShop));
  }

  private void setFieldsInBarberShop(BarberShop barberShop, BarberShopData barberShopData) {
    barberShop.setName(barberShopData.getName());
    barberShop.setAddress(barberShopData.getAddress());
    barberShop.setCity(barberShopData.getCity());
    barberShop.setState(barberShopData.getState());
    barberShop.setZip(barberShopData.getZip());
    barberShop.setPhoneNumber(barberShopData.getPhoneNumber());
  }

  private BarberShop findOrCreateBarberShop(Long barberShopId) {
    BarberShop barberShop;

    if (Objects.isNull(barberShopId)) {
      barberShop = new BarberShop();
    } else {
      barberShop = findBarberShopById(barberShopId);
    }

    return barberShop;
  }

  private BarberShop findBarberShopById(Long barberShopId) {
    return barberShopDao.findById(barberShopId).orElseThrow(() -> new NoSuchElementException(
      "Barbershop with ID=" + barberShopId + " was not found"));
  }

  // Get all barber shops
  @Transactional(readOnly = true)
  public List<BarberShopData> retrieveAllBarberShops() {
    return barberShopDao.findAll().stream().map(BarberShopData::new).toList();
  }

  // Get one barber shop
  @Transactional(readOnly = true)
  public BarberShopData retrieveBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);

    return new BarberShopData(barberShop);
  }

  // Delete a barber shop
  @Transactional
  public void deleteBarberShopById(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);
    barberShopDao.delete(barberShop);
  }

  // Get all customers from one shop
  @Transactional
  public List<CustomerData> retrieveCustomersByShopId(Long barberShopId) {
    BarberShop barberShop = findBarberShopById(barberShopId);

    return barberShop.getCustomers().stream().map(CustomerData::new).collect(Collectors.toList());
  }
}
