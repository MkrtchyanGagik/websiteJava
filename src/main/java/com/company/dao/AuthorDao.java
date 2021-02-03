package com.company.dao;

import com.company.hibernate.HibernateUtil;
import com.company.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuthorDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    public void addAuthor(Author author){
        session.beginTransaction();
        session.save(author);

        session.getTransaction().commit();
    }
}
