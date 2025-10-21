package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.grao.RegisterGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.grao.ResponseGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.ResponsePropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.GraoService;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import TrabAgro.Qualymentos.Qualymentos.service.TransporteService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final PropriedadeService propriedadeService;
    private final TransporteService transService;
    private final GraoService graoService;

    @GetMapping
    public String listarPropriedadesUsuario(Authentication authentication, Model model) {
        // Obtém o usuário logado
        Usuario user = (Usuario) authentication.getPrincipal();

        // Busca as propriedades do usuário
        List<ResponsePropriedadeDTO> propriedades = propriedadeService.listarPorUsuario(user.getId())
                .stream()
                .map(ResponsePropriedadeDTO::fromEntity)
                .collect(Collectors.toList());

        // Adiciona os dados ao Model (para usar no Thymeleaf)
        model.addAttribute("usuario", user);
        model.addAttribute("propriedades", propriedades);

        // Retorna o nome da página HTML (ex: templates/propriedade/listar.html)
        return "usuario_home";
    }

   

    @GetMapping("/{id}")
    public String detailsPropriedades(@PathVariable Long id, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        List<Grao> graos = graoService.getAllG(id);
        List<ResponseGraoDTO> response = graos.stream()
                .map(ResponseGraoDTO::fromEntity)
                .toList();

        model.addAttribute("graos", response);

        return "tela_detalhes_propriedade";

    }

    @GetMapping("/{id}/T")
    public String detailsPropriedadesT(@PathVariable Long id, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        List<Grao> graos = graoService.getAllG(id);
        List<ResponseGraoDTO> response = graos.stream()
                .map(ResponseGraoDTO::fromEntity)
                .toList();

        model.addAttribute("graos", response);

        return "teste";

    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deletePropriedade(@PathVariable Long id) {
        propriedadeService.deletePropriedade(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/AddGrao")
    public String tela_cadastro_cultivo(@PathVariable Long id, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        return "tela_cadastro_cultivo";
    }

    @PostMapping("{id}/AddGrao")
    public ResponseEntity addGrao(@RequestBody RegisterGraoDTO dto, @PathVariable Long id) {
        Propriedade propriedade = propriedadeService.getById(id);
        graoService.salvarGrao(dto, propriedade);

        return ResponseEntity.ok().build();
    }

    @GetMapping("cdpropriedade")
    public String cdpropriedade() {
        return "tela_cadastro_propriedade";
    }

}
