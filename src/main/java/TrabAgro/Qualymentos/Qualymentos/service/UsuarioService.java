package TrabAgro.Qualymentos.Qualymentos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.user.UpdateUserRequestDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository uRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean altSenha(UpdateUserRequestDTO dto, Usuario user) {
        if (dto.senhan() == dto.senhav() && passwordEncoder.matches(dto.senha(), user.getSenha())) {
            user.setSenha(dto.senhan());
            uRepository.save(user);
            return true;
        }
        return false;
    }
}
