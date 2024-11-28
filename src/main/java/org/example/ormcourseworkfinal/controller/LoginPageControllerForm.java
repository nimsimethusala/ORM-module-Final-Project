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
    private ImageView rootLogin;

    @FXML
    private TextField txtPwd;

    @FXML
    private TextField txtUsername;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

//        boolean isCorrect = checkCredencials(username, password);

        boolean isMatch = userBO.checkUsername(username);

        try {
//            if (isCorrect) {
                if (isMatch) {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/org/example/ormcourseworkfinal/AdminDashboardForm.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                }else {
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/org/example/ormcourseworkfinal/UserDashboardForm.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                }
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkCredencials(String username, String password) {
        boolean isUsernameSimilar = userBO.checkUsername(username);
        ArrayList<String> hashPasswords = userBO.getHashPassword(username);
        boolean isPasswordSimilar = CheckCredential.checkPassword(password, hashPasswords);

        if (isUsernameSimilar) {
            if (isPasswordSimilar) {
                return true;
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Password duplicated...!").show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Username duplicated...!").show();
        }
        return false;
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
