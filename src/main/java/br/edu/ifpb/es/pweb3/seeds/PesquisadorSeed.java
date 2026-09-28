package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Endereco;
import br.edu.ifpb.es.pweb3.models.Pesquisador;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoAtiva;
import br.edu.ifpb.es.pweb3.models.enums.Titulacao;
import jakarta.persistence.EntityManager;

public final class PesquisadorSeed {

    private PesquisadorSeed() {}

    public static void carregar(EntityManager em) {

        List<Pesquisador> pesquisadores = new ArrayList<>();

        pesquisadores.add(
            Pesquisador.builder()
                .nome("Maria Silva")
                .cpf("12345678902")
                .dataNascimento(LocalDate.of(1985, 5, 20))
                .email("maria.silva@ifpb.edu.br")
                .telefone("(83) 99999-1001")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua das Acácias",
                    123,
                    null,
                    "Torre",
                    "João Pessoa",
                    "PB",
                    "58040000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numRegistroInstitucional(20231092L)
                .areaPesquisa("Geologia")
                .titulacao(Titulacao.DOUTOR)
                .valorDiarioBolsa(new BigDecimal("150.00"))
                .build()
        );

        pesquisadores.add(
            Pesquisador.builder()
                .nome("Carlos Henrique Souza")
                .cpf("23456789013")
                .dataNascimento(LocalDate.of(1978, 11, 14))
                .email("carlos.souza@ufpb.br")
                .telefone("(83) 98888-2002")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Avenida Epitácio Pessoa",
                    850,
                    "Apto 402",
                    "Bairro dos Estados",
                    "João Pessoa",
                    "PB",
                    "58030000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numRegistroInstitucional(20190451L)
                .areaPesquisa("Biologia Subterrânea")
                .titulacao(Titulacao.MESTRE)
                .valorDiarioBolsa(new BigDecimal("120.00"))
                .build()
        );

        pesquisadores.add(
            Pesquisador.builder()
                .nome("Ana Beatriz Oliveira")
                .cpf("34567890124")
                .dataNascimento(LocalDate.of(1990, 3, 8))
                .email("ana.oliveira@ufpe.br")
                .telefone("(81) 97777-3003")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua do Sol",
                    456,
                    null,
                    "Centro",
                    "Recife",
                    "PE",
                    "50030000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numRegistroInstitucional(20210573L)
                .areaPesquisa("Paleontologia")
                .titulacao(Titulacao.DOUTOR)
                .valorDiarioBolsa(new BigDecimal("180.00"))
                .build()
        );

        pesquisadores.add(
            Pesquisador.builder()
                .nome("Rafael Martins Costa")
                .cpf("45678901235")
                .dataNascimento(LocalDate.of(1988, 9, 25))
                .email("rafael.costa@ufrn.br")
                .telefone("(84) 96666-4004")
                .situacaoAtiva(SituacaoAtiva.FERIAS)
                .endereco(new Endereco(
                    "Rua das Dunas",
                    789,
                    null,
                    "Tirol",
                    "Natal",
                    "RN",
                    "59020000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numRegistroInstitucional(20181234L)
                .areaPesquisa("Espeleologia")
                .titulacao(Titulacao.MESTRE)
                .valorDiarioBolsa(new BigDecimal("135.00"))
                .build()
        );

        pesquisadores.add(
            Pesquisador.builder()
                .nome("Juliana Ferreira Lima")
                .cpf("56789012346")
                .dataNascimento(LocalDate.of(1993, 7, 17))
                .email("juliana.lima@ifpb.edu.br")
                .telefone("(83) 95555-5005")
                .situacaoAtiva(SituacaoAtiva.ATIVA)
                .endereco(new Endereco(
                    "Rua Professor José Coelho",
                    215,
                    "Casa B",
                    "Bancários",
                    "João Pessoa",
                    "PB",
                    "58051000"
                ))
                .participacoesExpedicoes(new HashSet<>())
                .utilizacoesEquipamentos(new HashSet<>())
                .numRegistroInstitucional(20240187L)
                .areaPesquisa("Ecologia de Cavernas")
                .titulacao(Titulacao.ESPECIALISTA)
                .valorDiarioBolsa(new BigDecimal("110.00"))
                .build()
        );

        for (Pesquisador p : pesquisadores) {
            em.persist(p);
        }
    }
}