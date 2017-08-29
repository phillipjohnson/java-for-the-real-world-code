package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class OrderService {

    private static final Logger log =
            LoggerFactory.getLogger(OrderService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public OrderService(){}

    @Transactional
    public void save(Order order) {
        log.debug("Trying to save order.");
        entityManager.persist(order);
    }

}