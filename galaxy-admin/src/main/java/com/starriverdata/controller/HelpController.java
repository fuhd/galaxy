package com.starriverdata.controller;

import com.starriverdata.config.UnCheckLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/help")

public class HelpController {

    @RequestMapping
    @UnCheckLogin
    public String index() {
        return "help";
    }

}
