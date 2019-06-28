package com.project.bemobi.challenge.repository;

import com.project.bemobi.challenge.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface URLRepository extends JpaRepository<URL, Long> {

    URL findByAlias(String alias);

    List<URL> findTop10UrlByOrderByViewDesc();
}