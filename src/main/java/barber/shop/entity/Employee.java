package barber.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long employeeId;

  private String firstName;
  private String lastName;
  private String phoneNumber;

  @Column(unique = true)
  private String email;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToOne
  @JoinColumn(name = "barber_shop_id")
  private BarberShop barberShop;
}
