package TrabAgro.Qualymentos.Qualymentos.dto.grao;

import java.time.LocalDate;

public record RegisterGraoDTO(
    String tipoGrao,
    String areaPlantada,
    LocalDate dataPlantio
) {
    
}
 