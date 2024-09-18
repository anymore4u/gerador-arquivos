package com.example.geradorarquivos.infra.adapters.out.repository;

import com.example.geradorarquivos.application.ports.out.LayoutRepository;
import com.example.geradorarquivos.domain.entities.Layout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Component
public class S3LayoutRepository implements LayoutRepository {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3LayoutRepository(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public Layout obterLayout(String nomeLayout) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(nomeLayout)
                .build();

        try {
            ResponseBytes<GetObjectResponse> s3ObjectBytes = s3Client.getObject(getObjectRequest, ResponseTransformer.toBytes());
            String conteudo = s3ObjectBytes.asUtf8String();
            return new Layout(conteudo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter layout do S3", e);
        }
    }
}
