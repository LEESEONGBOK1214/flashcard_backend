package com.teosprint.flashcard.service.interfaces;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.entity.Card;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService{

    Card findCardById(Long cardId);
    CardDto.CardInfo getCardById(Long cardId);
    List<CardDto.CardSerachByHashtag> getCardAllByHashTag(String hashtag, Pageable pageable);
    CardDto.CardInfo postCard(CardDto.CardPost postDto);
    CardDto.CardInfo updateCard(CardDto.CardPost postDto);
    CardDto.CardInfo updateCardView(Long cardId);
    CardDto.CardInfo deleteCardById(Long cardId);
}
