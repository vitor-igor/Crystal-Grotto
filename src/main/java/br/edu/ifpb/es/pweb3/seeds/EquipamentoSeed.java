package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Equipamento;
import br.edu.ifpb.es.pweb3.models.enums.SituacaoEquipamento;
import br.edu.ifpb.es.pweb3.models.enums.TipoEquipamento;
import jakarta.persistence.EntityManager;

public final class EquipamentoSeed {

    private EquipamentoSeed() {}

    public static void carregar(EntityManager em) {

        List<Equipamento> equipamentos = new ArrayList<>();

        equipamentos.add(
            new Equipamento(
                null,
                10001L,
                "Capacete de Segurança",
                TipoEquipamento.CAPACETE,
                "Petzl",
                new BigDecimal("450.00"),
                LocalDate.of(2024, 2, 15),
                LocalDate.of(2026, 7, 10),
                SituacaoEquipamento.DISPONIVEL,
                true,
                new HashSet<>()
            )
        );

        equipamentos.add(
            new Equipamento(
                null,
                10002L,
                "Lanterna de Cabeça",
                TipoEquipamento.FONTE_DE_LUZ,
                "Fenix",
                new BigDecimal("380.00"),
                LocalDate.of(2024, 5, 20),
                LocalDate.of(2026, 6, 18),
                SituacaoEquipamento.DISPONIVEL,
                true,
                new HashSet<>()
            )
        );

        equipamentos.add(
            new Equipamento(
                null,
                10003L,
                "Corda Semi-estática 50m",
                TipoEquipamento.CORDA,
                "Beal",
                new BigDecimal("1250.00"),
                LocalDate.of(2023, 8, 12),
                LocalDate.of(2026, 5, 22),
                SituacaoEquipamento.MANUTENCAO_CORRETIVA,
                false,
                new HashSet<>()
            )
        );

        equipamentos.add(
            new Equipamento(
                null,
                10004L,
                "Mosquetão de Segurança",
                TipoEquipamento.ACESSORIO,
                "Black Diamond",
                new BigDecimal("180.00"),
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2026, 8, 5),
                SituacaoEquipamento.DISPONIVEL,
                false,
                new HashSet<>()
            )
        );

        equipamentos.add(
            new Equipamento(
                null,
                10005L,
                "Detector de Gases",
                TipoEquipamento.MONITORAMENTO,
                "MSA",
                new BigDecimal("3200.00"),
                LocalDate.of(2023, 11, 5),
                LocalDate.of(2026, 4, 12),
                SituacaoEquipamento.INDISPONIVEL,
                true,
                new HashSet<>()
            )
        );

        for (Equipamento e : equipamentos) {
            em.persist(e);
        }
    }
}
