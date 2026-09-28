package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Caverna;
import br.edu.ifpb.es.pweb3.models.CoordenadaGeografica;
import jakarta.persistence.EntityManager;

public final class CavernaSeed {

    private CavernaSeed() {}

    public static void carregar(EntityManager em) {

        List<Caverna> cavernas = new ArrayList<>();

        cavernas.add(
            new Caverna(
                null,
                "Caverna da Pedra Furada",
                1000001L,
                "João Pessoa",
                "PB",
                new CoordenadaGeografica(
                    new BigDecimal("-7.1195"),
                    new BigDecimal("-34.8450"),
                    "SIRGAS2000"
                ),
                new BigDecimal("42.50"),
                new BigDecimal("1250.00"),
                LocalDate.of(2026, 8, 15),
                true,
                new java.util.HashSet<>(),
                new java.util.HashSet<>()
            )
        );

        cavernas.add(
            new Caverna(
                null,
                "Gruta do Sol Nascente",
                1000002L,
                "Cabaceiras",
                "PB",
                new CoordenadaGeografica(
                    new BigDecimal("-7.4900"),
                    new BigDecimal("-36.2860"),
                    "SIRGAS2000"
                ),
                new BigDecimal("318.70"),
                new BigDecimal("890.50"),
                LocalDate.of(2026, 7, 20),
                true,
                new java.util.HashSet<>(),
                new java.util.HashSet<>()
            )
        );

        cavernas.add(
            new Caverna(
                null,
                "Caverna do Vale Profundo",
                1000003L,
                "Areia",
                "PB",
                new CoordenadaGeografica(
                    new BigDecimal("-6.9700"),
                    new BigDecimal("-35.7000"),
                    "SIRGAS2000"
                ),
                new BigDecimal("156.30"),
                new BigDecimal("2340.80"),
                LocalDate.of(2026, 6, 10),
                true,
                new java.util.HashSet<>(),
                new java.util.HashSet<>()
            )
        );

        cavernas.add(
            new Caverna(
                null,
                "Gruta das Águas",
                1000004L,
                "Mamanguape",
                "PB",
                new CoordenadaGeografica(
                    new BigDecimal("-6.8340"),
                    new BigDecimal("-35.1210"),
                    "SIRGAS2000"
                ),
                new BigDecimal("87.40"),
                new BigDecimal("675.20"),
                LocalDate.of(2026, 5, 25),
                false,
                new java.util.HashSet<>(),
                new java.util.HashSet<>()
            )
        );

        cavernas.add(
            new Caverna(
                null,
                "Caverna do Lajedo",
                1000005L,
                "Santa Luzia",
                "PB",
                new CoordenadaGeografica(
                    new BigDecimal("-6.8730"),
                    new BigDecimal("-36.9180"),
                    "SIRGAS2000"
                ),
                new BigDecimal("421.80"),
                new BigDecimal("3100.60"),
                LocalDate.of(2026, 8, 30),
                true,
                new java.util.HashSet<>(),
                new java.util.HashSet<>()
            )
        );

        for (Caverna caverna : cavernas) {
            em.persist(caverna);
        }
    }
}