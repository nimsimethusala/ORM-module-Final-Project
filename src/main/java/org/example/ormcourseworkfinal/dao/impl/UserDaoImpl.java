package org.example.ormcourseworkfinal.dao.impl;

import org.example.ormcourseworkfinal.config.FactoryConfiguration;
import org.example.ormcourseworkfinal.dao.UserDAO;
import org.example.ormcourseworkfinal.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {

    @Override
    public boolean save(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String userId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM User WHERE userId = :userId");
        query.setParameter("userId", userId);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<User> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM User ");
        ArrayList<User> users = (ArrayList<User>) query.list();

        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public String generateNextId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT userId FROM User ORDER BY userId DESC LIMIT 1");
        String userId = (String) query.uniqueResult();

        session.close();
        return splitUserId(userId);
    }

    private String splitUserId(String userId) {
        if(userId != null) {
            String[] strings = userId.split("U0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "U00"+id;
            }else {
                if (length < 3){
                    return "U0"+id;
                }else {
                    return "U"+id;
                }
            }
        }
        return "U001";
    }

    @Override
    public boolean checkUsername(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT username FROM User");
        ArrayList<String> users = (ArrayList<String>) query.list();

        for (String user : users) {
            if (user.equals(username)) {
                return false;
            }
        }
        transaction.commit();
        session.close();
        return true;
        }

    @Override
    public ArrayList<String> getHashPasswords(String username) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT password FROM User");
        ArrayList<String> passwords = (ArrayList<String>) query.list();

        transaction.commit();
        session.close();

        return passwords;
    }

    @Override
    public User getUser(String username) throws IOException {
//        Session session = FactoryConfiguration.getInstance().getSession();
//        Transaction transaction = null;
//        User user = null;
//
//        try {
//            transaction = session.beginTransaction();
//
//            Query<User> query = session.createQuery("FROM User WHERE userId = :userId", User.class);
//            query.setParameter("userId", username);
//
//            user = query.uniqueResult();
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return user;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = null;

        try {
            String hql = "FROM User WHERE username = :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            user = query.uniqueResult();


            if (user == null) {
                throw new IOException("User not found: " + username);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e; // Re-throw the exception
        } finally {
            session.close();
        }
        return user;
    }
}
