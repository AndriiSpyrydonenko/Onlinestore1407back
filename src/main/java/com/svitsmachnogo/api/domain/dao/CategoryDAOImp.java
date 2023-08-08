package com.svitsmachnogo.api.domain.dao;


import com.svitsmachnogo.api.domain.entity.Category;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImp implements CategoryDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        Session session = entityManager.unwrap(Session.class);
        List<Category> categories = session.createQuery("from Category").getResultList();
        return categories;
    }
}
