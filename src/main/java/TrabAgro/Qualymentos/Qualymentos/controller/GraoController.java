package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.http.ResponseEntity;
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

import TrabAgro.Qualymentos.Qualymentos.dto.grao.RegisterGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.grao.UpdateGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.service.GraoService;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/propriedade/{id}/grao")
@AllArgsConstructor
public class GraoController {
    private final GraoService graoService;
    private final PropriedadeService propriedadeService;

    @GetMapping
    public String graoCadastro(@PathVariable Long id, Model model) {
        model.addAttribute("propriedade", propriedadeService.getById(id));
        model.addAttribute("dto", new RegisterGraoDTO(null, null, null));

        return "grao/grao_cadastro";
    }

    @GetMapping("/{idgrao}")
    public String menu_graos(@PathVariable("idgrao") Long idgrao, Model model) {
        Grao grao = graoService.getById(idgrao);
        model.addAttribute("grao", grao);
        return "tela_menu_grao";
    }

    @PostMapping("/add")
    public String adicionar_grao(@ModelAttribute RegisterGraoDTO dto, @PathVariable Long id) {
        Propriedade propriedade = propriedadeService.getById(id);
        graoService.salvarGrao(dto, propriedade);

        return "redirect:/propriedade/" + id;
    }

    @DeleteMapping("/{idgrao}")
    public ResponseEntity deletar_Grao(@PathVariable("idgrao") Long idgrao) {
        graoService.deleteById(idgrao);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idgrao}/editar")
    public ResponseEntity editar_grao(@PathVariable("idgrao") Long idgrao, @RequestBody UpdateGraoDTO dto) {
        graoService.updateGrao(idgrao, dto);
        return ResponseEntity.ok().build();
    }

}
