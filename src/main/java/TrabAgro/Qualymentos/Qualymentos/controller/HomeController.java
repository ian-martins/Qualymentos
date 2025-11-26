package TrabAgro.Qualymentos.Qualymentos.controller;

import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Safra;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.service.PropriedadeService;
import TrabAgro.Qualymentos.Qualymentos.service.QRCodeGenerator;
import TrabAgro.Qualymentos.Qualymentos.service.SafraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class HomeController {
    private final SafraService safraService;
    private final PropriedadeService propriedadeService;
    private final QRCodeGenerator qr;

    @GetMapping
    public String ola() {
        return "public/home";
    }

    @GetMapping("/lote/{lote}")
    public String lote_dados(@PathVariable String lote, Model model) {
        Safra safra = safraService.getById(lote);
        Grao grao = safra.getGrao();
        Propriedade propriedade = grao.getPropriedade();
        Usuario usuario = propriedade.getUsuario();
        model.addAttribute("safra", safra);
        model.addAttribute("grao", grao);
        model.addAttribute("propriedade", propriedade);
        model.addAttribute("usuario", usuario);
        byte[] imagem = qr.gerarQrCode(lote);
        String base64 = Base64.getEncoder().encodeToString(imagem);
        model.addAttribute("qr", base64);

        return "public/lote";
    }

    @GetMapping(value = "/qrcode/{lote}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> qrCode(@PathVariable String lote) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr.gerarQrCode(lote));
    }
}
