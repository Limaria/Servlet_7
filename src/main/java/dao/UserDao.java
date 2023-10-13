package dao;

import connection.ConnectionDB;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class UserDao {
    public User getUser(String login) {
        Session session = ConnectionDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        User user = session.byNaturalId(User.class).using("login", login).load();
        transaction.commit();
        session.close();
        return user;
    }

    public List<User> getAll () {
        Session session = ConnectionDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        Query<User> query = session.createQuery(criteriaQuery);
        List<User> results = query.getResultList();

        transaction.commit();
        session.close();

        return results;
    }
    public boolean save (User user) {
        boolean isSaved = true;
        Session session = ConnectionDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(user);
        } catch (PersistenceException e) {
            isSaved = false;
        }
        transaction.commit();
        session.close();
        return isSaved;
    }

    public boolean update (User user) {
        boolean isSaved = false;
        Session session = ConnectionDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(user);
        } catch (PersistenceException e) {
            isSaved = false;
        }
        transaction.commit();
        session.close();
        return isSaved;
    }

    public boolean delete (User user) {
        boolean isSaved = false;
        Session session = ConnectionDB.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(user);
        } catch (PersistenceException e) {
            isSaved = false;
        }
        transaction.commit();
        session.close();
        return isSaved;
    }
}
