package com.project.bemobi.challenge.service;

import com.project.bemobi.challenge.model.URL;
import com.project.bemobi.challenge.repository.URLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

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

            Object[] result = {String.format("err_code: 001 - CUSTOM ALIAS ALREADY EXISTS")};
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

        URL novaUrl = new URL();
        novaUrl.setAlias(short_url);
        novaUrl.setUrl(url);

        Object[] result = {String.format(FINAL_URL, short_url), " Original URL: " + url, " Time_taken: " + (System.currentTimeMillis() - ini + "ms")};

        this.urlRepository.save(novaUrl);

        return result;
    }

}