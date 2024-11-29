package org.example.ormcourseworkfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.ormcourseworkfinal.MainApplication;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.UserBO;
import org.example.ormcourseworkfinal.dto.UserDTO;
import org.example.ormcourseworkfinal.util.CheckCredential;

import java.io.IOException;
import java.util.ArrayList;

public class LoginPageControllerForm {

    public PasswordField txtPassword;
    @FXML
    private AnchorPane rootLogin;

    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        System.out.println(username);

        UserDTO userDTO = null;
        try {
            userDTO = userBO.getUser(username);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        System.out.println(userDTO.getPassword());

        try {
            if (username.equals("Admin")) {
                if (CheckCredential.verifyPassword(password,userDTO.getPassword())) {
                    AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/AdminDashboardForm.fxml"));

                    Scene scene = new Scene(rootNode);

                    Stage stage = (Stage) this.rootLogin.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("Dashboard Form");
                }else {
                    new Alert(Alert.AlertType.INFORMATION, "Password is incorrect...!").show();
                }

            }else {
                if (CheckCredential.verifyPassword(password,userDTO.getPassword())) {

                    AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/UserDashboardForm.fxml"));
                    Scene scene = new Scene(rootNode);

                    Stage stage = (Stage) this.rootLogin.getScene().getWindow();
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.setTitle("Dashboard Form");
                }else {
                    new Alert(Alert.AlertType.INFORMATION, "Password is incorrect...!").show();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isPasswordVisible = false;

    public void btnPasswordOnAction(MouseEvent actionEvent) {
        isPasswordVisible = !isPasswordVisible;

        if (!isPasswordVisible) {
            txtPwd.setVisible(false);
            txtPassword.setVisible(true);
            txtPassword.setText(txtPwd.getText());
        }else {
            txtPwd.setVisible(true);
            txtPassword.setVisible(false);
            txtPwd.setText(txtPassword.getText());
        }
    }
}
