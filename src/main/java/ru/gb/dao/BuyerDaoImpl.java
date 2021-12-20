package ru.gb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.entity.Buyer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class BuyerDaoImpl implements BuyerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Iterable<Buyer> findAll() {
        return entityManager.createQuery("select m from Buyer m").getResultList();
    }

    @Override
    public Buyer findById(Long id) {
        TypedQuery<Buyer> query = entityManager.createNamedQuery("Buyer.findById", Buyer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public String findNameById(Long id) {
        TypedQuery<String> query = entityManager.createNamedQuery("Buyer.findNameById", String.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Buyer save(Buyer buyer) {
        if (buyer.getId() == null) {
            entityManager.persist(buyer);
        } else {
            entityManager.merge(buyer);
        }
        return buyer;
    }

    @Override
    public void deleteById(Long id) {
        Buyer buyer = new Buyer();
        buyer.setId(id);
        buyer = entityManager.merge(buyer);
        entityManager.remove(buyer);
    }
}
