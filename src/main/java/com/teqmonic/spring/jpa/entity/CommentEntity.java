package com.teqmonic.spring.jpa.entity;

import java.time.LocalDateTime;

import com.teqmonic.spring.utils.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGen")
	@SequenceGenerator(name = "seqGen", sequenceName = "comment_id_seq", allocationSize = 1)
	private Long id;


	@NotBlank(message = "Review is required")
	@Size(max = 50, message = "Review must be atmost 50 characters")
	@Column(name = "review")
	private String review;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	@ManyToOne
	//@JoinColumn(name = "post_det_id")
	private PostEntity post;

}
