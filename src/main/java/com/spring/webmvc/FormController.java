package com.spring.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FormController {
    @GetMapping("/hello")
    public String hello() {
        return "/WEB-INF/views/hello.jsp";
    }
    public ModelAndView hello2(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
