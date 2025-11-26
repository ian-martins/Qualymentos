package TrabAgro.Qualymentos.Qualymentos.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import TrabAgro.Qualymentos.Qualymentos.dto.safra.RegisterSafraDTO;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Safra;
import TrabAgro.Qualymentos.Qualymentos.entity.Transporte;
import TrabAgro.Qualymentos.Qualymentos.repository.GraoRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.SafraRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.TransportadoraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SafraService {
    private final SafraRepository safraRepository;
    private final PropriedadeService propriedadeService;
    private final GraoRepository graoRepository;
    private final TransportadoraRepository transporteRepository;


    public void salvarSafra(RegisterSafraDTO dto, Long idPropriedade) {
        Grao grao = graoRepository.getById(dto.graoId());
        Propriedade propriedade = propriedadeService.getById(idPropriedade);

        Safra safra = new Safra(null, dto.dataPlantio(), dto.dataColheita(), dto.quantidadeColhida(), dto.qualidade(),
                grao, propriedade, null);
        safraRepository.save(safra);
    }

    public Safra getById(String idsafra) {
        safraRepository.findById(idsafra).orElseThrow(() -> new RuntimeException("Safra n��o encontrada"));
        return safraRepository.getById(idsafra);
    }

    public void deletarSafra(String idsafra) {
        safraRepository.deleteById(idsafra);
    }

    public void atualizarCampo(String id, String campo, String valor) {
        Safra safra = getById(id);

        switch (campo) {

            case "dataPlantio" -> {
                LocalDate date = LocalDate.parse(valor);
                safra.setDataPlantio(date);
            }

            case "dataColheita" -> {
                LocalDate date = LocalDate.parse(valor);
                safra.setDataColheita(date);
            }

            case "quantidadeColhida" -> safra.setQuantidadeColhida(valor);
            case "qualidade" -> safra.setQualidade(valor);

            case "grao" -> {
                Long graoId = Long.parseLong(valor);
                Grao grao = graoRepository.getById(graoId);
                safra.setGrao(grao);
            }
            case "transporte" -> {
                Long transporteId = Long.parseLong(valor);
                Transporte transporte = transporteRepository.getById(transporteId); 
                safra.setTransporte(transporte);;
            }
            default -> throw new RuntimeException("Campo inválido: " + campo);
        }

        safraRepository.save(safra);
    }

    public String gerarQrCode(String lote){
        try {
            String texto = "http://localhost:8080/lote/" + lote; // conteúdo do QR
            String caminho = "qrcode.png";        // arquivo a ser gerado
            int largura = 300;
            int altura = 300;

            BitMatrix matrix = new MultiFormatWriter()
                    .encode(texto, BarcodeFormat.QR_CODE, largura, altura);

            Path path = FileSystems.getDefault().getPath(caminho);
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);

            System.out.println("QR Code gerado em: " + caminho);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
