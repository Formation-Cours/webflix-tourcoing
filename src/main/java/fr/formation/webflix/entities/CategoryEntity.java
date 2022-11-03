package fr.formation.webflix.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category")
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<VideoEntity> videos;
}
