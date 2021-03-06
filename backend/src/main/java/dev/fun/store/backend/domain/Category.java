package dev.fun.store.backend.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category {

	private static final String SEQUENCE_NAME = "category_seq";
	
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
  @Column(name = "id")
  private Long id;
  
  @Column(name = "title", nullable = false)
  private String title;

	public Category(String title) {
		this.title = title;
	}
	
	@ManyToMany
  @JoinTable(
      name = "products_categories",
      joinColumns = @JoinColumn(name = "category_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products;
  	
}
