package dev.fun.store.backend.domain;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "baskets")
public class Basket {

	private static final String SEQUENCE_NAME = "basket_seq";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToMany
	@JoinTable(
	    name = "baskets_products",
	    joinColumns = @JoinColumn(name = "basket_id"),
	    inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> products;
	
	public Basket(User user) {
		this.user = user;
	}
	
}
