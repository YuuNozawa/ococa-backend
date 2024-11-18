package dev.ococa.api.presentation.unsplash;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UnsplashProxyController {
    String unsplashUrl;
    String unsplashKey;
    RestTemplate restTemplate;

    public UnsplashProxyController(
         @Value("${api.unsplash.url}") String unsplashUrl
        ,@Value("${api.unsplash.key}") String unsplashKey
        ,RestTemplate restTemplate
    ) {
        this.unsplashUrl = unsplashUrl;
        this.unsplashKey = unsplashKey;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/Unsplash/photos/{id}")
    public ResponseEntity<String> getPhotosById(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",  "Client-ID " + unsplashKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        return restTemplate.exchange(unsplashUrl + "photos/" + id, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/api/Unsplash/photos/{id}/download")
    public ResponseEntity<String> incrementDownload(
         @PathVariable String id
        ,@RequestParam Map<String, String> allParams) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",  "Client-ID " + unsplashKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String queryString = allParams.entrySet().stream().map(entry -> 
            entry.getKey() + "=" + entry.getValue()
        ).collect(Collectors.joining("&"));

        String urlWithParams = unsplashUrl + "photos/" + id + "/download?" + queryString;
        
        return restTemplate.exchange(urlWithParams, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/api/Unsplash/search/photos")
    public ResponseEntity<String> getPhotosByKeyWords(
         @RequestParam(name="query", required=false, defaultValue="") String query
        ,@RequestParam(name="per_page", required=false, defaultValue = "5") int perPage) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",  "Client-ID " + unsplashKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String urlWithParam = unsplashUrl + "search/photos?query=" + query + "&per_page=" + perPage;

        return restTemplate.exchange(urlWithParam, HttpMethod.GET, entity, String.class);
    }

    
}
