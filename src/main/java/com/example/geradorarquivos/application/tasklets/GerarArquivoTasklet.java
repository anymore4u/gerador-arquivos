package com.example.geradorarquivos.application.tasklets;

import com.example.geradorarquivos.application.ports.in.GerarArquivoUseCase;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class GerarArquivoTasklet implements org.springframework.batch.core.step.tasklet.Tasklet {

    private final GerarArquivoUseCase gerarArquivoUseCase;

    public GerarArquivoTasklet(GerarArquivoUseCase gerarArquivoUseCase) {
        this.gerarArquivoUseCase = gerarArquivoUseCase;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String nomeLayout = (String) chunkContext.getStepContext().getJobParameters().get("nomeLayout");
        String data = (String) chunkContext.getStepContext().getJobParameters().get("data");
        String tipoArquivo = (String) chunkContext.getStepContext().getJobParameters().get("tipoArquivo");

        gerarArquivoUseCase.gerarArquivo(nomeLayout, data, tipoArquivo);

        return RepeatStatus.FINISHED;
    }
}
