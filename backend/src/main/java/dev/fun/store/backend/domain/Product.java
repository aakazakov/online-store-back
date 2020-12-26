package dev.fun.store.backend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dev.fun.store.backend.finance.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
	
	private static final String SEQUENCE_NAME = "product_seq";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	@Setter(value = AccessLevel.PRIVATE)
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "cost", nullable = false)
	private Long cost;

	public Product(String title, Long cost) {
		this.title = title;
		this.cost = cost;
	}
	
	public Product(String title, Double cost) {
		this.title = title;
		this.cost = Money.doubleToLong(cost);
	}
	
	public double getCostInDoubleValue() {
		return Money.longToDouble(cost);
	}
	
}
