package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class OrderService {

    private static final Log log = LogFactory.getLog(OrderService.class);

    @PersistenceContext
    private EntityManager entityManager;

    public OrderService(){}

    @Transactional
    public void save(Order order) {
        log.debug("Trying to save order.");
        entityManager.persist(order);
    }

}