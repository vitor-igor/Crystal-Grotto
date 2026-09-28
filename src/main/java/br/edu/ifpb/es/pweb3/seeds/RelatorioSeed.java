package br.edu.ifpb.es.pweb3.seeds;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.Relatorio;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoRelatorio;
import jakarta.persistence.EntityManager;

public final class RelatorioSeed {

    private RelatorioSeed() {}

    public static void carregar(EntityManager em) {

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<Relatorio> relatorios = new ArrayList<>();

        relatorios.add(
            new Relatorio(
                null,
                "Relatório de Mapeamento da Caverna da Pedra Furada",
                "Relatório contendo os resultados do mapeamento dos principais condutos e das formações geológicas identificadas.",
                LocalDate.of(2026, 10, 15),
                42,
                SituacaoRelatorio.APROVADO,
                ArquivoSeed.ler(
                    "seeds/relatorios/relatorio_20260001.pdf"
                ),
                true,
                expedicoes.get(0)
            )
        );

        relatorios.add(
            new Relatorio(
                null,
                "Relatório do Estudo Hidrológico da Gruta do Sol Nascente",
                "Documento com os resultados da análise da circulação de água e dos pontos sujeitos a alagamento.",
                LocalDate.of(2026, 10, 22),
                35,
                SituacaoRelatorio.APROVADO,
                ArquivoSeed.ler(
                    "seeds/relatorios/relatorio_20260002.pdf"
                ),
                true,
                expedicoes.get(1)
            )
        );

        relatorios.add(
            new Relatorio(
                null,
                "Relatório do Levantamento Geológico do Vale Profundo",
                "Resultados do levantamento geológico e da documentação das formações minerais encontradas.",
                LocalDate.of(2026, 10, 30),
                51,
                SituacaoRelatorio.EM_ANALISE,
                ArquivoSeed.ler(
                    "seeds/relatorios/relatorio_20260003.pdf"
                ),
                false,
                expedicoes.get(2)
            )
        );

        relatorios.add(
            new Relatorio(
                null,
                "Relatório da Avaliação da Galeria Alagada",
                "Relatório sobre as condições de segurança e os impactos das inundações na galeria.",
                LocalDate.of(2026, 11, 12),
                28,
                SituacaoRelatorio.NECESSITA_CORRECOES,
                ArquivoSeed.ler(
                    "seeds/relatorios/relatorio_20260004.pdf"
                ),
                false,
                expedicoes.get(3)
            )
        );

        relatorios.add(
            new Relatorio(
                null,
                "Relatório da Exploração do Abismo Central",
                "Documento com os resultados da exploração e do levantamento topográfico do setor profundo.",
                LocalDate.of(2026, 11, 20),
                64,
                SituacaoRelatorio.EM_RASCUNHO,
                ArquivoSeed.ler(
                    "seeds/relatorios/relatorio_20260005.pdf"
                ),
                false,
                expedicoes.get(4)
            )
        );

        for (Relatorio relatorio : relatorios) {
            em.persist(relatorio);
        }
    }
}