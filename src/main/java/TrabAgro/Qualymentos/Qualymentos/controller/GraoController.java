package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.grao.RegisterGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.grao.UpdateGraoDTO;
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

    @GetMapping("/{idgrao}")
    public String menu_graos(@PathVariable("id") Long id, @PathVariable("idgrao") Long idgrao, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);
        return "tela_menu_grao";
    }

    @PostMapping("/add")
    public ResponseEntity adicionar_grao(@RequestBody RegisterGraoDTO dto, @PathVariable Long id) {
        Propriedade propriedade = propriedadeService.getById(id);
        graoService.salvarGrao(dto, propriedade);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idgrao}")
    public ResponseEntity deletar_Grao(@PathVariable("idgrao") Long idgrao){
        graoService.deleteById(idgrao);
    }

    @PutMapping("/{idgrao}/editar")
    public ResponseEntity editar_grao(@PathVariable("idgrao") Long idgrao, @RequestBody UpdateGraoDTO dto){
        graoService.updateGrao(idgrao, dto);
    }


}
