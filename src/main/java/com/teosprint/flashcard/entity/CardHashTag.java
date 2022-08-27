package com.teosprint.flashcard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // 엔티티(테이블엔티티) 임을 알림
@Table(schema = "flashcard", name = "tb_card_hashtag") // 테이블명
@Data
@NoArgsConstructor // 기본 생성자 =>class() 추가
@AllArgsConstructor @Builder // 전체 생성자에 builder까지 설정
public class CardHashTag {
    // 카드엔 이름, 내용, 해시태그가 들어감
    // 카드:해시태그 = 1:n
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_hashtag_id")
    private Long id;

    // 해시태그 명
    @Column(nullable = false, length = 50)
    private String name; // 해시태그가 아무리 길어봤자 50자를 넘기겠어?

    // 카드와 모든 연산을 동일시한다.
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "tb_card_hashtag_card_id_fk"), nullable = false)
    private Card card;
}
