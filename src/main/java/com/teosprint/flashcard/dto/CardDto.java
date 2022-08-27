package com.teosprint.flashcard.dto;


import com.teosprint.flashcard.entity.Card;
import com.teosprint.flashcard.entity.CardHashTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class CardDto {

    @Data
    @NoArgsConstructor
    public static class CardInfo{
        @Schema(description = "카드 기본키")
        private Long cardId;
        @Schema(description = "설명-앞")
        private String explain;
        @Schema(description = "정답-뒤")
        private String answer;
        @Schema(description = "해시태그 값 list (현재 한 개)")
        private List<CardHashTag> hashtags;

        public CardInfo(Card card){
            this.cardId = card.getId();
            this.explain = card.getExplain();
            this.answer = card.getAnswer();
            this.hashtags = card.getHashtags();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardPost{
        private String explain;
        private String answer;
        private List<String> hashtags;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardUpdate{
        @Schema(description = "카드 기본키")
        private Long cardId;
        @Schema(description = "설명-앞")
        private String explain;
        @Schema(description = "정답-뒤")
        private String answer;
        @Schema(description = "해시태그 값 list (현재 한 개)")
        private List<CardHashTag> hashtags;
    }
}
