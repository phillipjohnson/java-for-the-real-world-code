package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class IngredientService {

    private EntityManager em;

    @Autowired
    public IngredientService(EntityManager em) {
        this.em = em;
    }

    public List<Ingredient> getFlavors() {
        return getIngredients(Ingredient.Type.ICE_CREAM);
    }

    public List<Ingredient> getToppings() {
        return getIngredients(Ingredient.Type.TOPPING);
    }

    private List<Ingredient> getIngredients(Ingredient.Type type) {
        String sql = "select i from Ingredient i where type =:type";
        Query query = em.createQuery(sql);
        query.setParameter("type", type);
        @SuppressWarnings("unchecked")
        List<Ingredient> ingredients =
                (List<Ingredient>) query.getResultList();
        return ingredients;
    }

    public Ingredient getIngredientById(int id) {
        return em.find(Ingredient.class, id);
    }
}
