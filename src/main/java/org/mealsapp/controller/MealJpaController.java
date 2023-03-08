package org.mealsapp.controller;

import jakarta.persistence.EntityManager;
import org.mealsapp.model.MainModel;
import org.mealsapp.model.Meal;
import org.mealsapp.model.NoneexistentEntityException;
import org.mealsapp.model.PreexistingEntityException;

import jakarta.persistence.EntityNotFoundException;
import java.io.Serializable;

public class MealJpaController implements Serializable {

    private MainModel mainModel;
    private EntityManager entityManager;

    public MealJpaController(MainModel mainModel) {
        this.mainModel = mainModel;
        this.entityManager = mainModel.entityManager;
    }

    public void create(Meal meal) throws PreexistingEntityException, Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(meal);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            if (findMeal(meal.getIdmeal()) != null) {
                throw new PreexistingEntityException("Meal " + meal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void edit(Meal meal) throws NoneexistentEntityException, Exception {
        try {
            entityManager.getTransaction().begin();
            meal = entityManager.merge(meal);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = meal.getIdmeal();
                if (findMeal(id) == null) {
                    throw new NoneexistentEntityException("The meal with ID " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public void destroy(Integer id) throws NoneexistentEntityException {
        try {
            entityManager.getTransaction().begin();
            Meal meal;
            try {
                meal = entityManager.getReference(Meal.class, id);
                meal.getIdmeal();
            } catch (EntityNotFoundException enfe) {
                throw new NoneexistentEntityException("The meal with ID " + id + " no longer exists.", enfe);
            }
            entityManager.remove(meal);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Meal findMeal(Integer id) {
        try {
            return entityManager.find(Meal.class, id);
        } finally {
            entityManager.close();
        }
    }

}
