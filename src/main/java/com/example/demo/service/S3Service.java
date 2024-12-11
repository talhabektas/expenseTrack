// S3Service.java
package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        PutObjectRequest request = new PutObjectRequest(
                bucketName,
                fileName,
                file.getInputStream(),
                metadata
        );

        amazonS3.putObject(request);
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    public byte[] downloadFile(String fileName) {
        try {
            S3Object object = amazonS3.getObject(bucketName, fileName);
            return object.getObjectContent().readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file from S3", e);
        }
    }
}