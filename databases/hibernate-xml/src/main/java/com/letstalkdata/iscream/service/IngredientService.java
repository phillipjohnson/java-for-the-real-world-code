package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Ingredient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private SessionFactory sessionFactory;

    public IngredientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired


    public List<Ingredient> getFlavors() {
        return getIngredients(Ingredient.Type.ICE_CREAM);
    }

    public List<Ingredient> getToppings() {
        return getIngredients(Ingredient.Type.TOPPING);
    }

    private List<Ingredient> getIngredients(Ingredient.Type type) {
        String sql = "select i from Ingredient i where type =:type";
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(sql);
            query.setParameter("type", type);
            @SuppressWarnings("unchecked")
            List<Ingredient> ingredients = (List<Ingredient>) query.list();
            return ingredients;
        } // Session is auto-closed
    }

    public Ingredient getIngredientById(int id) {
        try(Session session = sessionFactory.openSession()) {
            return session.get(Ingredient.class, id);
        } // Session is auto-closed
    }
}
