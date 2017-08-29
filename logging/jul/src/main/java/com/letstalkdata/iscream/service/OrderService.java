package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
public class OrderService {

    private static final Logger log =
            Logger.getLogger(OrderService.class.getPackage().getName());

    @PersistenceContext
    private EntityManager entityManager;

    public OrderService(){}

    @Transactional
    public void save(Order order) {
        log.fine("Trying to save order.");
        entityManager.persist(order);
    }

}