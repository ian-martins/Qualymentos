package TrabAgro.Qualymentos.Qualymentos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.RegisterPropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.dto.propriedade.ResponsePropriedadeDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.PropriedadeRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropriedadeService {
    private final PropriedadeRepository propriedadeRepository;
    private final UsuarioRepository usuarioRepository;

    public Optional<ResponsePropriedadeDTO> salvarPropriedade(RegisterPropriedadeDTO dto, Usuario userId) {
        Propriedade newPropriedade = new Propriedade();
        newPropriedade.setNome(dto.nome());
        newPropriedade.setCodigoRural(dto.codigoRural());
        newPropriedade.setAreaTotal(dto.areaTotal());
        newPropriedade.setMunicipio(dto.municipio());
        newPropriedade.setEstado(dto.estado());

        newPropriedade.setTipoSolo(dto.tipoSolo());
        newPropriedade.setTipoProducao(dto.tipoProducao());
        newPropriedade.setTipoCultura(dto.tipoCultura());

        newPropriedade.setUsuario(userId);
        propriedadeRepository.save(newPropriedade);

        return Optional.of(ResponsePropriedadeDTO.fromEntity(newPropriedade));
    }

    public boolean propriedadeValida(Propriedade propriedade) {
        if (!propriedadeRepository.existsById(propriedade.getId())) {
            throw new IllegalArgumentException("Propriedade com ID " + propriedade.getId() + " não encontrada.");
        }
        return true;
    }

    public List<Propriedade> getAllP(Usuario usuario) {
        return propriedadeRepository.findByUsuario(usuario);
    }

    public Propriedade getById(Long id) {
        return propriedadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Propriedade com ID " + id + " não encontrada."));
    }

}
