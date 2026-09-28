package br.edu.ifpb.es.pweb3.seeds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Endereco;
import br.edu.ifpb.es.pweb3.models.GuiaEspeleologia;
import br.edu.ifpb.es.pweb3.models.enums.NivelCertificacao;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoAtiva;
import jakarta.persistence.EntityManager;

public final class GuiaEspeleologiaSeed {

    private GuiaEspeleologiaSeed() {}

    public static void carregar(EntityManager em) {

        List<GuiaEspeleologia> guias = new ArrayList<>();

        guias.add(
            GuiaEspeleologia.builder()
                .nome("João Pedro Almeida")
                .cpf("67890123457")
                .dataNascimento(LocalDate.of(1982, 2, 10))
                .email("joao.almeida@espeleologia.org")
                .telefone("(83) 94444-6006")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua das Palmeiras",
                    120,
                    null,
                    "Manaíra",
                    "João Pessoa",
                    "PB",
                    "58038000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numCredenciamento(10001L)
                .nivelCertificacao(NivelCertificacao.AVANCADO)
                .dataValidadeCertificacao(LocalDate.of(2028, 12, 31))
                .qtdExpedicoesConcluidas(47)
                .build()
        );

        guias.add(
            GuiaEspeleologia.builder()
                .nome("Mariana Costa Santos")
                .cpf("78901234568")
                .dataNascimento(LocalDate.of(1991, 6, 22))
                .email("mariana.santos@espeleologia.org")
                .telefone("(83) 93333-7007")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua João Machado",
                    321,
                    "Apto 301",
                    "Centro",
                    "João Pessoa",
                    "PB",
                    "58013000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numCredenciamento(10002L)
                .nivelCertificacao(NivelCertificacao.INTERMEDIARIO)
                .dataValidadeCertificacao(LocalDate.of(2027, 8, 15))
                .qtdExpedicoesConcluidas(23)
                .build()
        );

        guias.add(
            GuiaEspeleologia.builder()
                .nome("Felipe Rodrigues")
                .cpf("89012345679")
                .dataNascimento(LocalDate.of(1975, 10, 5))
                .email("felipe.rodrigues@espeleologia.org")
                .telefone("(84) 92222-8008")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Avenida Hermes da Fonseca",
                    950,
                    null,
                    "Petrópolis",
                    "Natal",
                    "RN",
                    "59020000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numCredenciamento(10003L)
                .nivelCertificacao(NivelCertificacao.EXPERT)
                .dataValidadeCertificacao(LocalDate.of(2030, 3, 20))
                .qtdExpedicoesConcluidas(86)
                .build()
        );

        guias.add(
            GuiaEspeleologia.builder()
                .nome("Camila Martins")
                .cpf("90123456780")
                .dataNascimento(LocalDate.of(1995, 12, 3))
                .email("camila.martins@espeleologia.org")
                .telefone("(83) 91111-9009")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua Bancário Sérgio Guerra",
                    480,
                    null,
                    "Bancários",
                    "João Pessoa",
                    "PB",
                    "58051000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numCredenciamento(10004L)
                .nivelCertificacao(NivelCertificacao.BASICO)
                .dataValidadeCertificacao(LocalDate.of(2027, 5, 10))
                .qtdExpedicoesConcluidas(8)
                .build()
        );

        guias.add(
            GuiaEspeleologia.builder()
                .nome("Bruno Henrique Silva")
                .cpf("01234567891")
                .dataNascimento(LocalDate.of(1987, 4, 18))
                .email("bruno.silva@espeleologia.org")
                .telefone("(83) 90000-1010")
                .situacaoAtiva(SituacaoAtiva.AFASTADA)
                .endereco(new Endereco(
                    "Rua Deputado José Mariz",
                    725,
                    "Sala 02",
                    "Tambauzinho",
                    "João Pessoa",
                    "PB",
                    "58042000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numCredenciamento(10005L)
                .nivelCertificacao(NivelCertificacao.AVANCADO)
                .dataValidadeCertificacao(LocalDate.of(2029, 11, 30))
                .qtdExpedicoesConcluidas(61)
                .build()
        );

        for (GuiaEspeleologia g : guias) {
            em.persist(g);
        }
    }
}
