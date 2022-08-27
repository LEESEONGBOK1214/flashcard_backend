package com.teosprint.flashcard.dto;

import com.teosprint.flashcard.entity.CardHashTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CardHashTagDto {

    @Data
    @AllArgsConstructor@NoArgsConstructor
    public static class InCardInfo{
        private Long cardHashtagId;
        private String name; // 해시태그가 아무리 길어봤자 50자를 넘기겠어?

        public InCardInfo(CardHashTag cardHashTag){
            this.cardHashtagId = cardHashTag.getId();
            this.name = cardHashTag.getName();
        }
    }
}
