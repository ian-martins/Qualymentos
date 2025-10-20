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
import org.springframework.web.bind.annotation.PathVariable;

import TrabAgro.Qualymentos.Qualymentos.dto.grao.ResponseGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.RegisterPropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.ResponsePropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
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

    @GetMapping
    public String propriedadeCadastro(Model model, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<Propriedade> propriedades = propriedadeService.getAllP(usuario);
        List<ResponsePropriedadeDTO> response = propriedades.stream()
                .map(ResponsePropriedadeDTO::fromEntity)
                .toList();

        model.addAttribute("propriedades", response);
        model.addAttribute("usuario", usuario);
        return "propriedade_cadastro";
    }

    @GetMapping("/{id}")
    public String propriedadeDetalhes(@PathVariable Long id, Authentication authentication, Model model) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", usuario);

        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);

        List<Grao> graos = graoService.getAllG(id);
        List<ResponseGraoDTO> response = graos.stream()
                .map(ResponseGraoDTO::fromEntity)
                .toList();
        model.addAttribute("graos", response);
        return "propriedade_detalhes";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePropriedade(@PathVariable Long id) {
        propriedadeService.deletePropriedade(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/{id}/see")
    public String detailsPropriedades(@PathVariable Long id, Model model) {
        Propriedade propriedade = propriedadeService.getById(id);
        model.addAttribute("propriedade", propriedade);
        Propriedade prop = propriedadeService.getById(id);
        model.addAttribute("propriedades", prop);
        List<Grao> graos = graoService.getAllG(id);
        List<ResponseGraoDTO> response = graos.stream()
                .map(ResponseGraoDTO::fromEntity)
                .toList();

        model.addAttribute("graos", response);

        return "tela_detalhes_propriedade";
    }

    

    @PostMapping("/add")
    public ResponseEntity cadastrarPropriedade(@RequestBody RegisterPropriedadeDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        Propriedade newPropriedade = new Propriedade(null, dto.nome(), dto.codigoRural(), dto.areaTotal(),
                dto.municipio(), dto.estado(), dto.tipoSolo(), dto.tipoProducao(), dto.tipoCultura(),null, user, null);
        propriedadeService.salvarPropriedade(newPropriedade);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarCampo(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        propriedadeService.atualizarCampo(id, campo, valor);
        return ResponseEntity.ok("Campo atualizado");
    }

}
