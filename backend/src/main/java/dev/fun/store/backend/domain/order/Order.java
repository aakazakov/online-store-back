package dev.fun.store.backend.domain.order;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.fun.store.backend.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

	private static final String SEQ_NAME = "order_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @Column(name = "id")
  private Long id;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private OrderStatus status;
	
  @CreationTimestamp
  @Column(name = "created", nullable = false)
  private LocalDateTime created;
  
  @UpdateTimestamp
  @Column(name = "updated")
  private LocalDateTime updated;
  
  @Column(name = "delivery_address")
  private String deliveryAddress;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  @Column(name = "total_cost")
  private Long totalCost;
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderDetails> orderDetails;

	public Order(OrderStatus status, LocalDateTime created) {
		this.status = status;
		this.created = created;
	}

}
