package br.edu.ifpb.es.pweb3.seeds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.AutorizacaoAmbiental;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoAutorizacaoAmbiental;
import jakarta.persistence.EntityManager;

public final class AutorizacaoAmbientalSeed {

    private AutorizacaoAmbientalSeed() {}

    public static void carregar(EntityManager em) {

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<AutorizacaoAmbiental> autorizacoes = new ArrayList<>();

        autorizacoes.add(
            new AutorizacaoAmbiental(
                null,
                202600001L,
                "SUDEMA",
                LocalDate.of(2026, 9, 1),
                LocalDate.of(2026, 12, 31),
                SituacaoAutorizacaoAmbiental.VALIDA,
                new ArrayList<>(List.of(
                    "Autorização para realização da expedição.",
                    "Atividade condicionada ao cumprimento do plano de segurança."
                )),
                ArquivoSeed.ler(
                    "seeds/autorizacoes/autorizacao_20260001.pdf"
                ),
                expedicoes.get(0)
            )
        );

        autorizacoes.add(
            new AutorizacaoAmbiental(
                null,
                202600002L,
                "SUDEMA",
                LocalDate.of(2026, 9, 5),
                LocalDate.of(2026, 12, 31),
                SituacaoAutorizacaoAmbiental.VALIDA,
                new ArrayList<>(List.of(
                    "Autorização emitida para estudo hidrológico."
                )),
                ArquivoSeed.ler(
                    "seeds/autorizacoes/autorizacao_20260002.pdf"
                ),
                expedicoes.get(1)
            )
        );

        autorizacoes.add(
            new AutorizacaoAmbiental(
                null,
                202600003L,
                "SUDEMA",
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 12, 31),
                SituacaoAutorizacaoAmbiental.VALIDA,
                new ArrayList<>(List.of(
                    "Autorização para levantamento geológico."
                )),
                ArquivoSeed.ler(
                    "seeds/autorizacoes/autorizacao_20260003.pdf"
                ),
                expedicoes.get(2)
            )
        );

        autorizacoes.add(
            new AutorizacaoAmbiental(
                null,
                202600004L,
                "SUDEMA",
                LocalDate.of(2026, 9, 15),
                LocalDate.of(2026, 11, 30),
                SituacaoAutorizacaoAmbiental.INVALIDA,
                new ArrayList<>(List.of(
                    "Autorização substituída por documento posterior."
                )),
                ArquivoSeed.ler(
                    "seeds/autorizacoes/autorizacao_20260004.pdf"
                ),
                expedicoes.get(3)
            )
        );

        autorizacoes.add(
            new AutorizacaoAmbiental(
                null,
                202600005L,
                "SUDEMA",
                LocalDate.of(2026, 9, 20),
                LocalDate.of(2027, 1, 31),
                SituacaoAutorizacaoAmbiental.VALIDA,
                new ArrayList<>(List.of(
                    "Autorização para exploração científica controlada."
                )),
                ArquivoSeed.ler(
                    "seeds/autorizacoes/autorizacao_20260005.pdf"
                ),
                expedicoes.get(4)
            )
        );

        for (AutorizacaoAmbiental autorizacao : autorizacoes) {
            em.persist(autorizacao);
        }
    }
}