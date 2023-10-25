package barber.shop.dao;

import barber.shop.entity.BarberShop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberShopDao extends JpaRepository<BarberShop, Long> {
}
