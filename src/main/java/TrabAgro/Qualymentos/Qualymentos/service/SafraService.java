package TrabAgro.Qualymentos.Qualymentos.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.safra.RegisterSafraDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Safra;
import TrabAgro.Qualymentos.Qualymentos.repository.GraoRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.SafraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SafraService {
    private final SafraRepository safraRepository;
    private final PropriedadeService propriedadeService;
    private final GraoRepository graoRepository;

    public void salvarSafra(RegisterSafraDTO dto, Long idPropriedade) {
        Grao grao = graoRepository.getById(dto.graoId());
        Propriedade propriedade = propriedadeService.getById(idPropriedade);
        Safra safra = new Safra(null, dto.dataPlantio(), dto.dataColheita(), dto.quantidadeColhida(), dto.qualidade(),
                grao, propriedade);
        safraRepository.save(safra);
    }

    public Safra getById(String idsafra) {
        safraRepository.findById(idsafra).orElseThrow(() -> new RuntimeException("Safra n��o encontrada"));
        return safraRepository.getById(idsafra);
    }

    public void deletarSafra(String idsafra) {
        safraRepository.deleteById(idsafra);
    }

    public void atualizarCampo(String id, String campo, String valor) {
        Safra safra = getById(id);

        switch (campo) {

            case "dataPlantio" -> {
                LocalDate date = LocalDate.parse(valor);
                safra.setDataPlantio(date);
            }

            case "dataColheita" -> {
                LocalDate date = LocalDate.parse(valor);
                safra.setDataColheita(date);
            }

            case "quantidadeColhida" -> safra.setQuantidadeColhida(valor);

            case "qualidade" -> safra.setQualidade(valor);

            case "grao" -> {
                Long graoId = Long.parseLong(valor);
                Grao grao = graoRepository.getById(graoId);
                safra.setGrao(grao);
            }

            default -> throw new RuntimeException("Campo inválido: " + campo);
        }

        safraRepository.save(safra);
    }

}
