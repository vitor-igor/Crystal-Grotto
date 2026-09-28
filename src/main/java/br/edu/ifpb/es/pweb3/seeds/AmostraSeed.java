package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Amostra;
import br.edu.ifpb.es.pweb3.models.Coleta;
import br.edu.ifpb.es.pweb3.models.enums.CategoriaAmostra;
import br.edu.ifpb.es.pweb3.models.enums.CondicaoPreservacao;
import br.edu.ifpb.es.pweb3.models.enums.UnidadeMedida;
import jakarta.persistence.EntityManager;

public final class AmostraSeed {

    private AmostraSeed() {}

    public static void carregar(EntityManager em) {

        List<Coleta> coletas = em.createQuery(
                "SELECT c FROM Coleta c ORDER BY c.id",
                Coleta.class
        ).getResultList();

        List<Amostra> amostras = new ArrayList<>();

        amostras.add(
            new Amostra(
                null,
                20260001L,
                CategoriaAmostra.BIOLOGICA,
                new BigDecimal("125.350"),
                null,
                UnidadeMedida.GRAMA,
                LocalDateTime.of(2026, 10, 5, 11, 0),
                CondicaoPreservacao.DANIFICADA,
                false,
                ArquivoSeed.ler(
                    "seeds/amostras/amostra_01.png"
                ),
                new ArrayList<>(List.of(
                    "Amostra retirada da parede da galeria."
                )),
                coletas.get(0)
            )
        );

        amostras.add(
            new Amostra(
                null,
                20260002L,
                CategoriaAmostra.PALEONTOLOGICA,
                new BigDecimal("350.800"),
                null,
                UnidadeMedida.GRAMA,
                LocalDateTime.of(2026, 10, 12, 12, 0),
                CondicaoPreservacao.FRAGMENTADA,
                false,
                ArquivoSeed.ler(
                    "seeds/amostras/amostra_02.png"
                ),
                new ArrayList<>(List.of(
                    "Sedimento coletado próximo ao fluxo de água."
                )),
                coletas.get(1)
            )
        );

        amostras.add(
            new Amostra(
                null,
                20260003L,
                CategoriaAmostra.GEOLOGICA,
                new BigDecimal("82.450"),
                null,
                UnidadeMedida.GRAMA,
                LocalDateTime.of(2026, 10, 20, 14, 30),
                CondicaoPreservacao.FRAGMENTADA,
                false,
                ArquivoSeed.ler(
                    "seeds/amostras/amostra_03.png"
                ),
                new ArrayList<>(List.of(
                    "Fragmento mineral selecionado para análise."
                )),
                coletas.get(2)
            )
        );

        amostras.add(
            new Amostra(
                null,
                20260004L,
                CategoriaAmostra.BIOLOGICA,
                null,
                new BigDecimal("250.000"),
                UnidadeMedida.MILILITRO,
                LocalDateTime.of(2026, 11, 3, 14, 15),
                CondicaoPreservacao.INTACTA,
                true,
                ArquivoSeed.ler(
                    "seeds/amostras/amostra_04.png"
                ),
                new ArrayList<>(List.of(
                    "Amostra coletada em área sujeita a inundação.",
                    "Material encaminhado para análise laboratorial."
                )),
                coletas.get(3)
            )
        );

        amostras.add(
            new Amostra(
                null,
                20260005L,
                CategoriaAmostra.GEOLOGICA,
                new BigDecimal("215.600"),
                null,
                UnidadeMedida.GRAMA,
                LocalDateTime.of(2026, 11, 10, 16, 0),
                CondicaoPreservacao.INTACTA,
                false,
                ArquivoSeed.ler(
                    "seeds/amostras/amostra_05.png"
                ),
                new ArrayList<>(List.of(
                    "Fragmento coletado na região profunda."
                )),
                coletas.get(4)
            )
        );

        for (Amostra amostra : amostras) {
            em.persist(amostra);
        }
    }
}