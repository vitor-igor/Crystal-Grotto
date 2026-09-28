package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.ParticipacaoExpedicao;
import br.edu.ifpb.es.pweb3.models.Pessoa;
import br.edu.ifpb.es.pweb3.models.enums.PapelExpedicao;
import jakarta.persistence.EntityManager;

public final class ParticipacaoExpedicaoSeed {

    private ParticipacaoExpedicaoSeed() {}

    public static void carregar(EntityManager em) {

        List<Pessoa> pessoas = em.createQuery(
                "SELECT p FROM Pessoa p ORDER BY p.id",
                Pessoa.class
        ).getResultList();

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<ParticipacaoExpedicao> participacoes = new ArrayList<>();

        participacoes.add(
            new ParticipacaoExpedicao(
                null,
                PapelExpedicao.PESQUISADOR,
                LocalDate.of(2026, 9, 20),
                new BigDecimal("450.00"),
                3,
                true,
                new ArrayList<>(List.of(
                    "Responsável pelo levantamento geológico."
                )),
                pessoas.get(0),
                expedicoes.get(0)
            )
        );

        participacoes.add(
            new ParticipacaoExpedicao(
                null,
                PapelExpedicao.GUIA,
                LocalDate.of(2026, 9, 21),
                new BigDecimal("350.00"),
                3,
                true,
                new ArrayList<>(List.of(
                    "Responsável pela orientação durante o percurso."
                )),
                pessoas.get(1),
                expedicoes.get(0)
            )
        );

        participacoes.add(
            new ParticipacaoExpedicao(
                null,
                PapelExpedicao.PESQUISADOR,
                LocalDate.of(2026, 9, 22),
                new BigDecimal("500.00"),
                3,
                true,
                new ArrayList<>(List.of(
                    "Responsável pela documentação científica."
                )),
                pessoas.get(2),
                expedicoes.get(1)
            )
        );

        participacoes.add(
            new ParticipacaoExpedicao(
                null,
                PapelExpedicao.GUIA,
                LocalDate.of(2026, 9, 23),
                new BigDecimal("400.00"),
                2,
                true,
                new ArrayList<>(List.of(
                    "Responsável pela segurança do grupo."
                )),
                pessoas.get(3),
                expedicoes.get(2)
            )
        );

        participacoes.add(
            new ParticipacaoExpedicao(
                null,
                PapelExpedicao.PESQUISADOR,
                LocalDate.of(2026, 9, 24),
                new BigDecimal("550.00"),
                4,
                false,
                new ArrayList<>(List.of(
                    "Participação aguardando confirmação final."
                )),
                pessoas.get(4),
                expedicoes.get(3)
            )
        );

        for (ParticipacaoExpedicao participacao : participacoes) {
            em.persist(participacao);
        }
    }
}