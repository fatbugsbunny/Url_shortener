package com.example.url_shortener;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends CrudRepository<URL, Integer> {
    Optional<URL> findByShortenedUrl(String shortUrl);
    Optional<URL> findByOriginalUrl(String originalUrl);
}
