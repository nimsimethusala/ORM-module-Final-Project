package org.example.ormcourseworkfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.UserBO;
import org.example.ormcourseworkfinal.dto.UserDTO;
import org.example.ormcourseworkfinal.tm.StudentTm;
import org.example.ormcourseworkfinal.tm.UserTm;
import org.example.ormcourseworkfinal.util.CheckCredential;

import java.util.ArrayList;
import java.util.List;

public class UserFormController {

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private Label lblUserId;

    @FXML
    private AnchorPane rootUser;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        generateNextUserId();
        setCellValueFactory();
        loadAllUsers();
    }

    private void loadAllUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();

        try {
            List<UserDTO> userDTOS = userBO.getAllUsers();
            for (UserDTO user : userDTOS){
                UserTm userTm = new UserTm(user.getUserId(), user.getUsername());
                obList.add(userTm);
            }
            tblUser.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    }

    private void generateNextUserId() {
        String nextId = userBO.generateNextUserId();
        lblUserId.setText(nextId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String userId = lblUserId.getText();

        boolean isDelete = userBO.deleteUser(userId);

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "User is Deleted...!").show();
            clearFields();
            tblUser.refresh();
        }
    }

    private void clearFields() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String userId = lblUserId.getText();
        String username = txtUsername.getText();
        String plainPassword = txtPassword.getText();

        boolean isUsernameSimilar = userBO.checkUsername(username);
        ArrayList<String> hashPasswords = userBO.getHashPassword(username);
        boolean isPasswordSimilar = CheckCredential.checkPassword(plainPassword, hashPasswords);

        String hashPassword = CheckCredential.hashPassword(plainPassword);
        UserDTO userDTO = new UserDTO(userId, username, hashPassword);

        if (isUsernameSimilar) {
            if (isPasswordSimilar) {
                boolean isSaved = userBO.saveUser(userDTO);

                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION, "New User is Saved...!").show();
                    clearFields();
                    tblUser.refresh();
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Password duplicated...!").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Username duplicated...!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String userId = lblUserId.getText();
        String username = txtUsername.getText();
        String plainPassword = txtPassword.getText();

        boolean isUsernameSimilar = userBO.checkUsername(username);
        ArrayList<String> hashPasswords = userBO.getHashPassword(username);
        boolean isPasswordSimilar = CheckCredential.checkPassword(plainPassword, hashPasswords);

        String hashPassword = CheckCredential.hashPassword(plainPassword);
        UserDTO userDTO = new UserDTO(userId, username, hashPassword);

        if (isUsernameSimilar) {
            if (isPasswordSimilar) {
                boolean isSaved = userBO.updateUser(userDTO);

                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION, "New User is Updated...!").show();
                    clearFields();
                    tblUser.refresh();
                }
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Password duplicated...!").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Username duplicated...!").show();
        }
    }

    @FXML
    void tblUserOnAction(MouseEvent event) {
        int index = tblUser.getSelectionModel().getSelectedIndex();
        UserTm userTm = tblUser.getItems().get(index);

        lblUserId.setText(userTm.getUserId());
    }

}
