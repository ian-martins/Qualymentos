package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.PropriedadeResponseDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.transporte.TransporteResponseDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.user.UpdateUserRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.GraoService;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import TrabAgro.Qualymentos.Qualymentos.service.TransporteService;
import TrabAgro.Qualymentos.Qualymentos.service.UsuarioService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final PropriedadeService propriedadeService;
    private final TransporteService transService;
    private final GraoService graoService;
    private final UsuarioService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String user(Authentication authentication, Model model) {
        Usuario user = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", user);
        return "usuario/usuario_dados";
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        Usuario user = (Usuario) authentication.getPrincipal();
        model.addAttribute("usuario", user);
        return "usuario/usuario_home_dashboard";
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
        List<TransporteResponseDTO> transportes = transService.listarPorUsuario(user.getId())
                .stream()
                .map(TransporteResponseDTO::fromEntity)
                .collect(Collectors.toList());

        model.addAttribute("usuario", user);
        model.addAttribute("transportes", transportes);

        return "usuario/usuario_home_transportes";
    }

    @PutMapping
    public ResponseEntity mudarsenha(@RequestBody UpdateUserRequestDTO dto, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        if (passwordEncoder.matches(dto.senha(), user.getSenha())) {
            userService.altSenha(dto, user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/alt")
    public ResponseEntity mudarcampos(@RequestBody Map<String, String> dados, Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        String campo = dados.get("campo");
        String valor = dados.get("valor");

        userService.altcampos(campo, valor, user);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deletarConta(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();
        userService.deleteconta(user);
        return ResponseEntity.ok().build();
    }

}
