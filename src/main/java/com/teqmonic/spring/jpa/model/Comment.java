package com.teqmonic.spring.jpa.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class Comment {

	private String review;

	private LocalDateTime createdDateTime;

}
