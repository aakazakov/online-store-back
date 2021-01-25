package dev.fun.store.backend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dev.fun.store.backend.domain.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

	private static final String SEQUENCE_NAME = "user_seq";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	public User(String login, String password, Boolean enabled) {
		this.login = login;
		this.password = password;
		this.enabled = enabled;
	}
	
  @ManyToMany
  @JoinTable(
  		name = "users_authorities",
  		joinColumns = @JoinColumn(name = "user_id"),
  		inverseJoinColumns = @JoinColumn(name = "authority_id"),
  		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "authority_id"}))
  private List<Authority> authorities = new ArrayList<>();
  
  @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
  private Basket basket;
	
}
