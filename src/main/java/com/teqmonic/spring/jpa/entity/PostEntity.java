package com.teqmonic.spring.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Builder(toBuilder = true)
@Entity
@Table(name = "Post")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "post_id_seq", allocationSize = 1)
	private Long id;

	@NotBlank(message = "Post name is required")
	@Size(max = 10, message = "Post name must be atmost 10 characters")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "Post content is required")
	@Size(max = 50, message = "Post content must be atmost 50 characters")
	@Column(name = "content")
	private String content;

	@Column(name = "createdDateTime")
	private LocalDateTime createdDate;

	// All the operations should be cascaded from Parent entity to Child entity
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
	@Singular
	private List<CommentEntity> comments;
}
