package com.teosprint.flashcard.service;

import com.teosprint.flashcard.config.exception.MyEntityNotFoundException;
import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.entity.Card;
import com.teosprint.flashcard.repository.CardRepository;
import com.teosprint.flashcard.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public CardDto.CardInfo getCardById(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        if(cardOpt.isPresent())return new CardDto.CardInfo(cardOpt.get());
        else throw new MyEntityNotFoundException("card", "id로 조회할 수 없습니다.");
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDto.CardInfo> getCardAllByHashTag(String hashtag) {
        List<CardDto.CardInfo> cards = cardRepository.findAllByHashtagsContains(hashtag);
        return cards;
    }

    @Override
    @Transactional
    public CardDto.CardInfo postCard(CardDto.CardPost postDto) {
        Card card = Card.builder()
                // 앞
                .explain(postDto.getExplain())
                // 뒤
                .answer(postDto.getAnswer())
                .build();
        Card save = cardRepository.save(card);
        return new CardDto.CardInfo(save);
    }

    @Override
    @Transactional
    public CardDto.CardInfo updateCard(CardDto.CardPost postDto) {
        return null;
    }

    @Override
    @Transactional
    public CardDto.CardInfo deleteCardById(Long cardId) {
        return null;
    }
}
