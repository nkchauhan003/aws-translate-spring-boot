package com.cb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.translate.TranslateClient;
import software.amazon.awssdk.services.translate.model.TranslateTextRequest;

@Service
public class TranslationService {

    private final TranslateClient translateClient;

    public TranslationService(@Value("${aws.accessKeyId}") String accessKey,
                              @Value("${aws.secretAccessKey}") String secretKey,
                              @Value("${aws.region}") String region) {
        var awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        this.translateClient = TranslateClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.of(region))
                .build();
    }

    public String translateText(String text, String sourceLanguageCode, String targetLanguageCode) {
        var request = TranslateTextRequest.builder()
                .sourceLanguageCode(sourceLanguageCode)
                .targetLanguageCode(targetLanguageCode)
                .text(text)
                .build();
        var response = translateClient.translateText(request);
        var translatedText = response.translatedText();
        return translatedText;
    }
}