package org.example.ormcourseworkfinal.bo.impl;

import org.example.ormcourseworkfinal.bo.UserBO;
import org.example.ormcourseworkfinal.dao.DAOFactory;
import org.example.ormcourseworkfinal.dao.UserDAO;
import org.example.ormcourseworkfinal.dto.UserDTO;
import org.example.ormcourseworkfinal.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    public String generateNextUserId() {
        return userDAO.generateNextId();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        ArrayList<User> users = userDAO.getAll();

        for (User user : users) {
            UserDTO userDTO = new UserDTO(user.getUserId(), user.getUsername(), user.getPassword());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public boolean checkUsername(String username) {
        return userDAO.checkUsername(username);
    }

    @Override
    public boolean deleteUser(String userId) {
        return userDAO.delete(userId);
    }

    @Override
    public ArrayList<String> getHashPassword(String username) {
        return userDAO.getHashPasswords(username);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getUsername(), userDTO.getPassword()));
    }

    @Override
    public UserDTO getUser(String username) throws IOException {
        User user = userDAO.getUser(username);
        System.out.println(user);
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
