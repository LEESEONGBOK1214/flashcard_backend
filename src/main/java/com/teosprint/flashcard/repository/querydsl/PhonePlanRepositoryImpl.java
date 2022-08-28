//package com.teosprint.flashcard.repository.querydsl;
//
//import com.jsol.mcall.dto.PhonePlanDto;
//import com.jsol.mcall.dto.QPhonePlanDto_PhonePlanSearch;
//import com.jsol.mcall.entity.PhonePlan;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
//
//import java.util.List;
//
//import static com.jsol.mcall.entity.QPhonePlan.phonePlan;
//import static com.jsol.mcall.entity.QTelecom.telecom;
//
//public class PhonePlanRepositoryImpl extends QuerydslRepositorySupport implements PhonePlanRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    public PhonePlanRepositoryImpl(JPAQueryFactory queryFactory) {
//        super(PhonePlan.class);
//        this.queryFactory = queryFactory;
//    }
//
//    public List<PhonePlanDto.PhonePlanSearch> getAllByNameAndTelecomIdOrTelecomName(String name, Long telecomId, String telecomName) {
//        // shop을 조회할때 shop과 연관관계인 owner(Account)의 정보를 한 번에 다 가져오도록. N+1 방지
//        List<PhonePlanDto.PhonePlanSearch> phonePlans = queryFactory
//                .select(new QPhonePlanDto_PhonePlanSearch(phonePlan))
//                .from(phonePlan)
//                .join(phonePlan.telecom, telecom)
//                .where(
//                        // 요금제 이름 검색, 검색 값이 포함되는지 확인, 결과가 null이면 조건 추가 x
//                        nameContains(name),
//                        // 통신사 id와 같은지 확인, 결과가 null이면 조건 추가 x
//                        telecomIdEq(telecomId),
//                        // 통신사 name과 같은지 확인, 결과가 null이면 조건 추가 x
//                        telecomNameEq(telecomName)
//
//                )
//                .orderBy(phonePlan.frequency.desc(),phonePlan.id.desc())
//                .fetch();
//        return phonePlans;
//    }
//
//    private BooleanExpression nameContains(String name) {
//        return name != null ? phonePlan.name.contains(name) : null;
//    }
//
//    private BooleanExpression telecomIdEq(Long telecomId) {
//        return telecomId != null ? phonePlan.telecom.id.eq(telecomId) : null;
//    }
//
//    private BooleanExpression telecomNameEq(String telecomName) {
//        return telecomName != null ? phonePlan.telecom.name.eq(telecomName) : null;
//    }
//
//
//}
