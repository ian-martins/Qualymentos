package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.RegisterPropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final PropriedadeService propriedadeService;

    @GetMapping
    public String conf() {
        return "user";
    }

    @GetMapping("cdpropriedade")
    public String cdpropriedade() {
        return "cd_propriedade";
    }

    @GetMapping("cdproduto")
    public String cdproduto() {
        return "cd_produto";
    }

    @PostMapping("cdpropriedade")
    public ResponseEntity cadastrarPropriedade(@RequestBody RegisterPropriedadeDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        propriedadeService.salvarPropriedade(dto, user);
        return ResponseEntity.ok().build();
    }

}
