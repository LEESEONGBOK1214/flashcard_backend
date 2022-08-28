package com.teosprint.flashcard.controller;

import com.teosprint.flashcard.dto.CardDto;
import com.teosprint.flashcard.service.CardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Tag(name = "Card", description = "카드 API") //swagger 설정
@Slf4j
@RequiredArgsConstructor
public class CardController {

    @Setter
    private Long counts = 0L;

    private void addCounts(String message) {
        setCounts(this.counts + 1);
        log.info("요청 API : {} 총 API 통신 회수 : {}", message, this.counts);
    }


    private final CardServiceImpl cardService;

    @GetMapping("/card/{cardId}")
    @Operation(description = "카드 정보 단일 조회 api입니다. {cardId}로 넘어온 값을 단일조회합니다.", summary = "카드 단일 조회", tags = {"Card"})
    public ResponseEntity<CardDto.CardInfo> getCardById(@PathVariable Long cardId) {
        addCounts("카드 정보 단일 조회 api");
        CardDto.CardInfo cardInfo = cardService.getCardById(cardId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }

    @GetMapping("/card")
    @Operation(description = "카드 정보 여러개를 조회하는 api입니다. queryString으로 넘어온 조건에 따라 조회합니다.", summary = "카드 다중 조회", tags = {"Card"})
    public ResponseEntity<List<CardDto.CardSerachByHashtag>> getCards(
            @Parameter(name = "hashtag", description = "hashtag 값으로 넘어온 데이터를 포함하는 모든 Card를 조회한다.")
            @RequestParam(required = false) String hashtag,

            //페이지네이션, 솔팅 설정

            @Parameter(name = "pageNo", description = "전체 데이터중 가져올 페이지 번호 0부터 시작한다. 기본값은 0")
            @RequestParam(defaultValue = "0", required = false) Integer pageNo,

            @Parameter(name = "pageSize", description = "가져올 데이터양을 지정한다. 기본값은 10")
            @RequestParam(defaultValue = "10", required = false) Integer pageSize,

            @Parameter(name = "sortBy", description = "정렬 기준 값을 정한다. 기본값은 card_id", schema = @Schema(description = "정렬기준", type = "string", allowableValues = {
                    "card_id", "card_explain", "card_answer", "card_viewCount"
            }))
            @RequestParam(defaultValue = "cardId", required = false) String sortBy,

            @Parameter(name = "sortOrder", description = "가져올 데이터의 정렬 기준, desc외엔 asc로 정렬", schema = @Schema(description = "정렬기준", type = "string", allowableValues = {
                    "asc", "desc"
            }))
            @RequestParam(defaultValue = "asc", required = false) String sortOrder
    ) {
        addCounts("카드 정보 다중 조회 api");
        log.info("/card - hashtag : {}", hashtag);

        Sort sort = Sort.by(sortBy);

        if (sortOrder.equals("desc")) sort.descending();
        else sort.ascending();

        Pageable pageable =
                PageRequest.of(pageNo, pageSize, sort);

        List<CardDto.CardSerachByHashtag> cardAllByHashTag = cardService.getCardAllByHashTag(hashtag, pageable);
        return new ResponseEntity<>(cardAllByHashTag, HttpStatus.OK);
//        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
//        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }

    @PostMapping("/card")
    @Operation(description = "카드 정보 등록 api입니다. 넘어온 값 그대로 전부 저장시킵니다.", summary = "카드 등록", tags = {"Card"})
    public ResponseEntity<CardDto.CardInfo> postCard(@Valid @RequestBody CardDto.CardPost cardDto) {
        addCounts("카드 정보 등록 api");
        CardDto.CardInfo cardInfo = cardService.postCard(cardDto);
        return new ResponseEntity<>(cardInfo, HttpStatus.CREATED);
    }

    @PutMapping("/card/{cardId}/view")
    @Operation(description = "카드 조회수 증가 api입니다.", summary = "카드 조회수 1 증가", tags = {"Card"})
    public ResponseEntity<CardDto.CardInfo> postCardView(@PathVariable Long cardId) {
        addCounts("카드 정보 view+1 api");
        CardDto.CardInfo cardInfo = cardService.updateCardView(cardId);
        return new ResponseEntity<>(cardInfo, HttpStatus.OK);
    }
}
