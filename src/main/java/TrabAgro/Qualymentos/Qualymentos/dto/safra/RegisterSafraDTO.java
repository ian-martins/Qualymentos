package TrabAgro.Qualymentos.Qualymentos.dto.safra;

import java.time.LocalDate;

public record RegisterSafraDTO(
    LocalDate dataPlantio,
    LocalDate dataColheita,
    String quantidadeColhida,
    String qualidade,
    Long graoId
    ) {

}
