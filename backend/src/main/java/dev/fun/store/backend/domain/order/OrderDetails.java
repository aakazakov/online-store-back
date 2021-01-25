package dev.fun.store.backend.domain.order;

import javax.persistence.*;

import dev.fun.store.backend.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
	
  private static final String SEQ_NAME = "order_details_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @Column(name = "id")
  private Long id;
  
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  
  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;
  
  @Column(name = "amount")
  private Integer amount;
  
  @Column(name = "total_cost")
  private Long totalCost;
	
}
