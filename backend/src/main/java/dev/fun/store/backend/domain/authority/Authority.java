package dev.fun.store.backend.domain.authority;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dev.fun.store.backend.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authorities")
public class Authority {

	private static final String SEQUENCE_NAME = "authority_seq";
	private static final String PREFIX = "ROLE_";
	
	@Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
  @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;

	public Authority(Role title) {
		this.title = PREFIX + title.name();
	}
	
	public Authority(Auth title) {
		this.title = title.name();
	}
	
	/**
	 * Sets title value;<p>
	 * <strong>It is preferable to use this method instead of setTitle(String title)</strong>
	 * @param title {@code String} title value
	 * @param prefix {@code true} if title is {@link Role} name, otherwise {@code false} 
	 */
	public void setTitle(String title, boolean prefix) {
		if (prefix) {
			this.title = PREFIX + title;
		} else {
			this.title = title;
		}
	}
	
  @ManyToMany
  @JoinTable(
  		name = "users_authorities",
  		joinColumns = @JoinColumn(name = "authority_id"),
  		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> users = new ArrayList<>();

}
