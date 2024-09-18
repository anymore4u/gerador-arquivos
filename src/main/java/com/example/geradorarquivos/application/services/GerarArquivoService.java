package com.example.geradorarquivos.application.services;

import com.example.geradorarquivos.application.ports.in.GerarArquivoUseCase;
import com.example.geradorarquivos.application.ports.out.ArquivoStorage;
import com.example.geradorarquivos.application.ports.out.LayoutRepository;
import com.example.geradorarquivos.domain.entities.Layout;
import com.example.geradorarquivos.domain.enums.TipoArquivo;
import org.springframework.stereotype.Service;

@Service
public class GerarArquivoService implements GerarArquivoUseCase {

    private final LayoutRepository layoutRepository;
    private final ArquivoStorage arquivoStorage;

    public GerarArquivoService(LayoutRepository layoutRepository, ArquivoStorage arquivoStorage) {
        this.layoutRepository = layoutRepository;
        this.arquivoStorage = arquivoStorage;
    }

    @Override
    public void gerarArquivo(String nomeLayout, String data, String tipoArquivoParam) {
        // Obter o layout
        Layout layout = layoutRepository.obterLayout(nomeLayout);

        // Converter o tipo de arquivo
        TipoArquivo tipoArquivo = TipoArquivo.fromString(tipoArquivoParam);

        // Gerar o conteúdo
        String conteudo = layout.gerarConteudo(data, tipoArquivo);

        // Gerar o nome do arquivo
        String nomeArquivo = nomeLayout.replace("YYYYMMDD", data);

        // Salvar o arquivo
        arquivoStorage.salvarArquivo(nomeArquivo, conteudo);
    }
}
