package com.teosprint.flashcard.controller;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.service.CardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CardController {

    @Autowired
    private CardServiceImpl cardService;

    @GetMapping("/card/{cardId}")
    @Operation(description = "카드 정보 단일 조회 api입니다. {cardId}로 넘어온 값을 단일조회합니다.", summary = "카드 단일 조회")
    public ResponseEntity<CardDto.CardInfo> postCard(@PathVariable Long cardId){
        CardDto.CardInfo cardInfo = cardService.getCardById(cardId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }
    @GetMapping("/card")
    @Operation(description = "카드 정보 전체 조회 api입니다. queryString으로 넘어온 조건에 따라 조회합니다.", summary = "카드 다중 조회")
    public ResponseEntity<CardDto.CardInfo> postCard(@RequestParam(required = false) CardDto.CardInfo cardDto){
        return new ResponseEntity<>(cardDto,HttpStatus.OK);
//        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
//        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }
    @PostMapping("/card")
    @Operation(description = "카드 정보 등록 api입니다. 넘어온 값 그대로 전부 저장시킵니다.", summary = "카드 등록")
    public ResponseEntity<CardDto.CardInfo> postCard(@RequestBody CardDto.CardPost cardDto){
        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }
}
