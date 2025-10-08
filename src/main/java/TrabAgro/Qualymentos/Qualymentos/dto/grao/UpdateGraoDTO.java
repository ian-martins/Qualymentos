package TrabAgro.Qualymentos.Qualymentos.dto.grao;

import java.time.LocalDate;

public record UpdateGraoDTO(
    LocalDate dataColheita,
    String produtividade
) {
    
}
 