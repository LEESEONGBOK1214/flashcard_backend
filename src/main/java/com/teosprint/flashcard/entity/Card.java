package com.teosprint.flashcard.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import java.util.List;

@Entity // 엔티티(테이블엔티티) 임을 알림
@Table(schema = "flashcard", name = "tb_card") // 테이블명
@Getter @Setter 

// toString에 hashtags 제거
@ToString(exclude = "hashtags")
@NoArgsConstructor // 기본 생성자 =>class() 추가
@AllArgsConstructor @Builder // 전체 생성자에 builder까지 설정
public class Card {
    // 카드엔 이름, 내용, 해시태그가 들어감
    // 카드:해시태그 = 1:n
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Comment("카드의 explain(설명) 인데 explain으로 하면 예약어랑 겹쳐서 _str을 붙여줌")
    @Column(name = "explain_str", nullable = false, length = 400)
    private String explain;

    @Column(nullable = false, length = 4000)
    private String answer;

    @Column(nullable = false)
    private Long viewCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "tb_card_card_hash_tag_oTm_fk"))
    private List<CardHashTag> hashtags;
}
