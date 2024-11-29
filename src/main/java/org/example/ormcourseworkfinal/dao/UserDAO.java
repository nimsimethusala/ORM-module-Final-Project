package org.example.ormcourseworkfinal.dao;

import org.example.ormcourseworkfinal.entity.User;

import java.io.IOException;
import java.util.ArrayList;

public interface UserDAO extends CrudDAO<User> {

    boolean checkUsername(String username);

    ArrayList<String> getHashPasswords(String username);

    User getUser(String username) throws IOException;
}
