package dev.fun.store.backend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dev.fun.store.backend.finance.Money;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
	
	private static final String SEQUENCE_NAME = "product_seq";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
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
	
  @ManyToMany
  @JoinTable(
      name = "products_categories",
      joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"),
      uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "category_id"}))
  private List<Category> categories = new ArrayList<>();
	
}
