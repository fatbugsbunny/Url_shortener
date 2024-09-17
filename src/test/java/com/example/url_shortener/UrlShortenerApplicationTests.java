package com.example.url_shortener;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class UrlShortenerApplicationTests {

	@Autowired
	private URLRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void saveUrlTest(){
		URL url = new URL();
		url.setOriginalUrl("https://www.google.com");
		url.setShortenedUrl("");
		repository.save(url);

		URL foundUrl = repository.findById(url.getId()).orElse(new URL());
		assertNotNull(foundUrl);
		assertEquals(url.getOriginalUrl(), foundUrl.getOriginalUrl());
		assertEquals(url.getShortenedUrl(), foundUrl.getShortenedUrl());
	}

	@Test
	void listAllUrls(){
		URL url = new URL();
		url.setOriginalUrl("https://www.google.com");
		url.setShortenedUrl("https://tinyurl.com");
		repository.save(url);
		URL url2 = new URL();
		url2.setOriginalUrl("https://www.wikipedia.com");
		url2.setShortenedUrl("https://tinyurl23.com");
		repository.save(url2);

		Iterable<URL> iterator = repository.findAll();
		List<URL> urls = new ArrayList<>();
		iterator.forEach(urls::add);
		assertEquals(2, urls.size());
	}

	@Test
	void updateUrl(){
		URL url = new URL();
		url.setOriginalUrl("https://www.google.com");
		url.setShortenedUrl("https://tinyurl.com");
		repository.save(url);

		url.setShortenedUrl("https://tinyurl123.com");
		repository.save(url);

		URL foundUrl = repository.findById(url.getId()).orElse(new URL());
		assertEquals(foundUrl.getShortenedUrl(), url.getShortenedUrl());
	}

	@Test
	void generateShortenedUrl(){
		URLService service = new URLService(repository);
		String shortenedUrl = service.generateShortenedUrl(0);
		assertEquals("a", shortenedUrl);
	}
}
