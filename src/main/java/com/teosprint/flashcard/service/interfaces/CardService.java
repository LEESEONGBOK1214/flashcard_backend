package com.teosprint.flashcard.service.interfaces;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.entity.Card;

import java.util.List;

public interface CardService{

    CardDto.CardInfo getCardById(Long cardId);
    List<CardDto.CardInfo> getCardAllByHashTag(String hashtag);
    CardDto.CardInfo postCard(CardDto.CardPost postDto);
    CardDto.CardInfo updateCard(CardDto.CardPost postDto);
    CardDto.CardInfo deleteCardById(Long cardId);
}
