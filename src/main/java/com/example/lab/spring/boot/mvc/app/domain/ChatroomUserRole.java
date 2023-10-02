package com.example.lab.spring.boot.mvc.app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatroomUserRole {

	OWNER(1),
	ADMIN(2),
	PARTICIPANT(3);

	private final int value;

	@JsonValue
	public int value() {
		return value;
	}

	@JsonCreator
	public static ChatroomUserRole of(Integer value) {
		if (value == null) {
			return null;
		}
		for (ChatroomUserRole target : ChatroomUserRole.values()) {
			if (target.value() == value) {
				return target;
			}
		}
		return null;
	}
}
