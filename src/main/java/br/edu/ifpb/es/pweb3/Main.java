package br.edu.ifpb.es.pweb3;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("crystalPU");

        System.out.println("Tabelas criadas com sucesso!");

        emf.close();
    }
}