package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Ingredient;
import com.letstalkdata.iscream.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Mapper
@Service
public class OrderService {

    private SqlSession sqlSession;

    @Autowired
    public OrderService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Transactional
    public void save(Order order) {
        saveOrder(order);
        saveLineItem(order.getId(), order.getFlavor(), order.getScoops());
        order.getToppings()
                .forEach(topping -> saveLineItem(order.getId(), topping, 1));
    }

    private void saveOrder(Order order) {
        sqlSession.update("createPurchase", order);
    }

    private void saveLineItem(int orderId, Ingredient ingredient, int units) {
        Map<String, Object> params = new HashMap<>();
        params.put("purchaseId", orderId);
        params.put("ingredientId", ingredient.getId());
        params.put("units", units);
        sqlSession.insert("createPurchaseLineItem", params);
    }

}