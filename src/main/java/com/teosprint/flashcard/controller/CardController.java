package com.teosprint.flashcard.controller;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.service.CardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CardController {

    @Setter
    private Long counts = 0L;

    private void addCounts(String message){
        setCounts(this.counts+1);
        log.info("요청 API : {} 총 API 통신 회수 : {}",message, this.counts);
    }

    @Autowired
    private CardServiceImpl cardService;

    @GetMapping("/card/{cardId}")
    @Operation(description = "카드 정보 단일 조회 api입니다. {cardId}로 넘어온 값을 단일조회합니다.", summary = "카드 단일 조회")
    public ResponseEntity<CardDto.CardInfo> postCard(@PathVariable Long cardId){
        addCounts("카드 정보 단일 조회 api");
        CardDto.CardInfo cardInfo = cardService.getCardById(cardId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }
    @GetMapping("/card")
    @Operation(description = "카드 정보 여러개를 조회하는 api입니다. queryString으로 넘어온 조건에 따라 조회합니다.", summary = "카드 다중 조회")
    public ResponseEntity<List<CardDto.CardSerachByHashtag>> postCard(
            @Schema(name = "hashtag", description = "hashtag 값으로 넘어온 데이터를 포함하는 모든 Card를 조회한다.")
            @RequestParam(required = false) String hashtag
    ){
        addCounts("카드 정보 다중 조회 api");
        log.info("/card - hashtag : {}", hashtag);
        List<CardDto.CardSerachByHashtag> cardAllByHashTag = cardService.getCardAllByHashTag(hashtag);
        return new ResponseEntity<>(cardAllByHashTag,HttpStatus.OK);
//        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
//        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }
    @PostMapping("/card")
    @Operation(description = "카드 정보 등록 api입니다. 넘어온 값 그대로 전부 저장시킵니다.", summary = "카드 등록")
    public ResponseEntity<CardDto.CardInfo> postCard(@RequestBody CardDto.CardPost cardDto){
        addCounts("카드 정보 등록 api");
        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }

    @PutMapping("/card/{cardId}/view")
    @Operation(description = "카드 조회수 증가 api입니다.", summary = "카드 조회수 1 증가")
    public ResponseEntity<CardDto.CardInfo> postCardView(@PathVariable Long cardId){
        addCounts("카드 정보 view+1 api");
        CardDto.CardInfo cardInfo = cardService.updateCardView(cardId);
        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }
}
