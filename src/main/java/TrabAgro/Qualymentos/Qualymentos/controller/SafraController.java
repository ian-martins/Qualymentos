package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import TrabAgro.Qualymentos.Qualymentos.dto.safra.RegisterSafraDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Safra;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import TrabAgro.Qualymentos.Qualymentos.service.SafraService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@RequestMapping("/propriedade/{id}/safra")
public class SafraController {
    private final SafraService safraService;
    private final PropriedadeService propriedadeService;

    @GetMapping
    public String cadastroSafra(@PathVariable Long id, Model model) {
        model.addAttribute("propriedade", propriedadeService.getById(id));
        model.addAttribute("dto", new RegisterSafraDTO(null, null, null, null, null));
        model.addAttribute("graos", propriedadeService.getById(id).getGraos());

        return "safra/safra_cadastro";
    }

    @PostMapping("/add")
    public String adicionar_safra(@ModelAttribute RegisterSafraDTO dto, @PathVariable Long id) {
        safraService.salvarSafra(dto, id);
        return "redirect:/propriedade/{id}";
    }

    @GetMapping("/{idsafra}")
    public String menu_safra(@PathVariable("id") Long id, @PathVariable("idsafra") String idsafra,
            Authentication authentication, Model model) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);

        model.addAttribute("graos", propriedadeService.getById(id).getGraos());

        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        Safra safra = safraService.getById(idsafra);
        model.addAttribute("safra", safra);

        return "safra/safra_detalhes";
    }

    @DeleteMapping("/{idsafra}")
    public ResponseEntity deletar_safra(@PathVariable("id") Long id, @PathVariable("idsafra") String idsafra) {
        System.out.println("Deletando safra com ID: " + idsafra + " da propriedade com ID: " + id);
        safraService.deletarSafra(idsafra);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idsafra}")
    public ResponseEntity atualizar_safra(@PathVariable("id") Long id, @PathVariable("idsafra") String idsafra,
            @RequestBody Map<String, String> dados) {
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        if(campo.equals("dataPlantio") || campo.equals("dataColheita")) {
            
        }

        safraService.atualizarCampo(idsafra, campo,  valor);
        return ResponseEntity.ok().build();
    }
}
