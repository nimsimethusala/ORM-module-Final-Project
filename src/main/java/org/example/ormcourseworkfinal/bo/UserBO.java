package org.example.ormcourseworkfinal.bo;

import org.example.ormcourseworkfinal.dto.UserDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO userDTO);

    String generateNextUserId();

    List<UserDTO> getAllUsers();

    boolean checkUsername(String username);

    boolean deleteUser(String userId);

    ArrayList<String> getHashPassword(String username);

    boolean updateUser(UserDTO userDTO);

    UserDTO getUser(String username) throws IOException;
}
