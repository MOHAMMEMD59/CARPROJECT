package com.mezzi.carsnav.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@Controller
public class ErrorController {

    @GetMapping("/unauthorized")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String unauthorizedPage() {
        return "unauthorized";
    }
}

