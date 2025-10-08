package TrabAgro.Qualymentos.Qualymentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.grao.UpdateGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.grao.RegisterGraoDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.repository.GraoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GraoService {
    private final GraoRepository graoRepository;
    private final PropriedadeService propriedadeService;

    public void salvarGrao(RegisterGraoDTO dto, Propriedade propriedade) {
        Grao newGrao = new Grao(null, dto.tipoGrao(), dto.dataPlantio(), null, dto.areaPlantada(), null, propriedade);
        graoRepository.save(newGrao);
    }

    public List<Grao> getAllG(Long id) {
        Propriedade propriedade = propriedadeService.getById(id);
        return graoRepository.findByPropriedade(propriedade);
    }

    public void deleteById(Long id) {
        graoRepository.deleteById(id);
    }

    public void updateGrao(UpdateGraoDTO dto, Long id){
        Grao updateGrao  = new Grao(id, null, null, dto.dataColheita(), null, dto.produtividade(), null );
        graoRepository.save(updateGrao);
    }

}
