package TrabAgro.Qualymentos.Qualymentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import TrabAgro.Qualymentos.Qualymentos.dto.user.LoginUserRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.user.RegisterUserRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.user.ResponseUserDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.infra.security.TokenService;
import TrabAgro.Qualymentos.Qualymentos.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @GetMapping
    public String login() {
        return "auth";
    }

    @GetMapping("/sucesso")
    public String sucesso_cadastro() {
        return "cadastro_sucesso";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginUserRequestDTO loginRequestDTO, HttpServletResponse response) {

        try {
            Usuario user = this.userRepository.findByEmail(loginRequestDTO.email()).orElseThrow();
            if (passwordEncoder.matches(loginRequestDTO.senha(), user.getSenha())) {
                String token = this.tokenService.generateToken(user);
                jakarta.servlet.http.Cookie jwtCookie = new jakarta.servlet.http.Cookie("jwt", token);
                jwtCookie.setHttpOnly(false);
                jwtCookie.setSecure(false); // Em produção, deve ser true para HTTPS
                jwtCookie.setPath("/");
                jwtCookie.setMaxAge(2 * 60 * 60);

                response.addCookie(jwtCookie);

                // Retorna também o nome + token no corpo (opcional)
                return ResponseEntity.ok(new ResponseUserDTO(user.getNome(), token));
            }

            return ResponseEntity.badRequest().body("Senha invalida");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Usuario Não existe");
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody RegisterUserRequestDTO registerRequestDTO) {
        var present = this.userRepository.findByEmail(registerRequestDTO.email());
        if (present.isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setSenha(passwordEncoder.encode(registerRequestDTO.senha()));
            usuario.setNome(registerRequestDTO.nome());
            usuario.setEmail(registerRequestDTO.email());
            this.userRepository.save(usuario);

            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseUserDTO(usuario.getNome(), token));
        } else {
            return ResponseEntity.badRequest().body("Usuario já cadastrado!");
        }
    }

}
