package barber.shop.controller;

import barber.shop.controller.model.BarberShopData;
import barber.shop.service.BarberShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/barber_shop")
@Slf4j
public class BarberShopController {
  @Autowired
  BarberShopService barberShopService;

  // Create
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BarberShopData createBarberShop(@RequestBody BarberShopData barberShopData) {
    log.info("Creating barber shop {}", barberShopData);
    return barberShopService.createBarberShop(barberShopData);
  }

  // Update
  @PutMapping("/{barberShopId}")
  public BarberShopData updateBarberShop(@PathVariable Long barberShopId, @RequestBody BarberShopData barberShopData) {
    barberShopData.setBarberShopId(barberShopId);
    log.info("Updating barber shop with ID={}", barberShopId);
    return barberShopService.createBarberShop(barberShopData);
  }

  // Get
  @GetMapping("/{barberShopId}")
  public BarberShopData getBarberShop(@PathVariable Long barberShopId) {
    log.info("Retrieving barbershop with ID={}", barberShopId);
    return barberShopService.getBarberShop(barberShopId);
  }

  // Get all
  @GetMapping
  public Set<BarberShopData> getAllBarberShops() {
    log.info("Retrieving all available barber shops.");
    return barberShopService.getAllBarberShops();
  }

  // Delete
  @DeleteMapping("/{barberShopId}")
  public Map<String, String> deleteBarberShop(@PathVariable Long barberShopId) {
    log.info("Deleting barbershop with ID={}", barberShopId);
    barberShopService.deleteBarberShop(barberShopId);
    return Map.of("message", "Deletion of barber shop with ID+" + barberShopId + " was successful");
  }
}
