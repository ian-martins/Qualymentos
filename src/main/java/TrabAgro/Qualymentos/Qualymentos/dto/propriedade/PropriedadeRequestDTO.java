package TrabAgro.Qualymentos.Qualymentos.dto.propriedade;

public record PropriedadeRequestDTO(
                String nome,
                String codigoRural,
                String areaTotal,
                String tipoSolo,
                String tipoProducao,
                String tipoCultura,
                String fone,
                Long cidadeId) {
}
