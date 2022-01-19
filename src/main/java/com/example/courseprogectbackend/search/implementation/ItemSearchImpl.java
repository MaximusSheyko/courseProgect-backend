package com.example.courseprogectbackend.search.implementation;

import com.example.courseprogectbackend.model.Item;
import com.example.courseprogectbackend.search.EntitySearch;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemSearchImpl implements EntitySearch<Item> {

    private final EntityManager manager;

    public ItemSearchImpl(EntityManager entityManager) {
        this.manager = entityManager;
    }

    @Override
    public List<Item> search(String text, String... fields) {
        var fullTextEntityManager = Search.getFullTextEntityManager(manager);
        var queryBuilder = createQueryBuilder(fullTextEntityManager);
        var query = createQuery(queryBuilder, text, fields);
        return fullTextEntityManager.createFullTextQuery(query, Item.class).getResultList();
    }

    private QueryBuilder createQueryBuilder(FullTextEntityManager manager) {
        return manager.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
    }

    private Query createQuery(QueryBuilder queryBuilder, String text, String... fields) {
        return queryBuilder.keyword().onFields(fields).matching(text).createQuery();
    }
}
