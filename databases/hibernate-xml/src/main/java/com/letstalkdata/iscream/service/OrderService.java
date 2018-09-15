package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Order order) {
        try(var session = sessionFactory.openSession()) {
            var tx = session.beginTransaction();
            session.persist(order);
            tx.commit();
        } // Session is auto-closed
    }

}
