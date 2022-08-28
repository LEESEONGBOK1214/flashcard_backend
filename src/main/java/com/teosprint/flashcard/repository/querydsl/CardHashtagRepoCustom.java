package com.teosprint.flashcard.repository.querydsl;

import java.util.List;

public interface CardHashtagRepoCustom {
    List<String> getAllDistinctByNameAndNameContains(String name);
}