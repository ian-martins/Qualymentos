package TrabAgro.Qualymentos.Qualymentos.dto.grao;

import java.time.LocalDate;

import TrabAgro.Qualymentos.Qualymentos.entity.Grao;

public record ResponseGraoDTO(
        Long id,
        String tipoGrao,
        String areaPlantada,
        LocalDate dataPlantio
        ) {
    public static ResponseGraoDTO fromEntity(Grao newGrao) {
        return new ResponseGraoDTO(
                newGrao.getId(),
                newGrao.getTipoGrao(),
                newGrao.getAreaPlantada(),
                newGrao.getDataPlantio()
                );  
    }

}
