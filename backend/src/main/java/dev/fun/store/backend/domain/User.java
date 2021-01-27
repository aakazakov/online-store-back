package dev.fun.store.backend.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.fun.store.backend.domain.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

	private static final String SEQUENCE_NAME = "user_seq";
	
	@Transient
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;
	
	public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
	}

	public User(String username, String password, Boolean enabled) {
		this.username = username;
		this.password = passwordEncoder.encode(password);
		this.enabled = enabled;
	}
	
  @ManyToMany(fetch =  FetchType.EAGER)
  @JoinTable(
  		name = "users_authorities",
  		joinColumns = @JoinColumn(name = "user_id"),
  		inverseJoinColumns = @JoinColumn(name = "authority_id"),
  		uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "authority_id"}))
  private List<Authority> authorities = new ArrayList<>();
  
  @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
  private Basket basket;
	
}
