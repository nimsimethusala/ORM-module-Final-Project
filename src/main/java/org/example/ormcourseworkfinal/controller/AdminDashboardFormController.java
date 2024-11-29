package org.example.ormcourseworkfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminDashboardFormController {
    @FXML
    public AnchorPane rootAdminDashboard;

    @FXML
    private AnchorPane rootMain;

    @FXML
    void btnCourse(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/CourseForm.fxml"));
            rootMain.getChildren().clear();
            rootMain.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboard(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/AdminDashboardForm.fxml"));
            rootAdminDashboard.getChildren().clear();
            rootAdminDashboard.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPayment(ActionEvent event) {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/PaymentForm.fxml"));
            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.rootAdminDashboard.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Payment Form");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegistration(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/RegistrationForm.fxml"));
            rootMain.getChildren().clear();
            rootMain.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStudent(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/StudentForm.fxml"));
            rootMain.getChildren().clear();
            rootMain.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUser(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/UserForm.fxml"));
            rootMain.getChildren().clear();
            rootMain.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnLogout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to logout?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();

        try {
            if (result.isPresent() && result.get() == yesButton) {
                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/LoginPage.fxml"));
                Scene scene = new Scene(rootNode);

                Stage stage = (Stage) this.rootAdminDashboard.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard Form");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
