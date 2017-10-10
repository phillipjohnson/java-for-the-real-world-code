package com.letstalkdata.iscream.service;

import com.letstalkdata.iscream.domain.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(order);
            tx.commit();
        } // Session is auto-closed
    }

}
