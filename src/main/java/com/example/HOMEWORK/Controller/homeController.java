package com.example.HOMEWORK.Controller;

import jakarta.persistence.GeneratedValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Slf4j
@Controller
public class homeController {
    // 첫 홈페이지 창 호출
    @GetMapping("/")
    public String home() {
        return "home";
    }

    //로그인창 호출
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    //파라미터를 받았을 때 로그인 창 호출
    @GetMapping(value = "/login",params = "artifactId")
    public String login( @RequestParam("artifactId") String artifactId,Model model) {

        model.addAttribute("artifactId", artifactId);

        log.info(artifactId);
        return "login";
    }

    //회원가입 등록 직전 창 호출
    @GetMapping("/signup")
    public String beforEnroll() {
        return "BeforeEnroll";
    }

}
