package com.teqmonic.spring.model;

import java.time.LocalDateTime;

import com.teqmonic.spring.utils.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class Comment {

	private String review;
	
	private Status status;

	private LocalDateTime createdDateTime;

}
