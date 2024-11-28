package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.User;

import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {

    boolean checkUsername(String username);

    ArrayList<String> getHashPasswords(String username);
}
