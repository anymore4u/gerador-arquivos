package com.example.geradorarquivos.domain.entities;

import com.example.geradorarquivos.domain.enums.TipoArquivo;

public class Layout {
    private final String conteudo;

    public Layout(String conteudo) {
        this.conteudo = conteudo;
    }

    public String gerarConteudo(String data, TipoArquivo tipoArquivo) {
        // Substituir 'YYYYMMDD' pela data
        String conteudoProcessado = conteudo.replace("YYYYMMDD", data);

        StringBuilder resultado = new StringBuilder();

        // Montar o conteúdo com base no tipo de arquivo
        switch (tipoArquivo) {
            case HEADER -> resultado.append(extrairSecao(conteudoProcessado, "HEADER"));
            case TRAILER -> resultado.append(extrairSecao(conteudoProcessado, "TRAILER"));
            case HEADER_TRAILER -> {
                resultado.append(extrairSecao(conteudoProcessado, "HEADER"));
                resultado.append("\n");
                resultado.append(extrairSecao(conteudoProcessado, "TRAILER"));
            }
            case VAZIO -> {
                // Arquivo vazio
            }
        }

        return resultado.toString();
    }

    private String extrairSecao(String conteudo, String secao) {
        // Implementação para extrair a seção desejada
        String marcador = secao + ":";
        int indiceInicio = conteudo.indexOf(marcador);
        if (indiceInicio == -1) {
            return "";
        }
        indiceInicio += marcador.length();
        int indiceFim = conteudo.indexOf("\n", indiceInicio);
        if (indiceFim == -1) {
            indiceFim = conteudo.length();
        }
        return conteudo.substring(indiceInicio, indiceFim).trim();
    }
}
