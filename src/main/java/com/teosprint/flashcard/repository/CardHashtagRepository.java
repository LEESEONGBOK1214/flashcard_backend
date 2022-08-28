package com.teosprint.flashcard.repository;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.dto.CardHashTagDto;
import com.teosprint.flashcard.entity.CardHashTag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardHashtagRepository extends JpaRepository<CardHashTag, Long> {
    List<CardDto.CardSerachByHashtag> findCardAllByNameContains(String name);
    List<CardDto.CardSerachByHashtag> findCardAllByNameContains(String name, Pageable pageable);

    List<String> findNameDistinctByNameContains(String name);
}
