package TrabAgro.Qualymentos.Qualymentos.dto.transporte;

import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;

public record TransporteResponseDTO(
    Long id,
    String nome,
    String cnpj
) {
     public static TransporteResponseDTO fromEntity(Transporte transporte) {
        return new TransporteResponseDTO(   
            transporte.getId(),
            transporte.getNome(),
            transporte.getCnpj()
        );
    }
}
