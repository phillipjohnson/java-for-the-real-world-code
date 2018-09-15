package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Ingredient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    private SessionFactory sessionFactory;

    @Autowired
    public IngredientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Ingredient> getFlavors() {
        return getIngredients(Ingredient.Type.ICE_CREAM);
    }

    public List<Ingredient> getToppings() {
        return getIngredients(Ingredient.Type.TOPPING);
    }

    private List<Ingredient> getIngredients(Ingredient.Type type) {
        var sql = "select i from Ingredient i where type =:type";
        try(var session = sessionFactory.openSession()) {
            var query = session.createQuery(sql);
            query.setParameter("type", type);
            @SuppressWarnings("unchecked")
            var ingredients = (List<Ingredient>) query.list();
            return ingredients;
        } // Session is auto-closed
    }

    public Ingredient getIngredientById(int id) {
        try(var session = sessionFactory.openSession()) {
            return session.get(Ingredient.class, id);
        } // Session is auto-closed
    }
}
