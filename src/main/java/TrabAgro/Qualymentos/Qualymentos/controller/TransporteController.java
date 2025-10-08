package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.transporte.RegisterTransDTO;
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
    public String telaTransporte(Model model, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        List<Transporte> transportes = transService.getAllt(usuario);
        List<Transporte> transporte = transportes.stream().toList();
        model.addAttribute("transportes", transporte);
        model.addAttribute("usuario", usuario);

        return "tela_menu_transportadora";
    }

    @GetMapping("/{id}")
    public String detailsTransporte(@PathVariable Long id, Model model) {
        Transporte transporte = transService.getById(id);
        model.addAttribute("transportes", transporte);

        return "tela_detalhes_transportadora";
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteTransporte(@PathVariable Long id) {
        transService.deleteTransporte(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity cadastrarTransporte(@RequestBody RegisterTransDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        transService.salvarTrans(dto, user);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity atualizarTransporte(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        transService.atualizarTransporte(id, campo, valor);
        return ResponseEntity.ok("Campo atualizado");
    }
}
