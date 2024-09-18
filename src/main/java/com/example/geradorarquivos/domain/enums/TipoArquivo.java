package com.example.geradorarquivos.domain.enums;

public enum TipoArquivo {
    HEADER,
    TRAILER,
    HEADER_TRAILER,
    VAZIO;

    public static TipoArquivo fromString(String tipo) {
        return switch (tipo.toUpperCase()) {
            case "HEADER" -> HEADER;
            case "TRAILER" -> TRAILER;
            case "HEADER_TRAILER" -> HEADER_TRAILER;
            case "VAZIO" -> VAZIO;
            default -> throw new IllegalArgumentException("Tipo de arquivo inválido: " + tipo);
        };
    }
}
