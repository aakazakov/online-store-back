package dev.fun.store.backend.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delivery_addresses")
public class DeliveryAddress {

	private static final String SEQ_NAME = "delivery_address_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "gps", nullable = false)
  private String gps;
  
  public DeliveryAddress(String gps) {
  	this.gps = gps;
  }
	
}
