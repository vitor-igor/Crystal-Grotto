package br.edu.ifpb.es.pweb3.seeds;

import java.io.IOException;
import java.io.InputStream;

public final class ArquivoSeed {

    private ArquivoSeed() {
    }

    public static byte[] ler(String caminho) {
        try (InputStream is = ArquivoSeed.class
                .getClassLoader()
                .getResourceAsStream(caminho)) {

            if (is == null) {
                throw new IllegalArgumentException(
                        "Arquivo de seed não encontrado: "
                                + caminho
                );
            }

            return is.readAllBytes();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Erro ao ler arquivo de seed: " + caminho,
                    e
            );
        }
    }
}