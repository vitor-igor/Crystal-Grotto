package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Caverna;
import br.edu.ifpb.es.pweb3.models.SetorPesquisa;
import br.edu.ifpb.es.pweb3.models.enums.NivelDificuldade;
import jakarta.persistence.EntityManager;

public final class SetorPesquisaSeed {

    private SetorPesquisaSeed() {}

    public static void carregar(EntityManager em) {

        Caverna caverna1 = em.createQuery(
                "SELECT c FROM Caverna c WHERE c.codCadastroAmbiental = :codigo",
                Caverna.class
        )
        .setParameter("codigo", 1000001L)
        .getSingleResult();

        Caverna caverna2 = em.createQuery(
                "SELECT c FROM Caverna c WHERE c.codCadastroAmbiental = :codigo",
                Caverna.class
        )
        .setParameter("codigo", 1000002L)
        .getSingleResult();

        Caverna caverna3 = em.createQuery(
                "SELECT c FROM Caverna c WHERE c.codCadastroAmbiental = :codigo",
                Caverna.class
        )
        .setParameter("codigo", 1000003L)
        .getSingleResult();

        Caverna caverna4 = em.createQuery(
                "SELECT c FROM Caverna c WHERE c.codCadastroAmbiental = :codigo",
                Caverna.class
        )
        .setParameter("codigo", 1000004L)
        .getSingleResult();

        Caverna caverna5 = em.createQuery(
                "SELECT c FROM Caverna c WHERE c.codCadastroAmbiental = :codigo",
                Caverna.class
        )
        .setParameter("codigo", 1000005L)
        .getSingleResult();

        List<SetorPesquisa> setores = new ArrayList<>();

        setores.add(
            new SetorPesquisa(
                null,
                "Galeria Principal",
                NivelDificuldade.MODERADO,
                new BigDecimal("35.50"),
                new BigDecimal("420.00"),
                "Galeria de acesso principal, com formação rochosa estável.",
                new BigDecimal("15.00"),
                "Boa",
                caverna1
            )
        );

        setores.add(
            new SetorPesquisa(
                null,
                "Conduto Norte",
                NivelDificuldade.ALTO,
                new BigDecimal("72.30"),
                new BigDecimal("310.50"),
                "Conduto estreito com trechos de difícil progressão.",
                new BigDecimal("35.00"),
                "Regular",
                caverna2
            )
        );

        setores.add(
            new SetorPesquisa(
                null,
                "Salão das Estalactites",
                NivelDificuldade.BAIXO,
                new BigDecimal("18.70"),
                new BigDecimal("580.20"),
                "Grande salão com formações calcárias preservadas.",
                new BigDecimal("5.00"),
                "Boa",
                caverna3
            )
        );

        setores.add(
            new SetorPesquisa(
                null,
                "Galeria Alagada",
                NivelDificuldade.EXTREMO,
                new BigDecimal("95.40"),
                new BigDecimal("260.80"),
                "Trecho sujeito a alagamentos e com necessidade de equipamento especializado.",
                new BigDecimal("80.00"),
                "Instável",
                caverna4
            )
        );

        setores.add(
            new SetorPesquisa(
                null,
                "Abismo Central",
                NivelDificuldade.EXTREMO,
                new BigDecimal("140.20"),
                new BigDecimal("710.40"),
                "Setor profundo com acesso vertical e elevado grau de dificuldade.",
                new BigDecimal("20.00"),
                "Regular",
                caverna5
            )
        );

        for (SetorPesquisa setor : setores) {
            em.persist(setor);
        }
    }
}