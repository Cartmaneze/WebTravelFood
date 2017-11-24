package com.komarov.travelFood.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/anonymousEnter")
    public String anonymEnter() {
        return "anonymous/startCounts";
    }

    @GetMapping("/loginEnter")
    public String loginEnter() {
        return "autorized/login";
    }

    @GetMapping("/newUser")
    public String createLogin() {
        return "autorized/newLogin";
    }
}
