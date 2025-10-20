package TrabAgro.Qualymentos.Qualymentos.infra.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import TrabAgro.Qualymentos.Qualymentos.entity.Cidade;
import TrabAgro.Qualymentos.Qualymentos.entity.Estado;
import TrabAgro.Qualymentos.Qualymentos.repository.CidadeRepository;
import TrabAgro.Qualymentos.Qualymentos.repository.EstadoRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarDados(EstadoRepository estadoRepo, CidadeRepository cidadeRepo) {
        return args -> {
            if (estadoRepo.count() == 0) {

                // --- ESTADOS (apenas exemplo resumido, mas pode ser todos) ---
                Estado sp = new Estado(null, "São Paulo", "SP", null);
                Estado rj = new Estado(null, "Rio de Janeiro", "RJ", null);
                Estado mg = new Estado(null, "Minas Gerais", "MG", null);

                estadoRepo.saveAll(Arrays.asList(sp, rj, mg));

                // --- CIDADES ---
                List<Cidade> cidades = Arrays.asList(
                        new Cidade(null, "São Paulo", sp),
                        new Cidade(null, "Campinas", sp),
                        new Cidade(null, "Santos", sp),
                        new Cidade(null, "Rio de Janeiro", rj),
                        new Cidade(null, "Niterói", rj),
                        new Cidade(null, "Belo Horizonte", mg),
                        new Cidade(null, "Uberlândia", mg)
                );

                cidadeRepo.saveAll(cidades);

                System.out.println("✅ Estados e cidades inseridos com sucesso!");
            } else {
                System.out.println("📍 Dados de estados e cidades já existem, pulando carga inicial...");
            }
        };
    }
}