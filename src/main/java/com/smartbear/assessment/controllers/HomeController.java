package com.smartbear.assessment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page redirecting to swagger documentation
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }

}
