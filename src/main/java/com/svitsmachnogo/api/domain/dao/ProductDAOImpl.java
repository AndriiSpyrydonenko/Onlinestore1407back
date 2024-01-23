package com.svitsmachnogo.api.domain.dao;

import com.svitsmachnogo.api.component.PriceFilter;
import com.svitsmachnogo.api.domain.dao.abstractional.ProductDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final EntityManager entityManager;

    @Override
    public List<Product> findByPriorityForMainPage() {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("select p from Product p " +
                "join fetch p.pictures " +
                "join fetch p.packaging " +
                "where p.priorityScore > 0 " +
                "order by p.priorityScore");
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public List<Product> findForDiscountBlock() {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("from Product p " +
                "join fetch p.pictures " +
                "where p.discountPercent > 0 order by p.discountPercent ");
        return query.getResultList();
    }

    @Override
    public Product findById(int id) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        return session.get(Product.class, id);
    }

    @Override
    public List<Product> findAllByRatingWithLimit(int lim) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("from Product p" +
                " join fetch p.pictures " +
                " join fetch p.packaging " +
                " where p.priorityScore = 0" +
                " order by p.rating desc");

        return query
                .setMaxResults(lim)
                .getResultList();
    }

    @Override
    public List<Product> findByPartName(String partName) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("from Product p where p.name like :param ")
                .setParameter("param", "%" + partName + "%");
        return query.getResultList();
    }

    @Override
    public Set<Product> findAllByCategoryId(String categoryId) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("from Product p where p.category.id = :param ")
                .setParameter("param", categoryId);

        return (Set<Product>) query
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public PriceFilter findMinAndMaxPrice(String categoryId) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        PriceFilter priceFilter = new PriceFilter();

        double min = (double) session.createQuery(
                "select min(p.minPrice) from Product p where category.id = :param")
                .setParameter("param", categoryId)
                .uniqueResult();
        double max = (double) session.createQuery(
                "select max(p.minPrice) from Product p where category.id = :param")
                .setParameter("param", categoryId)
                .uniqueResult();
        priceFilter.setCategoryId(categoryId);
        priceFilter.setMinPrice(min);
        priceFilter.setMaxPrice(max);
        return priceFilter;
    }

    @Override
    public void saveProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        session.persist(product);
    }
}
