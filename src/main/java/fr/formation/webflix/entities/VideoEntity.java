package fr.formation.webflix.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video")
public class VideoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, columnDefinition = "smallint unsigned")
	private Integer duration;
	@Column(nullable = false, length = 60)
	private String originCountry;
	@Column(columnDefinition = "TEXT")
	private String synopsis;
	@Column(nullable = false)
	private String urlVideo;
	@Column(nullable = false)
	private String cover;
	@Column(columnDefinition = "smallint unsigned")
	private Integer productYear;

	private Calendar datePublished;
	private Calendar dateDeleted;

	@OneToMany(mappedBy = "video")
	private Collection<VideoProfileEntity> profiles;

	@ManyToOne(optional = false)
	private CategoryEntity category;

	@ManyToMany
	@JoinTable(
			name = "video_actor",
			joinColumns = @JoinColumn(name = "video_id"),
			inverseJoinColumns = @JoinColumn(name = "actor_id")
	)
	private Collection<ActorEntity> actors;
}
