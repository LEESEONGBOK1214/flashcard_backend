package com.teosprint.flashcard.controller;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.service.CardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CardController {

    @Autowired
    private CardServiceImpl cardService;

    @PostMapping("/card")
    @Operation(description = "카드 정보 등록 api입니다. 넘어온 값 그대로 전부 저장시킵니다.", summary = "카드 등록")
    public ResponseEntity<CardDto.CardInfo> postCard(@RequestBody CardDto.CardPost cardDto){
        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }
}
