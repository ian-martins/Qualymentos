package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
  
    @GetMapping
    public String ola() {
        return "home";
    }  

    @GetMapping("/cadastro_sucesso")
    public String cs() {
        return "cadastro_sucesso";
    }  
}
