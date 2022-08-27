package com.teosprint.flashcard.repository;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.entity.Card;
import com.teosprint.flashcard.entity.CardHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<CardDto.CardInfo> findAllByHashtagsContains(CardHashTag hashtag);
}
