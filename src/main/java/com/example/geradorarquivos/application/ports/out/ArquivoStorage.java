package com.example.geradorarquivos.application.ports.out;

public interface ArquivoStorage {
    void salvarArquivo(String nomeArquivo, String conteudo);
}
