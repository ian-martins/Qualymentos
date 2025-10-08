package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.transporte.RegisterTransDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.TransporteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/transporte")
@RequiredArgsConstructor

public class TransporteController {
     private final TransporteService transService;

    @GetMapping
    public String telaTransporte() {
        return "tela_cadastro_transportadora";
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTransporte(@PathVariable Long id) {
        transService.deleteTransporte(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public String cadastrarTransporte(@RequestBody RegisterTransDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        transService.salvarTrans(dto, user );

        return "redirect:/user";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity atualizarTransporte(@PathVariable Long id, @RequestBody RegisterTransDTO dto) {
        transService.atualizarTransporte(id, dto);
        return ResponseEntity.ok().build();
    }

}
