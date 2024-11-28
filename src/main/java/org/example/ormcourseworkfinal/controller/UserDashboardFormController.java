package org.example.ormcourseworkfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class UserDashboardFormController {
    @FXML
    public AnchorPane rootDashboard;

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
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/UserDashboardForm.fxml"));
            rootDashboard.getChildren().clear();
            rootDashboard.getChildren().add(course);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnPayment(ActionEvent event) {
        try {
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/PaymentForm.fxml"));
            rootDashboard.getChildren().clear();
            rootDashboard.getChildren().add(course);
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
    void btnSetting(ActionEvent event) {

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

}
