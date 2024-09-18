package com.example.geradorarquivos.infra.adapters.out.storage;

import com.example.geradorarquivos.application.ports.out.ArquivoStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileSystemArquivoStorage implements ArquivoStorage {

    @Value("${arquivo.output-dir:output}")
    private String outputDir;

    @Override
    public void salvarArquivo(String nomeArquivo, String conteudo) {
        try {
            Path path = Paths.get(outputDir, nomeArquivo);
            Files.createDirectories(path.getParent());
            Files.writeString(path, conteudo);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo", e);
        }
    }
}
