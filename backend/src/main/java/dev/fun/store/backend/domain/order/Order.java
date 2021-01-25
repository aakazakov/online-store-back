package dev.fun.store.backend.domain.order;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.fun.store.backend.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
  @Column(name = "status")
  private OrderStatus status;
	
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created")
  private Date created;
  
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated")
  private Date updated;
  
  @Column(name = "delivery_address")
  private String deliveryAddress;
  
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  @Column(name = "total_cost")
  private Long totalCost;
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderDetails> orderDetails;

}
