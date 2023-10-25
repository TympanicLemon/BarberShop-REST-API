package barber.shop.controller;

import barber.shop.controller.model.BarberShopData;
import barber.shop.controller.model.CustomerData;
import barber.shop.service.BarberShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/barber_shop")
@Slf4j
public class BarberShopController {
  @Autowired
  BarberShopService barberShopService;

  // Create
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BarberShopData insertBarberShop(@RequestBody BarberShopData barberShopData) {
    log.info("Creating barber shop {}", barberShopData);
    return barberShopService.saveBarberShop(barberShopData);
  }

  // Read all
  @GetMapping
  public List<BarberShopData> retrieveAllBarberShops() {
    log.info("Retrieving all available barber shops.");
    return barberShopService.retrieveAllBarberShops();
  }

  // Read
  @GetMapping("/{barberShopId}")
  public BarberShopData retrieveBarberShopById(@PathVariable Long barberShopId) {
    log.info("Retrieving barbershop with ID={}", barberShopId);
    return barberShopService.retrieveBarberShopById(barberShopId);
  }

  // Get all customers for a particular store
  @GetMapping("/{barberShopId}/customers")
  public List<CustomerData> retrieveCustomersByShopId(@PathVariable Long barberShopId) {
    log.info("Retrieving all customers in barber shop with ID={}", barberShopId);
    return barberShopService.retrieveCustomersByShopId(barberShopId);
  }

  // Update
  @PutMapping("/{barberShopId}")
  public BarberShopData updateBarberShop(@PathVariable Long barberShopId, @RequestBody BarberShopData barberShopData) {
    barberShopData.setBarberShopId(barberShopId);
    log.info("Updating barber shop with ID={}", barberShopId);
    return barberShopService.saveBarberShop(barberShopData);
  }

  // Delete
  @DeleteMapping("/{barberShopId}")
  public Map<String, String> deleteBarberShopById(@PathVariable Long barberShopId) {
    log.info("Deleting barbershop with ID={}", barberShopId);
    barberShopService.deleteBarberShopById(barberShopId);

    return Map.of("message", "Deletion of barber shop with ID+" + barberShopId + " was successful");
  }
}
