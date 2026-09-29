package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Coleta;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.Pesquisador;
import br.edu.ifpb.es.pweb3.models.SetorPesquisa;
import br.edu.ifpb.es.pweb3.models.enums.MetodoColeta;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoColeta;
import jakarta.persistence.EntityManager;

public final class ColetaSeed {

    private ColetaSeed() {}

    public static void carregar(EntityManager em) {

        List<SetorPesquisa> setores = em.createQuery(
                "SELECT s FROM SetorPesquisa s ORDER BY s.id",
                SetorPesquisa.class
        ).getResultList();

        List<Pesquisador> pesquisadores = em.createQuery(
                "SELECT p FROM Pesquisador p ORDER BY p.id",
                Pesquisador.class
        ).getResultList();

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<Coleta> coletas = new ArrayList<>();

        coletas.add(
            new Coleta(
                null,
                LocalDateTime.of(2026, 10, 5, 10, 30),
                MetodoColeta.MANUAL,
                "Ponto de coleta localizado na galeria principal.",
                new BigDecimal("19.50"),
                new BigDecimal("82.30"),
                new BigDecimal("18.500"),
                new ArrayList<>(List.of(
                    "Ambiente seco.",
                    "Boa visibilidade no ponto de coleta."
                )),
                SituacaoColeta.CONCLUIDA,
                setores.get(0),
                pesquisadores.get(0),
                new java.util.HashSet<>(),
                expedicoes.get(0)
            )
        );

        coletas.add(
            new Coleta(
                null,
                LocalDateTime.of(2026, 10, 12, 11, 15),
                MetodoColeta.AMOSTRAGEM,
                "Ponto próximo ao trecho sujeito a alagamento.",
                new BigDecimal("18.20"),
                new BigDecimal("91.40"),
                new BigDecimal("35.700"),
                new ArrayList<>(List.of(
                    "Presença de umidade elevada.",
                    "Fluxo de água observado durante a coleta."
                )),
                SituacaoColeta.CONCLUIDA,
                setores.get(1),
                pesquisadores.get(1),
                new java.util.HashSet<>(),
                expedicoes.get(1)
            )
        );

        coletas.add(
            new Coleta(
                null,
                LocalDateTime.of(2026, 10, 20, 14, 0),
                MetodoColeta.MANUAL,
                "Coleta realizada no salão principal.",
                new BigDecimal("20.10"),
                new BigDecimal("78.60"),
                new BigDecimal("12.300"),
                new ArrayList<>(List.of(
                    "Formações minerais preservadas.",
                    "Ponto sem sinais aparentes de alteração."
                )),
                SituacaoColeta.VALIDADA,
                setores.get(2),
                pesquisadores.get(2),
                new java.util.HashSet<>(),
                expedicoes.get(2)
            )
        );

        coletas.add(
            new Coleta(
                null,
                LocalDateTime.of(2026, 11, 3, 13, 45),
                MetodoColeta.EXTRACAO,
                "Ponto localizado na galeria alagada.",
                new BigDecimal("17.80"),
                new BigDecimal("96.20"),
                new BigDecimal("42.800"),
                new ArrayList<>(List.of(
                    "Umidade muito elevada.",
                    "Coleta realizada com equipamento de proteção."
                )),
                SituacaoColeta.VALIDADA,
                setores.get(3),
                pesquisadores.get(3),
                new java.util.HashSet<>(),
                expedicoes.get(3)
            )
        );

        coletas.add(
            new Coleta(
                null,
                LocalDateTime.of(2026, 11, 10, 15, 20),
                MetodoColeta.BUSCA,
                "Ponto localizado na região profunda do abismo.",
                new BigDecimal("16.90"),
                new BigDecimal("88.70"),
                new BigDecimal("115.600"),
                new ArrayList<>(List.of(
                    "Acesso realizado por sistema vertical.",
                    "Ponto com baixa circulação de ar."
                )),
                SituacaoColeta.EM_ANALISE,
                setores.get(4),
                pesquisadores.get(4),
                new java.util.HashSet<>(),
                expedicoes.get(4)
            )
        );

        for (Coleta coleta : coletas) {
            em.persist(coleta);
        }
    }
}