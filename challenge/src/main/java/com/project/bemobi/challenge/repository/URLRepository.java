package com.project.bemobi.challenge.repository;

import com.project.bemobi.challenge.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends JpaRepository<URL, Integer> {

    URL findByAlias(String alias);
}