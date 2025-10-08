package TrabAgro.Qualymentos.Qualymentos.service;

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

    public void atualizarTransporte(Long id, RegisterTransDTO dto) {
        transRepository.findById(id).ifPresent(transporte -> {
            transporte.setNome(dto.nome());
            transporte.setCnpj(dto.cnpj());
            transRepository.save(transporte);
        });
    }

}
