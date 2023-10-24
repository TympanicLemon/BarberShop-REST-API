package barber.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class BarberShop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long barberShopId;

  private String name;
  private String address;
  private String city;
  private String state;
  private String zip;
  private String phoneNumber;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy = "barberShop", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private Set<Employee> employees = new HashSet<>();

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "barber_shop_customer",
    joinColumns = @JoinColumn(name = "barber_shop_id"),
    inverseJoinColumns = @JoinColumn(name = "customer_id"))
  private Set<Customer> customers = new HashSet<>();
}
