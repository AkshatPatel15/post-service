package com.teqmonic.spring.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

	@NotBlank(message = "Post name is required")
	@Size(max = 10, message = "Post name must be atmost 10 characters")
	private String name;
	
	@NotBlank(message = "Post content is required")
	@Size(max = 50, message = "Post content must be atmost 50 characters")
	private String content;

	private LocalDateTime createdDate;

	private List<Comment> comments;
}
