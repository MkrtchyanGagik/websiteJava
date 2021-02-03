package com.company.dao;

import com.company.hibernate.HibernateUtil;
import com.company.model.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BookDao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();

    public void addBook(Book book){


        session.beginTransaction();
        session.save(book);

        session.getTransaction().commit();
    }

    public List<Book> viewAllBooks(){
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = criteriaBuilder.createQuery(Book.class);
        Root<Book>  rootEntry=cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        TypedQuery<Book> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public Book getBookByTittle(String tittle){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        /** create a CriteriaQuery which returns book Objects **/
        CriteriaQuery<Book> cq = builder.createQuery(Book.class);
        /** fetch the book Entity **/
        Root<Book> book = cq.from(Book.class);
        /** select the book entity**/
        cq.select(book);
        /** add a restriction to fetch only book with ID= 1003 **/
        cq.where(builder.equal(book.get("tittle"),tittle));
        /** fetch the book result **/
        Book bookResult = session.createQuery(cq).getSingleResult();

return bookResult;
    }
}
