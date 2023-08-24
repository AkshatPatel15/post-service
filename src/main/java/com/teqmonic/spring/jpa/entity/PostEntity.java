package com.teqmonic.spring.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Entity
@Table(name = "Post")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	private List<CommentEntity> comments;
}
