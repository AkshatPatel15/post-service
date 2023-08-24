package com.teqmonic.spring.jpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder(toBuilder = true)
@Entity
@Table(name = "Comment")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CommentEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Review is required")
	@Size(max = 50, message = "Review must be atmost 50 characters")
	@Column(name = "review")
	private String review;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	@ManyToOne
	//@JoinColumn(name = "post_det_id")
	private PostEntity post;

}
