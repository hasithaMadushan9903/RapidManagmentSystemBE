package com.rapidattendencesystem.project.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UploadProgressService {

    private final Map<String, Double> progressMap = new ConcurrentHashMap<>();
    private final Map<String, String> urlMap = new ConcurrentHashMap<>();

    public void setProgress(String uploadId, double progress) {
        progressMap.put(uploadId, progress);
    }

    public double getProgress(String uploadId) {
        return progressMap.getOrDefault(uploadId, 0.0);
    }

    public void setUrl(String uploadId, String url) {
        urlMap.put(uploadId, url);
    }

    public String getUrl(String uploadId) {
        return urlMap.get(uploadId);
    }

    public void removeProgress(String uploadId) {
        progressMap.remove(uploadId);
    }
}
