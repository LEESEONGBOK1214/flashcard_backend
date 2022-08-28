package com.teosprint.flashcard.service;

import com.teosprint.flashcard.config.exception.MyEntityNotFoundException;
import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.dto.CardHashTagDto;
import com.teosprint.flashcard.entity.Card;
import com.teosprint.flashcard.entity.CardHashTag;
import com.teosprint.flashcard.repository.CardHashtagRepository;
import com.teosprint.flashcard.repository.CardRepository;
import com.teosprint.flashcard.service.interfaces.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    @Autowired
    private EntityManager em;
    
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardHashtagRepository cardHashtagRepository;

//    @Autowired
//    private CardHashtagServiceImpl cardHashtagService;

    /**
     * card entity로 조회한다
     * @param cardId 카드 pk
     * @return Card Entity
     */
    @Override
    @Transactional(readOnly = true)
    public Card findCardById(Long cardId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        if(cardOpt.isPresent())return cardOpt.get();
        else throw new MyEntityNotFoundException("card", "id로 조회할 수 없습니다.");
    }

    /**
     * Card를 Dto로 조회한다.
     * @param cardId
     * @return CardDto.CardInfo Dto
     */
    @Override
    @Transactional(readOnly = true)
    public CardDto.CardInfo getCardById(Long cardId) {
        return new CardDto.CardInfo(findCardById(cardId));
    }



    @Override
    @Transactional(readOnly = true)
    public List<CardDto.CardSerachByHashtag> getCardAllByHashTag(String hashtag, Pageable pageable) {
        if(hashtag == null){
            hashtag="";
        }
        List<CardDto.CardSerachByHashtag> cardHashTags = cardHashtagRepository.findCardAllByNameContains(hashtag, pageable);
        log.info("card hash tags : {}", cardHashTags);
        return cardHashTags;
    }

    @Override
    @Transactional
    public CardDto.CardInfo postCard(CardDto.CardPost postDto) {
        
        Card card = Card.builder()
                // 앞
                .explain(postDto.getExplain())
                // 뒤
                .answer(postDto.getAnswer())
                .viewCount(0L)
                .build();
        Card save = cardRepository.save(card);
        
        // save 값을 저장시켜서 hashtag가 인식하도록 설정
        em.persist(save);
        em.flush();
        // 카드에 해시태그 값들 저장하기
        List<String> hashtags = postDto.getHashtags();
        List<CardHashTagDto.InCardInfo> hashTags = hashtags.stream().map(name -> {
                    CardHashTag hashtag = cardHashtagRepository.save(new CardHashTag(save, name));
                    em.persist(hashtag);
                    return new CardHashTagDto.InCardInfo(hashtag);
                })
                .collect(Collectors.toList());
        
        // 결과 반환에 save에 나오도록 데이터 밀어넣음
        em.flush();

        CardDto.CardInfo resCard = new CardDto.CardInfo(save);
        resCard.setHashtags(hashTags);

        log.info("save : {}", save);
        log.info("hashTags : {}", hashTags);
        log.info("resCard : {}", resCard);
        return resCard;
    }

    @Override
    @Transactional
    public CardDto.CardInfo updateCard(CardDto.CardPost postDto) {
        return null;
    }

    @Override
    @Transactional
    public CardDto.CardInfo updateCardView(Long cardId) {
        Card card = findCardById(cardId);
        card.setViewCount(card.getViewCount() + 1);
        Card savedEntity = cardRepository.save(card);
        CardDto.CardInfo resCardInfo = new CardDto.CardInfo(savedEntity);
        return resCardInfo;
    }

    @Override
    @Transactional
    public CardDto.CardInfo deleteCardById(Long cardId) {
        return null;
    }
}
