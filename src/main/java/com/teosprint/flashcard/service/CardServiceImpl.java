package com.teosprint.flashcard.service;

import com.teosprint.flashcard.config.exception.MyEntityNotFoundException;
import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.entity.Card;
import com.teosprint.flashcard.entity.CardHashTag;
import com.teosprint.flashcard.repository.CardHashtagRepository;
import com.teosprint.flashcard.repository.CardRepository;
import com.teosprint.flashcard.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHashtagRepository cardHashtagRepository;

    @Autowired
    private CardHashtagServiceImpl cardHashtagService;

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
        
        // save 값을 저장시켜서 hashtag가 인식하도록 설정
        em.persist(save);
        em.flush();
        // 카드에 해시태그 값들 저장하기
        List<String> hashtags = postDto.getHashtags();
        List<CardHashTag> hashTags = hashtags.stream().map(name -> {
                    CardHashTag hashtag = cardHashtagRepository.save(new CardHashTag(save, name));
                    em.persist(hashtag);
                    return hashtag;
                })
                .collect(Collectors.toList());
        
        // 결과 반환에 save에 나오도록 데이터 밀어넣음
        em.flush();
        return getCardById(save.getId());
//        return new CardDto.CardInfo(save);
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
