package org.example.ormcourseworkfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.tm.RegistrationTm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistrationFormController {

    @FXML
    private ComboBox<?> cmbCourseId;

    @FXML
    private ComboBox<?> cmbStudentId;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colCourseName;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colUpfrontPayment;

    @FXML
    private Label lblCourseName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblRegistrationId;

    @FXML
    private Label lblStudentName;

    @FXML
    private AnchorPane rootRegistrationForm;

    @FXML
    private TableView<RegistrationTm> tblRegistration;

    @FXML
    private TextField txtUpfrontPayment;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);

    public void initialize() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);
        lblDate.setText(formattedDate);

        generateNextRegistrationId();
        setCellValueFactory();
        loadAllCustomer();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String regId = lblRegistrationId.getText();
        String studentId = (String) cmbStudentId.getValue();
        String studentName = lblStudentName.getText();
        String courseId = (String) cmbCourseId.getValue();
        String courseName = lblCourseName.getText();
        double upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText());

        String Date = lblDate.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(Date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomer(){
        String studentId = (String) cmbStudentId.getValue();
        String studentName = lblStudentName.getText();
        String courseId = (String) cmbCourseId.getValue();
        String courseName = lblCourseName.getText();
        //double upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText());

        double upfrontPayment = 0.0; // Default value
        if (txtUpfrontPayment.getText() != null && !txtUpfrontPayment.getText().trim().isEmpty()) {
            try {
                upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid upfront payment value: " + txtUpfrontPayment.getText());
                return; // Stop execution if invalid value
            }
        } else {
            System.out.println("Upfront payment field is empty!");
            return; // Stop execution if the field is empty
        }

        ObservableList<RegistrationTm> obList = FXCollections.observableArrayList();

        try {
            //List<RegistrationDTO> registrationDTOList = registrationBO.getAllStudents();
            //for (RegistrationDTO registrationDTO : registrationDTOList){
            RegistrationTm registrationTm = new RegistrationTm(studentId, studentName, courseId, courseName, upfrontPayment);
            obList.add(registrationTm);
            //}
            tblRegistration.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextRegistrationId() {
        String nextId = registrationBO.generateNextRegistrationId();
        System.out.println(nextId);
        lblRegistrationId.setText(nextId);
    }

    private void setCellValueFactory(){
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colUpfrontPayment.setCellValueFactory(new PropertyValueFactory<>("upfrontPayment"));
    }
}
