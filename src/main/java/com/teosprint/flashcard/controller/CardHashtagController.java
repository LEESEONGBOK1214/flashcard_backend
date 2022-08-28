//package com.teosprint.flashcard.controller;
//
//import com.teosprint.flashcard.dto.CardDto;
//import com.teosprint.flashcard.dto.CardHashTagDto;
//import com.teosprint.flashcard.service.CardHashtagServiceImpl;
//import com.teosprint.flashcard.service.CardServiceImpl;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/v1/card")
//@Tag(name="Card-Hashtag",description = "카드-해시태그 API")
//@Slf4j
//@RequiredArgsConstructor
//public class CardHashtagController {
//
//    private final CardHashtagServiceImpl cardHashtagService;
//
//
//
//    @GetMapping("/hashtag")
//    @Operation(description = "카드에 설정된 해시태그 값 여러개를 조회하는 api입니다. queryString으로 넘어온 조건에 따라 조회합니다.", summary = "카드 해시태그 다중 조회", tags = {"Card-Hashtag"})
//    public ResponseEntity<List<String>> getHashTagNames(
//            @Schema(name = "name", description = "name 값으로 넘어온 데이터를 포함하는 모든 해시태그를 조회한다.")
//            @RequestParam(required = false) String name
//    ){
//        log.info("/card/hashtag?name={}", name);
//        List<String> hashtags = cardHashtagService.getHashTagsContainsName(name);
//        return new ResponseEntity<>(hashtags,HttpStatus.OK);
////        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
////        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
//    }
//
//
//}
