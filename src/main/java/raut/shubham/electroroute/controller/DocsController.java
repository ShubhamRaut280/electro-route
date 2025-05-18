package raut.shubham.electroroute.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocsController {


    @GetMapping("/docs")
    public String redirectToSwaggerUI() {
        return "redirect:/swagger-ui/index.html";
    }
}
