package TrabAgro.Qualymentos.Qualymentos.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QRCodeGenerator {

    public byte[] gerarQrCode(String lote) {
        try {
            String texto = "http://localhost:8080/lote/" + lote;
            int largura = 300;
            int altura = 300;

            BitMatrix matrix = new MultiFormatWriter()
                    .encode(texto, BarcodeFormat.QR_CODE, largura, altura);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", outputStream);

            return outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar QR Code", e);
        }
    }
}