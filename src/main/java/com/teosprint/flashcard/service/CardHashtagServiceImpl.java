//package com.teosprint.flashcard.service;
//
//import com.teosprint.flashcard.repository.CardHashtagRepository;
//import com.teosprint.flashcard.repository.querydsl.CardHashtagRepoCustomImpl;
//import com.teosprint.flashcard.service.interfaces.CardHashtagService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class CardHashtagServiceImpl implements CardHashtagService {
//
//    // Autowired를 사용하면 인식을 못 함.
////    @Qualifier("CardHashtagRepoCustomImpl")
//    private final CardHashtagRepoCustomImpl cardHashtagRepoCustom;
//
//    @Override
//    public List<String> getHashTagsContainsName(String name) {
//        List<String> hashtags = cardHashtagRepoCustom.getAllDistinctByNameAndNameContains(name);
//        log.info("해시태그 값 조회 결과 : {}", hashtags);
//
//        return hashtags;
//    }
//}
