package com.SocialMediaPlatform.Controller.Mapping;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RealTimeChatController {

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}