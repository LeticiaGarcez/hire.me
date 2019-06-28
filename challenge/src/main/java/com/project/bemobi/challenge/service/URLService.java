package com.project.bemobi.challenge.service;

import com.project.bemobi.challenge.model.URL;
import com.project.bemobi.challenge.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.List;

@Component
public class URLService {

    @Autowired
    private URLRepository urlRepository;
    private static final String FINAL_URL = "http://shortener/u/%s";
    private String url;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String shortnerURL(String url){

        StringBuilder alias = new StringBuilder();

        for (int i = 0; i < 10; i++) {

            Random character = new Random();
            alias.append(ALPHABET.charAt(character.nextInt(63) % BASE));

        }

        return alias.reverse().toString();
    }


    public Object shortWithAlias(String url, String alias) {

        long ini = System.currentTimeMillis();

        URL urlExists = this.urlRepository.findByAlias(alias);

        if (urlExists != null) {

            Object[] result = {String.format("ERR_CODE: 001 - CUSTOM ALIAS ALREADY EXISTS")};
            return result;

        } else {

            URL novaUrl = new URL();
            novaUrl.setAlias(alias);
            novaUrl.setUrl(url);

            this.urlRepository.save(novaUrl);

            Object[] result = {String.format(FINAL_URL, alias), " Original URL: " + url, " Time_taken: " + (System.currentTimeMillis() - ini + "ms")};

            return result;
        }
    }


    public Object shortWithoutAlias(String url) {

        long ini = System.currentTimeMillis();

        String short_url = this.shortnerURL(url);

        URL newUrl = new URL();
        newUrl.setAlias(short_url);
        newUrl.setUrl(url);

        Object[] result = {String.format(FINAL_URL, short_url), " Original URL: " + url, " Time_taken: " + (System.currentTimeMillis() - ini + "ms")};

        this.urlRepository.save(newUrl);

        return result;
    }


    public Object searchByAlias(String alias) {

        long ini = System.currentTimeMillis();

        URL aliasExists = this.urlRepository.findByAlias(alias);

        if (aliasExists == null) {

            Object[] result = {String.format("ERR_CODE: 002 - SHORTENED URL NOT FOUND")};
            return result;

        } else {

            int view = aliasExists.getView() + 1;
            aliasExists.setView(view);

            this.urlRepository.save(aliasExists);
            
            Object[] result = {"URL shortened: " + aliasExists.getUrl(), " Time_taken: " + (System.currentTimeMillis() - ini + "ms")};

            return result;
        }
    }


    public List top10UrlAcessed() {

        List<URL> topUrl = this.urlRepository.findTop10UrlByOrderByViewDesc();

        return topUrl;

    }

}

