package br.edu.ifpb.es.pweb3.main;

import jakarta.transaction.UserTransaction;
import br.edu.ifpb.es.pweb3.seeds.AmostraSeed;
import br.edu.ifpb.es.pweb3.seeds.AutorizacaoAmbientalSeed;
import br.edu.ifpb.es.pweb3.seeds.CavernaSeed;
import br.edu.ifpb.es.pweb3.seeds.ColetaSeed;
import br.edu.ifpb.es.pweb3.seeds.EquipamentoSeed;
import br.edu.ifpb.es.pweb3.seeds.ExpedicaoSeed;
import br.edu.ifpb.es.pweb3.seeds.GuiaEspeleologiaSeed;
import br.edu.ifpb.es.pweb3.seeds.ParticipacaoExpedicaoSeed;
import br.edu.ifpb.es.pweb3.seeds.PesquisadorSeed;
import br.edu.ifpb.es.pweb3.seeds.PlanoSegurancaSeed;
import br.edu.ifpb.es.pweb3.seeds.RelatorioSeed;
import br.edu.ifpb.es.pweb3.seeds.SetorPesquisaSeed;
import br.edu.ifpb.es.pweb3.seeds.UtilizacaoEquipamentoSeed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CargaInicial {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crystalPU");
        EntityManager em = emf.createEntityManager();
        
        UserTransaction tx = com.arjuna.ats.jta.UserTransaction.userTransaction();

        try {
            tx.begin();
            em.joinTransaction();

            PesquisadorSeed.carregar(em);
            GuiaEspeleologiaSeed.carregar(em);
            CavernaSeed.carregar(em);
            SetorPesquisaSeed.carregar(em);
            ExpedicaoSeed.carregar(em);
            PlanoSegurancaSeed.carregar(em);
            EquipamentoSeed.carregar(em);
            UtilizacaoEquipamentoSeed.carregar(em);
            ParticipacaoExpedicaoSeed.carregar(em);
            RelatorioSeed.carregar(em);
            ColetaSeed.carregar(em);
            AmostraSeed.carregar(em);
            AutorizacaoAmbientalSeed.carregar(em);

            tx.commit();
            
            System.out.println("Dados inseridos com sucesso!");
        } catch (Exception e) {
            try {
                if (tx != null) {
                    tx.rollback();
                }
            } catch (Exception rollBackException) {
                e.addSuppressed(rollBackException);
            }

            throw new RuntimeException("Erro ao executar transação JTA: ", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
