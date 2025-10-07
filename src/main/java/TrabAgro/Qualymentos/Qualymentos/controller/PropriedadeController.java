package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.RegisterPropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/propriedade")
@RequiredArgsConstructor

public class PropriedadeController {
    private final PropriedadeService propriedadeService;

    @GetMapping
    public String telaPropriedades() {
        return "propriedades";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePropriedade(@PathVariable Long id) {
        propriedadeService.deletePropriedade(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public String cadastrarPropriedade(@RequestBody RegisterPropriedadeDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Propriedade newPropriedade = new Propriedade(null, dto.nome(), dto.codigoRural(), dto.areaTotal(), dto.municipio(), dto.estado(), dto.tipoSolo(), dto.tipoProducao(), dto.tipoCultura(), user, null);
        propriedadeService.salvarPropriedade(newPropriedade);
       
        return "redirect:/user";
    }

}
