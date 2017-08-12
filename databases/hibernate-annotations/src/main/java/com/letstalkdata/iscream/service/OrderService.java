package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class OrderService {

    private EntityManager entityManager;

    @Autowired
    public OrderService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

}