package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.transporte.TransporteRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.TransporteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/transporte")
@RequiredArgsConstructor

public class TransporteController {
    private final TransporteService transService;

    @GetMapping
    public String telaTransporte(Model model,Authentication authentication) {
        model.addAttribute("dto", new TransporteRequestDTO("", ""));
        return "transporte/transporte_cadastro";
    }
 
    @GetMapping("/{id}")
    public String detailsTransporte(@PathVariable Long id,Authentication authentication, Model model) {
           Usuario usuario = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);
        
        Transporte transporte = transService.getById(id);


        model.addAttribute("transportes", transporte);

        return "transporte/transporte_detalhes";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTransporte(@PathVariable Long id) {
        transService.deleteTransporte(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public String cadastrarTransporte (@ModelAttribute TransporteRequestDTO dto,
            Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        transService.salvarTrans(dto, user);

        return "redirect:/usuario/transportes";
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarTransporte(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        transService.atualizarTransporte(id, campo, valor);
        return ResponseEntity.ok("Campo atualizado");
    }
}
