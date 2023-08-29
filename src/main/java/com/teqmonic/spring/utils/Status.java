package com.teqmonic.spring.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {

	APPROVED("APPROVED"),
	NON_APPROVED("NON_APPROVED");

	private final String value;

}
