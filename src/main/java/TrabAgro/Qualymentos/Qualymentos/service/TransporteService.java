package TrabAgro.Qualymentos.Qualymentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.transporte.RegisterTransDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.TransportadoraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransporteService {
    private final TransportadoraRepository transRepository;

    public void salvarTrans(RegisterTransDTO dto, Usuario user) {
        Transporte newTrans = new Transporte(null, dto.nome(), dto.cnpj(), user);
        transRepository.save(newTrans);
    }

    public void deleteTransporte(Long id) {
        transRepository.deleteById(id);
    }

    public void atualizarTransporte(Long id, String campo, String valor) {
        Transporte updateTrans = getById(id);
        switch (campo) {
            case "nome" -> updateTrans.setNome(valor);
            case "cnpj" -> updateTrans.setCnpj(valor);
        }
        transRepository.save(updateTrans);
    }

    public List<Transporte> getAllt(Usuario usuario) {
        return transRepository.findByUsuario(usuario);
    }

    public Transporte getById(Long id) {
        return transRepository.findById(id).orElseThrow(() -> new RuntimeException("Transportadora n��o encontrada"));
    }

}
