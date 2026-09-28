package br.edu.ifpb.es.pweb3.seeds;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpb.es.pweb3.models.Equipamento;
import br.edu.ifpb.es.pweb3.models.Expedicao;
import br.edu.ifpb.es.pweb3.models.Pessoa;
import br.edu.ifpb.es.pweb3.models.UtilizacaoEquipamento;
import br.edu.ifpb.es.pweb3.models.enums.EstadoEquipamento;
import jakarta.persistence.EntityManager;

public final class UtilizacaoEquipamentoSeed {

    private UtilizacaoEquipamentoSeed() {}

    public static void carregar(EntityManager em) {

        List<Expedicao> expedicoes = em.createQuery(
                "SELECT e FROM Expedicao e ORDER BY e.id",
                Expedicao.class
        ).getResultList();

        List<Equipamento> equipamentos = em.createQuery(
                "SELECT e FROM Equipamento e ORDER BY e.id",
                Equipamento.class
        ).getResultList();

        List<Pessoa> pessoas = em.createQuery(
                "SELECT p FROM Pessoa p ORDER BY p.id",
                Pessoa.class
        ).getResultList();

        if (expedicoes.size() < 4) {
            throw new IllegalStateException(
                "É necessário ter pelo menos 4 expedições cadastradas."
            );
        }

        if (equipamentos.size() < 5) {
            throw new IllegalStateException(
                "É necessário ter pelo menos 5 equipamentos cadastrados."
            );
        }

        if (pessoas.size() < 5) {
            throw new IllegalStateException(
                "É necessário ter pelo menos 5 pessoas cadastradas."
            );
        }

        List<UtilizacaoEquipamento> utilizacoes = new ArrayList<>();

        utilizacoes.add(
            new UtilizacaoEquipamento(
                null,
                LocalDateTime.of(2026, 9, 10, 7, 30),
                LocalDateTime.of(2026, 9, 10, 18, 0),
                LocalDateTime.of(2026, 9, 10, 17, 40),
                EstadoEquipamento.DISPONIVEL,
                EstadoEquipamento.DISPONIVEL,
                null,
                expedicoes.get(0),
                equipamentos.get(0),
                pessoas.get(0)
            )
        );

        utilizacoes.add(
            new UtilizacaoEquipamento(
                null,
                LocalDateTime.of(2026, 9, 10, 7, 45),
                LocalDateTime.of(2026, 9, 10, 18, 0),
                LocalDateTime.of(2026, 9, 10, 17, 50),
                EstadoEquipamento.DISPONIVEL,
                EstadoEquipamento.DESCARTE,
                null,
                expedicoes.get(0),
                equipamentos.get(1),
                pessoas.get(1)
            )
        );

        utilizacoes.add(
            new UtilizacaoEquipamento(
                null,
                LocalDateTime.of(2026, 9, 15, 6, 30),
                LocalDateTime.of(2026, 9, 15, 20, 0),
                LocalDateTime.of(2026, 9, 15, 19, 30),
                EstadoEquipamento.DISPONIVEL,
                EstadoEquipamento.DESCARTE,
                new BigDecimal("250.00"),
                expedicoes.get(1),
                equipamentos.get(2),
                pessoas.get(2)
            )
        );

        utilizacoes.add(
            new UtilizacaoEquipamento(
                null,
                LocalDateTime.of(2026, 9, 20, 8, 0),
                LocalDateTime.of(2026, 9, 20, 17, 0),
                LocalDateTime.of(2026, 9, 20, 16, 45),
                EstadoEquipamento.DISPONIVEL,
                EstadoEquipamento.DISPONIVEL,
                null,
                expedicoes.get(2),
                equipamentos.get(3),
                pessoas.get(3)
            )
        );

        utilizacoes.add(
            new UtilizacaoEquipamento(
                null,
                LocalDateTime.of(2026, 9, 22, 7, 0),
                LocalDateTime.of(2026, 9, 22, 19, 0),
                null,
                EstadoEquipamento.DISPONIVEL,
                null,
                null,
                expedicoes.get(3),
                equipamentos.get(4),
                pessoas.get(4)
            )
        );

        for (UtilizacaoEquipamento u : utilizacoes) {
            em.persist(u);
        }
    }
}
