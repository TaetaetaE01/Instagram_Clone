package com.example.instargram_clone.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());


        // 이미지 업로드
        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        String uploadUrl = amazonS3.getUrl(bucket, originalFilename).toString();

        return uploadUrl;
    }

    public String deleteImage(String originalFilename) {
        amazonS3.deleteObject(bucket, originalFilename);
        return "SUCCES";
    }
}
