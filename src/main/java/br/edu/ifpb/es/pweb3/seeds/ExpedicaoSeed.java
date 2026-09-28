package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Caverna;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.SetorPesquisa;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoExpedicao;
import jakarta.persistence.EntityManager;

public final class ExpedicaoSeed {

    private ExpedicaoSeed() {}

    public static void carregar(EntityManager em) {

        List<Caverna> cavernas = em.createQuery(
                "SELECT c FROM Caverna c ORDER BY c.id",
                Caverna.class
        ).getResultList();

        List<SetorPesquisa> setores = em.createQuery(
                "SELECT s FROM SetorPesquisa s ORDER BY s.id",
                SetorPesquisa.class
        ).getResultList();

        List<Expedicao> expedicoes = new ArrayList<>();

        Expedicao expedicao1 = new Expedicao(
                null,
                20260001L,
                "Mapeamento da Caverna da Pedra Furada",
                "Mapear os principais condutos e registrar as formações geológicas da caverna.",
                LocalDateTime.of(2026, 10, 5, 8, 0),
                LocalDateTime.of(2026, 10, 7, 18, 0),
                new BigDecimal("15000.00"),
                null,
                10,
                SituacaoExpedicao.PLANEJADA,
                false,
                cavernas.get(0),
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>(),
                new HashSet<>(),
                null
        );

        expedicao1.getSetoresPesquisa().add(setores.get(0));

        Expedicao expedicao2 = new Expedicao(
                null,
                20260002L,
                "Estudo Hidrológico da Gruta do Sol Nascente",
                "Investigar a circulação de água e os pontos sujeitos a alagamento.",
                LocalDateTime.of(2026, 10, 12, 7, 30),
                LocalDateTime.of(2026, 10, 14, 17, 30),
                new BigDecimal("18500.00"),
                null,
                8,
                SituacaoExpedicao.AUTORIZADA,
                false,
                cavernas.get(1),
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>(),
                new HashSet<>(),
                null
        );

        expedicao2.getSetoresPesquisa().add(setores.get(1));

        Expedicao expedicao3 = new Expedicao(
                null,
                20260003L,
                "Levantamento Geológico do Vale Profundo",
                "Realizar levantamento geológico e documentação das formações minerais.",
                LocalDateTime.of(2026, 10, 20, 8, 0),
                LocalDateTime.of(2026, 10, 22, 17, 0),
                new BigDecimal("22000.00"),
                null,
                12,
                SituacaoExpedicao.PLANEJADA,
                false,
                cavernas.get(2),
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>(),
                new HashSet<>(),
                null
        );

        expedicao3.getSetoresPesquisa().add(setores.get(2));

        Expedicao expedicao4 = new Expedicao(
                null,
                20260004L,
                "Avaliação da Galeria Alagada",
                "Avaliar as condições de segurança e os impactos das inundações no setor.",
                LocalDateTime.of(2026, 11, 3, 6, 30),
                LocalDateTime.of(2026, 11, 5, 19, 0),
                new BigDecimal("27500.00"),
                null,
                6,
                SituacaoExpedicao.PLANEJADA,
                false,
                cavernas.get(3),
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>(),
                new HashSet<>(),
                null
        );

        expedicao4.getSetoresPesquisa().add(setores.get(3));

        Expedicao expedicao5 = new Expedicao(
                null,
                20260005L,
                "Exploração do Abismo Central",
                "Investigar o setor profundo e realizar levantamento topográfico.",
                LocalDateTime.of(2026, 11, 10, 7, 0),
                LocalDateTime.of(2026, 11, 13, 18, 0),
                new BigDecimal("32000.00"),
                null,
                8,
                SituacaoExpedicao.PLANEJADA,
                false,
                cavernas.get(4),
                new HashSet<>(),
                new HashSet<>(),
                null,
                new HashSet<>(),
                new HashSet<>(),
                null
        );

        expedicao5.getSetoresPesquisa().add(setores.get(4));

        expedicoes.add(expedicao1);
        expedicoes.add(expedicao2);
        expedicoes.add(expedicao3);
        expedicoes.add(expedicao4);
        expedicoes.add(expedicao5);

        for (Expedicao expedicao : expedicoes) {
            em.persist(expedicao);
        }
    }
}