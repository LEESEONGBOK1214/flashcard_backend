package com.teosprint.flashcard.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 계정 유형
 *
 * FLASHCARD
 * GIT
 * APPLE
 * GOOGLE
 */

@AllArgsConstructor
@Getter
public enum LoginTypeEnums implements EnumGeneric<String> {
    FLASHCARD("FLASHCARD"),
    GIT("GIT"),
    APPLE("APPLE"),
    GOOGLE("GOOGLE");

    private final String value;
}