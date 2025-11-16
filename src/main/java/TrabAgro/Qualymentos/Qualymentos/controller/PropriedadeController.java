package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.PropriedadeRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Cidade;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.CidadeRepository;
import TrabAgro.Qualymentos.Qualymentos.service.GraoService;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/propriedade")
@RequiredArgsConstructor

public class PropriedadeController {
    private final PropriedadeService propriedadeService;
    private final GraoService graoService;
    private final CidadeRepository cidadeRepository;

    @GetMapping
    public String propriedadeCadastro(Model model) {
        model.addAttribute("dto", new PropriedadeRequestDTO("", "", "", "", "", "", "", null));
        model.addAttribute("cidades", cidadeRepository.findAll());
        return "propriedade/propriedade_cadastro";
    }

    @GetMapping("/{id}")
    public String propriedadeDetalhes(@PathVariable Long id, Authentication authentication, Model model) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);

        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        List<Cidade> cidades = cidadeRepository.findAll();
        model.addAttribute("cidades", cidades);

        model.addAttribute("grao", graoService.getAllG(id));


        /*
         * List<Grao> graos = graoService.getAllG(id);
         * List<ResponseGraoDTO> response = graos.stream()
         * .map(ResponseGraoDTO::fromEntity)
         * .toList();
         * model.addAttribute("graos", response);
         */
        return "propriedade/propriedade_detalhes";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePropriedade(@PathVariable Long id) {
        propriedadeService.deletePropriedade(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public String cadastrarPropriedade(@ModelAttribute PropriedadeRequestDTO dto,
            Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        propriedadeService.salvarPropriedade(dto, usuario);

        return "redirect:/usuario/propriedades";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarCampo(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        propriedadeService.atualizarCampo(id, campo, valor);
        return ResponseEntity.ok("Campo atualizado");
    }

}
