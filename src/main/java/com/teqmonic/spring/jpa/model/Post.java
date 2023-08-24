package com.teqmonic.spring.jpa.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {

	private String name;
	
	private String content;

	private LocalDateTime createdDate;

	private List<Comment> comments;
}
