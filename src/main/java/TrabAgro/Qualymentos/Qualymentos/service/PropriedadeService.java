package TrabAgro.Qualymentos.Qualymentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.GraoRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.PropriedadeRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PropriedadeService {
    private final PropriedadeRepository propriedadeRepository;
    private final GraoRepository graoRepository;
    private final UsuarioRepository usuarioRepository;

    public void salvarPropriedade(Propriedade propriedade) {
       propriedadeRepository.save(propriedade);
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

    public void deletePropriedade(Long id) {
        propriedadeRepository.deleteById(id);
    }

    public void atualizarCampo(Long id, String campo, String valor) {
    Propriedade prop = getById(id);
    switch (campo) {
        case "nome" -> prop.setNome(valor);
        case "fone" -> prop.setFone(valor);
        case "codigoRural" -> prop.setCodigoRural(valor);
        case "areaTotal" -> prop.setAreaTotal(valor);
        case "municipio" -> prop.setMunicipio(valor);
        case "estado" -> prop.setEstado(valor);
        case "tipoSolo" -> prop.setTipoSolo(valor);
        case "tipoProducao" -> prop.setTipoProducao(valor);
        case "tipoCultura" -> prop.setTipoCultura(valor);
    }
    propriedadeRepository.save(prop);
}
}
