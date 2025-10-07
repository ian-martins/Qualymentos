package TrabAgro.Qualymentos.Qualymentos.service;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.transporte.RegisterTransDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Transportadora;
import TrabAgro.Qualymentos.Qualymentos.repository.TransportadoraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransportadoraService {
    private final TransportadoraRepository transRepository;

    public void salvarTrans(RegisterTransDTO dto) {
        Transportadora newTrans = new Transportadora(null, dto.nome(), dto.cnpj());
        transRepository.save(newTrans);
    }

}
