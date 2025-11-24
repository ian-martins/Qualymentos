package TrabAgro.Qualymentos.Qualymentos.dto.grao;

import TrabAgro.Qualymentos.Qualymentos.entity.Grao;

public record ResponseGraoDTO(
        Long id,
        String tipoGrao,
        String subtipoGrao
        ) {
    public static ResponseGraoDTO fromEntity(Grao newGrao) {
        return new ResponseGraoDTO(
                newGrao.getId(),
                newGrao.getTipoGrao(),
                newGrao.getSubtipoGrao()
                );  
    }

}
