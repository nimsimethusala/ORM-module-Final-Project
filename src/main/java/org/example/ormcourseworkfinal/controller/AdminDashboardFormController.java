package org.example.ormcourseworkfinal.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
            AnchorPane course = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/PaymentForm.fxml"));
            rootAdminDashboard.getChildren().clear();
            rootAdminDashboard.getChildren().add(course);
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

}
