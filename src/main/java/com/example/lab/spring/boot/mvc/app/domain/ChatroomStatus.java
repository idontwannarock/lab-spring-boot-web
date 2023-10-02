package com.example.lab.spring.boot.mvc.app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatroomStatus {

	DELETED(0),
	ACTIVE(1);

	private final int value;

	@JsonValue
	public int value() {
		return this.value;
	}

	@JsonCreator
	public static ChatroomStatus of(Integer value) {
		if (value == null) {
			return null;
		}
		for (ChatroomStatus target : ChatroomStatus.values()) {
			if (target.value == value) {
				return target;
			}
		}
		return null;
	}
}
