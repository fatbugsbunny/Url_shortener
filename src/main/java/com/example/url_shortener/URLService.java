package com.example.url_shortener;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class URLService {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Integer BASE = ALPHABET.length();

    private final URLRepository urlRepository;

    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    
    public String addURL(String link) {
        Optional<URL> optionalURL = urlRepository.findByOriginalUrl(link);
        if(optionalURL.isPresent()){
            return optionalURL.get().getShortenedUrl();
        }

        URL url = new URL();
        url.setOriginalUrl(link);
        url.setShortenedUrl("");
        URL savedURL = urlRepository.save(url);

        Integer id = savedURL.getId();
        url.setShortenedUrl(generateShortenedUrl(id));

        urlRepository.save(url);
        return savedURL.getShortenedUrl();
    }

    public Optional<URL> getURL(String url) {
        return urlRepository.findByShortenedUrl(url);
    }

    public String generateShortenedUrl(Integer id) {
        if (id == 0) {
            return String.valueOf(ALPHABET.charAt(0));  // Base case for 0
        }

        StringBuilder base62 = new StringBuilder();

        while (id > 0) {
            int remainder = id % BASE; // Find the remainder
            base62.append(ALPHABET.charAt(remainder)); // Append corresponding character to the result
            id /= BASE; // Update num by integer division
        }

        return base62.reverse().toString();
    }
}
