package com.teosprint.flashcard.repository;

import com.teosprint.flashcard.entity.CardHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardHashtagRepository extends JpaRepository<CardHashTag, Long> {
}
