package TrabAgro.Qualymentos.Qualymentos.dto.propriedade;

import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;

public record ResponsePropriedadeDTO(
    Long id, 
    String nome, 
    String codigoRural, 
    String areaTotal, 
    String municipio,
    String estado, 
    String tipoSolo, 
    String tipoProducao, 
    String tipoCultura
    ) {

    public static ResponsePropriedadeDTO fromEntity(Propriedade newPropriedade) {
         return new ResponsePropriedadeDTO(
            newPropriedade.getId(),
            newPropriedade.getNome(),
            newPropriedade.getCodigoRural(),
            newPropriedade.getAreaTotal(),
            newPropriedade.getMunicipio(),
            newPropriedade.getEstado(),
            newPropriedade.getTipoSolo(),
            newPropriedade.getTipoProducao(),
            newPropriedade.getTipoCultura()
         );
    }

}
