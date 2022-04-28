package model.dao;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class DaoBase<T extends Serializable> {

    private Class<T> instance;
    protected EntityManager entityManager;

    protected DaoBase(Class<T> instance) {
        this.entityManager = getEntityManager();
        this.instance = instance;
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bancolojacrud");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public void create(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public T createReturnId(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.refresh(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return entity;
    }

    public T retrieve(Integer id) {
        return entityManager.find(instance, id);
    }

    public void update(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void delete(T entity) {
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.find(instance, instance.getName());
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            T entity = retrieve(id);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public List<T> retrieveAll() {
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(instance);
        criteria.select(criteria.from(instance));
        return entityManager.createQuery(criteria).getResultList();
    }
}