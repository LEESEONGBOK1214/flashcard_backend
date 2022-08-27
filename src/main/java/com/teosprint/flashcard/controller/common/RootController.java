package com.teosprint.flashcard.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    // mapping과 return이 같으면 안 됨
    @GetMapping("/home")
    public String rootPage(){

        return "home_page";
    }
}
