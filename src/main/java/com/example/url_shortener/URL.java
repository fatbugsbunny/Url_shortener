package com.example.url_shortener;

import jakarta.persistence.*;


@Entity
@Table(name = "urls")
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String originalUrl;

    @Column(unique = true, nullable = false)
    private String shortenedUrl;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public User getUser() {
        return user;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
