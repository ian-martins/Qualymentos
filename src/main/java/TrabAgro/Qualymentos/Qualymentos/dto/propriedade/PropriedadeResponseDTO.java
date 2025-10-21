package TrabAgro.Qualymentos.Qualymentos.dto.propriedade;

import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;

public record PropriedadeResponseDTO(
        Long id,
        String nome,
        String codigoRural,
        String areaTotal,
        String cidade,
        String estado,
        String tipoSolo,
        String tipoProducao,
        String tipoCultura,
        String fone
) {

    public static PropriedadeResponseDTO fromEntity(Propriedade propriedade) {
        String nomeCidade = propriedade.getCidade() != null ? propriedade.getCidade().getNome() : null;
        String nomeEstado = (propriedade.getCidade() != null && propriedade.getCidade().getEstado() != null)
                ? propriedade.getCidade().getEstado().getSigla()
                : null;

        return new PropriedadeResponseDTO(
                propriedade.getId(),
                propriedade.getNome(),
                propriedade.getCodigoRural(),
                propriedade.getAreaTotal(),
                nomeCidade,
                nomeEstado,
                propriedade.getTipoSolo(),
                propriedade.getTipoProducao(),
                propriedade.getTipoCultura(),
                propriedade.getFone()
        );
    }
}
