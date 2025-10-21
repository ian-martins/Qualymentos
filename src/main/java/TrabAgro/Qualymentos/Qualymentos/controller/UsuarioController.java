package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.grao.RegisterGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.PropriedadeResponseDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.transporte.TransporteRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.GraoService;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import TrabAgro.Qualymentos.Qualymentos.service.TransporteService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
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
    public String dashboard(Authentication authentication, Model model) {
        // Obtém o usuário logado
        Usuario user = (Usuario) authentication.getPrincipal();

        // Adiciona os dados ao Model (para usar no Thymeleaf)
        model.addAttribute("usuario", user);

        // Retorna o nome da página HTML (ex: templates/usuario/home.html)
        return "usuario/usuario_home";
    }

    @GetMapping("/propriedades")
    public String listarPropriedadesUsuario(Authentication authentication, Model model) {
        Usuario user = (Usuario) authentication.getPrincipal();

        List<PropriedadeResponseDTO> propriedades = propriedadeService.listarPorUsuario(user.getId())
                .stream()
                .map(PropriedadeResponseDTO::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("usuario", user);
        model.addAttribute("propriedades", propriedades);

        return "usuario/usuario_home_propriedades";
    }
    
    @GetMapping("/transportes")
    public String listarTransportesUsuario(Authentication authentication, Model model) {
        Usuario user = (Usuario) authentication.getPrincipal();
        List<TransporteRequestDTO> transportes = transService.getAllt(user).stream()
                .map(t -> new TransporteRequestDTO(t.getNome(), t.getCnpj()))
                .collect(Collectors.toList());

        model.addAttribute("usuario", user);
        model.addAttribute("propriedades", transportes);
        
        return "usuario/usuario_home_transportes";
    }

   

/*     @GetMapping("/{id}")
    public String detailsPropriedades(@PathVariable Long id, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        List<Grao> graos = graoService.getAllG(id);
        List<ResponseGraoDTO> response = graos.stream()
                .map(ResponseGraoDTO::fromEntity)
                .toList();

        model.addAttribute("graos", response);

        return "tela_detalhes_propriedade";

    } */
/* 
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

    } */

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
