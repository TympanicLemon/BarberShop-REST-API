package barber.shop.dao;

import barber.shop.controller.model.CustomerData;
import barber.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CustomerDao extends JpaRepository<Customer, Long> {
  Optional<Customer> findByEmail(String customerEmail);
}
