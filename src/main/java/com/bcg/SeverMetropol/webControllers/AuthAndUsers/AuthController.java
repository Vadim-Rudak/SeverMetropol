package com.bcg.SeverMetropol.webControllers.AuthAndUsers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AuthController {


    @GetMapping("/login")
    public String login(){
        return "Auth/login";
    }

}
