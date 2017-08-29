package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class OrderService {

    private static final Logger log = LogManager.getLogger(OrderService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public OrderService(){}

    @Transactional
    public void save(Order order) {
        log.debug("Trying to save order.");
        entityManager.persist(order);
    }

}