package com.firstteam.sportsLink.Product;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

        @Autowired
        private AmazonS3 amazonS3;


        private String bucketName = "sportlink-image";

        public String uploadFile(MultipartFile file) throws IOException {
            String fileName = generateFileName(file);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
            return fileName;
        }

        private String generateFileName(MultipartFile file) {
            return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        }
}
