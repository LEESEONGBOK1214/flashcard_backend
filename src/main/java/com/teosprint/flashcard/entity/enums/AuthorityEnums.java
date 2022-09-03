package com.teosprint.flashcard.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 권한유형
 * ROLE_ROOT
 * ROLE_USER
 */

@AllArgsConstructor
@Getter
public enum AuthorityEnums implements EnumGeneric<String> {
    ROOT("ROLE_ROOT"),
    USER("ROLE_USER");

    private final String value;
}