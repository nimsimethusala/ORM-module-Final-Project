package org.example.ormcourseworkfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.StudentBO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.tm.StudentTm;
import org.example.ormcourseworkfinal.util.Regex;
import org.example.ormcourseworkfinal.util.TextFeildRegex;

import java.sql.SQLException;
import java.util.List;

public class StudentFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSudentID;

    @FXML
    private Label lblStudentId;

    @FXML
    private AnchorPane rootStudent;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtContact.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtAddress.requestFocus();
            }
        });

        generateNextCustomerId();
        setCellValueFactory();
        loadAllCustomer();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();

        boolean isDelete = studentBO.deleteStudent(studentId);

        if (isDelete){
            new Alert(Alert.AlertType.CONFIRMATION, "Student is Deleted...!").show();
            clearFields();
            tblStudent.refresh();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String studentName = txtName.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, contact, address, email);

        boolean isSaved = studentBO.saveStudent(studentDTO);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "New Student is Saved...!").show();
            clearFields();
            tblStudent.refresh();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String studentId = lblStudentId.getText();
        String studentName = txtName.getText();
        int contact = Integer.parseInt(txtContact.getText());
        String address = txtAddress.getText();
        String email = txtEmail.getText();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, contact, address, email);

        boolean isUpdated = studentBO.updateStudent(studentDTO);

        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Student is Updated...!").show();
            clearFields();
            tblStudent.refresh();
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    private void setCellValueFactory(){
        colSudentID.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAllCustomer(){
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentList = studentBO.getAllStudents();
            for (StudentDTO student : studentList){
                StudentTm studentTm = new StudentTm(student.getStudentId(), student.getName(), student.getContact(), student.getAddress(), student.getEmail());
                obList.add(studentTm);
            }
            tblStudent.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextCustomerId() {
        String nextId = studentBO.generateNextStudentId();
        System.out.println(nextId);
        lblStudentId.setText(nextId);
    }

    public void tblStudentOnAction(MouseEvent mouseEvent) {
        int index = tblStudent.getSelectionModel().getSelectedIndex();
        StudentTm studentTm = tblStudent.getItems().get(index);

        lblStudentId.setText(studentTm.getStudentId());
        txtName.setText(studentTm.getName());
        txtContact.setText(String.valueOf(studentTm.getContact()));
        txtAddress.setText(studentTm.getAddress());
        txtEmail.setText(studentTm.getEmail());
    }

    @FXML
    public void txtNameOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtName);
    }

    @FXML
    public void txtEmailOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.EMAIL,txtEmail);
    }

    @FXML
    public void txtContactOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.CONTACT,txtContact);
    }
}
