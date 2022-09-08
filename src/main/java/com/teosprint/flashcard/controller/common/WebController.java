package com.teosprint.flashcard.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class WebController {

    // mapping과 return이 같으면 안 됨
    @GetMapping("/flashcard")
    public String flashcard(){
//        log.info("request - rootPage");
        return "flashcard/index.html";
    }

    
    // flashcard/a~~/b~~ 와같이 오면 white label이 나오는데 리다이렉트 시킴
    @RequestMapping(value = {"/{path:[^\\.]*}", "/**/{path:^(?!oauth).*}/{path:[^\\.]*}"}, method = RequestMethod.GET)
    public String forward() {
        return "forward:/";
    }


//    @GetMapping("/flashcard/**")
//    public String redirectFlashcard(){
////        log.info("request - rootPage");
//        return "flashcard/index.html";
//    }
}
