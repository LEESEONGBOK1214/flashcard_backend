package com.teosprint.flashcard.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.teosprint.flashcard.dto.CardHashTagDto;
import com.teosprint.flashcard.entity.CardHashTag;
import com.teosprint.flashcard.entity.QCardHashTag;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.teosprint.flashcard.entity.QCardHashTag.cardHashTag;


public class CardHashtagRepoCustomImpl extends QuerydslRepositorySupport implements CardHashtagRepoCustom {

    private final JPAQueryFactory queryFactory;

    public CardHashtagRepoCustomImpl(JPAQueryFactory queryFactory) {
        super(CardHashTag.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<String> getAllDistinctByNameAndNameContains(String name) {
         // card hashtag 조회하는데,
        // hashtag-name을 전체에서 중복제거 후 가져온다
        List<String> hashtagNames = queryFactory
                .select(cardHashTag.name).distinct()
                .from(cardHashTag)
                .where(
                        // 요금제 이름 검색, 검색 값이 포함되는지 확인, 결과가 null이면 조건 추가 x
                        nameContains(name)
//                        // 통신사 id와 같은지 확인, 결과가 null이면 조건 추가 x
//                        telecomIdEq(telecomId),
//                        // 통신사 name과 같은지 확인, 결과가 null이면 조건 추가 x
//                        telecomNameEq(telecomName)

                )
                .orderBy(cardHashTag.name.desc())
                .fetch();
        return hashtagNames;

//        return null;
    }

    private BooleanExpression nameContains(String name) {
        return name != null ? cardHashTag.name.contains(name) : null;
    }

//    private BooleanExpression telecomIdEq(Long telecomId) {
//        return telecomId != null ? phonePlan.telecom.id.eq(telecomId) : null;
//    }
//
//    private BooleanExpression telecomNameEq(String telecomName) {
//        return telecomName != null ? phonePlan.telecom.name.eq(telecomName) : null;
//    }
}
