package com.example.courseprogectbackend.search;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
@Transactional
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManager entityManager;

    public BuildSearchIndex(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SneakyThrows
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        var fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }
}
