package br.edu.ifpb.es.pweb3.seeds;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.PlanoSeguranca;
import jakarta.persistence.EntityManager;

public final class PlanoSegurancaSeed {

    private PlanoSegurancaSeed() {}

    public static void carregar(EntityManager em) {

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<PlanoSeguranca> planos = new ArrayList<>();

        planos.add(
            new PlanoSeguranca(
                null,
                new ArrayList<>(List.of(
                    "Evacuar imediatamente pela galeria principal.",
                    "Reunir todos os participantes no ponto externo.",
                    "Realizar conferência dos participantes antes do deslocamento."
                )),
                "Estacionamento da entrada principal da caverna",
                30,
                "193",
                false,
                ArquivoSeed.ler(
                    "seeds/planos/mapa_rota_20260001.pdf"
                ),
                expedicoes.get(0)
            )
        );

        planos.add(
            new PlanoSeguranca(
                null,
                new ArrayList<>(List.of(
                    "Interromper a atividade em caso de elevação do nível da água.",
                    "Retornar pelo trajeto de entrada.",
                    "Comunicar a equipe externa sobre a evacuação."
                )),
                "Área de apoio próxima à entrada",
                20,
                "193",
                true,
                ArquivoSeed.ler(
                    "seeds/planos/mapa_rota_20260002.pdf"
                ),
                expedicoes.get(1)
            )
        );

        planos.add(
            new PlanoSeguranca(
                null,
                new ArrayList<>(List.of(
                    "Retornar pela rota sinalizada.",
                    "Manter comunicação periódica com a equipe externa.",
                    "Realizar conferência do grupo após a saída."
                )),
                "Ponto de encontro junto ao veículo de apoio",
                45,
                "193",
                false,
                ArquivoSeed.ler(
                    "seeds/planos/mapa_rota_20260003.pdf"
                ),
                expedicoes.get(2)
            )
        );

        planos.add(
            new PlanoSeguranca(
                null,
                new ArrayList<>(List.of(
                    "Suspender a expedição diante de risco de inundação.",
                    "Utilizar a rota alternativa de emergência.",
                    "Solicitar apoio médico se necessário."
                )),
                "Base operacional externa",
                15,
                "193",
                true,
                ArquivoSeed.ler(
                    "seeds/planos/mapa_rota_20260004.pdf"
                ),
                expedicoes.get(3)
            )
        );

        planos.add(
            new PlanoSeguranca(
                null,
                new ArrayList<>(List.of(
                    "Interromper a descida em caso de emergência.",
                    "Retornar utilizando o sistema de segurança vertical.",
                    "Acionar a equipe externa caso a comunicação seja perdida."
                )),
                "Área segura próxima à entrada",
                20,
                "193",
                true,
                ArquivoSeed.ler(
                    "seeds/planos/mapa_rota_20260005.pdf"
                ),
                expedicoes.get(4)
            )
        );

        for (PlanoSeguranca plano : planos) {
            em.persist(plano);
        }
    }
}