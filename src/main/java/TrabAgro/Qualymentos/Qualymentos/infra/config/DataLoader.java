package TrabAgro.Qualymentos.Qualymentos.infra.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import TrabAgro.Qualymentos.Qualymentos.entity.Cidade;
import TrabAgro.Qualymentos.Qualymentos.entity.Estado;
import TrabAgro.Qualymentos.Qualymentos.entity.Grao;
import TrabAgro.Qualymentos.Qualymentos.entity.Propriedade;
import TrabAgro.Qualymentos.Qualymentos.entity.Safra;
import TrabAgro.Qualymentos.Qualymentos.entity.Usuario;
import TrabAgro.Qualymentos.Qualymentos.repository.CidadeRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.EstadoRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.GraoRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.PropriedadeRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.SafraRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.TransportadoraRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner addEstadoCidade(EstadoRepository estadoRepo, CidadeRepository cidadeRepo) {
        return args -> {
            if (estadoRepo.count() == 0) {
                Estado sp = new Estado(null, "São Paulo", "SP", null);
                Estado rj = new Estado(null, "Rio de Janeiro", "RJ", null);
                Estado mg = new Estado(null, "Minas Gerais", "MG", null);
                estadoRepo.saveAll(Arrays.asList(sp, rj, mg));
                List<Cidade> cidades = Arrays.asList(new Cidade(null, "São Paulo", sp),
                        new Cidade(null, "Campinas", sp), new Cidade(null, "Santos", sp),
                        new Cidade(null, "Rio de Janeiro", rj), new Cidade(null, "Niterói", rj),
                        new Cidade(null, "Belo Horizonte", mg), new Cidade(null, "Uberlândia", mg),
                        new Cidade(null, "Juiz de Fora", mg), new Cidade(null, "Ourinhos", sp));
                cidadeRepo.saveAll(cidades);
                System.out.println("✅ Estados e cidades inseridos com sucesso!");
            } else {
                System.out.println("📍 Dados de estados e cidades já existem, pulando carga inicial...\n");
            }
        };
    }

    @Bean
    public CommandLineRunner adminLoad(
            UsuarioRepository usuarioRepo,
            PropriedadeRepository propriedadeRepo,
            TransportadoraRepository transporteRepo,
            GraoRepository graoRepo,
            EstadoRepository estadoRepo,
            CidadeRepository cidadeRepo,
            PasswordEncoder passwordEncoder,
            SafraRepository safraRepository) {
        System.out.println("DataLoader iniciado...");
        return args -> {

            String adminEmail = "admin@system.com";
            String adminPass = passwordEncoder.encode("123");

            Optional<Usuario> adminOpt = usuarioRepo.findByEmail(adminEmail);

            if (adminOpt.isPresent()) {
                if (adminOpt.isPresent()) {
                    usuarioRepo.delete(adminOpt.get());
                    System.out.println("🧹 Admin antigo removido com CASCADE.");
                }
            }

            Usuario admin = new Usuario(null, "Administrador do Sistema", adminEmail, adminPass, null, null);
            usuarioRepo.save(admin);
            System.out.println("👑 Novo ADMIN criado.");

            Propriedade p1 = new Propriedade(
                    null,
                    "Fazenda Admin",
                    "CR001",
                    "100",
                    "Latossolo",
                    "Agricultura",
                    "Soja",
                    "1199999999",
                    cidadeRepo.getById((long) 1),
                    admin,
                    new ArrayList<>(),
                    new ArrayList<>());
            propriedadeRepo.save(p1);
            System.out.println("🏡 Propriedade do Admin criada.");

            Grao g1 = new Grao(
                    null,
                    "Soja",
                    "tipo_1",
                    p1,
                    null);
            Grao g2 = new Grao(
                    null,
                    "Soja",
                    "tipo_2",
                    p1,
                    null);
            graoRepo.save(g1);
            graoRepo.save(g2);
            System.out.println("🌱 Grão plantado na propriedade do Admin criado.");

            
            LocalDateTime now = LocalDateTime.now();
            
            LocalDate pDate = now.toLocalDate();
            LocalDate cDate = now.toLocalDate();

            Safra safra1 = new Safra(null, pDate, cDate, "500", "5", g1, p1);
            Safra safra2 = new Safra(null, pDate, cDate, "600", "6", g1, p1);

            safraRepository.save(safra1);
            safraRepository.save(safra2);

        };
    }
}

/*
 * usuario tem n propriedades
 * propriedade tem 1 usuario
 * propriedade tem 1 cidade
 * propriedade tem n graos
 * grao tem 1 propriedade
 * 
 * atributos de usuario
 * String id
 * String nome
 * String email
 * String senha
 * 
 * atributos de propriedade
 * 
 * Long id
 * String nome
 * String codigoRural
 * String areaTotal
 * String tipoSolo
 * String tipoProducao
 * String tipoCultura
 * String fone
 * Cidade cidade
 * Usuario usuario
 * List<Grao> graos
 * 
 * atributos de grao
 * Long id
 * String tipoGrao
 * LocalDate dataPlantio
 * LocalDate dataColheita
 * String areaPlantada
 * String produtividade
 * Propriedade propriedade
 */