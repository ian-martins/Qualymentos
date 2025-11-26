package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

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

    @GetMapping
    public String user(Authentication authentication, Model model){
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
    public String mudarsenha(@RequestBody UpdateUserRequestDTO dto, Authentication authentication){
        Usuario user = (Usuario) authentication.getPrincipal();
        
        userService.altSenha(dto, user);
        return null;
    }
}
