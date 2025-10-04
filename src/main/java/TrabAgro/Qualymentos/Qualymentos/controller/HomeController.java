package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping
    public String home() {
        return "home";
    }  
}
