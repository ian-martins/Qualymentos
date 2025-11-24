package TrabAgro.Qualymentos.Qualymentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
        Grao newGrao = new Grao(null, dto.tipoGrao(), dto.subtipoGrao(), propriedade);
        graoRepository.save(newGrao);
    }

    public List<Grao> getAllG(Long id) {
        Propriedade propriedade = propriedadeService.getById(id);
        return graoRepository.findByPropriedade(propriedade);
    }

    public void deleteById(Long id) {
        graoRepository.deleteById(id);
    }

    public Grao getById(Long idgrao) {
        return graoRepository.findById(idgrao).orElseThrow(() -> new RuntimeException("Grão não encontrado"));
    }
/* 
    public void atualizarCampo(Long id, String campo, String valor) {
        Grao grao = getById(id);

        LocalDate data = null;

        if ("dataPlantio".equals(campo) || "dataColheita".equals(campo)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            data = LocalDate.parse(valor, formatter);
        }

        switch (campo) {
            case "tipoGrao" -> grao.setTipoGrao(valor);
            case "areaPlantada" -> grao.setAreaPlantada(valor);
            case "dataPlantio" -> grao.setDataPlantio(data);
            case "dataColheita" -> grao.setDataColheita(data);
            case "produtividade" -> grao.setProdutividade(valor);
            default -> throw new RuntimeException("Campo inválido: " + campo);
        }

        graoRepository.save(grao);
    }
 */
    public void atualizarCampo(Long id, String campo, String valor) {
        Grao grao = getById(id);

        switch (campo) {
            case "tipoGrao" -> grao.setTipoGrao(valor);
            case "subtipoGrao" -> grao.setSubtipoGrao(valor);
        }

        graoRepository.save(grao);
    }


}
