package com.example.geradorarquivos.application.ports.in;

public interface GerarArquivoUseCase {
    void gerarArquivo(String nomeLayout, String data, String tipoArquivo);
}
