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
import org.example.ormcourseworkfinal.bo.CourseBO;
import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.bo.StudentBO;
import org.example.ormcourseworkfinal.dto.CourseDTO;
import org.example.ormcourseworkfinal.dto.RegistrationDTO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Student;
import org.example.ormcourseworkfinal.tm.RegistrationTm;
import org.example.ormcourseworkfinal.tm.StudentTm;
import org.example.ormcourseworkfinal.util.Regex;
import org.example.ormcourseworkfinal.util.TextFeildRegex;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationFormController {
    @FXML
    public TableColumn<?, ?> colRegistrationId;

    @FXML
    private ComboBox<String> cmbCourseId;

    @FXML
    private ComboBox<String> cmbStudentId;

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
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);
        lblDate.setText(formattedDate);

        generateNextRegistrationId();
        getAllStudentId();
        getAllCourseId();
        setCellValueFactory();
        loadAllRegistration();

    }

    private void getAllCourseId() {
        ArrayList<CourseDTO> courseDTOS = (ArrayList<CourseDTO>) courseBO.getAllCourse();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (CourseDTO courseDTO : courseDTOS){
            obList.add(courseDTO.getCourseId());
        }

        cmbCourseId.setItems(obList);
    }

    private void getAllStudentId() {
        ArrayList<StudentDTO> studentDTOS = (ArrayList<StudentDTO>) studentBO.getAllStudent();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : studentDTOS){
            obList.add(studentDTO.getStudentId());
        }

        cmbStudentId.setItems(obList);
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String regId = lblRegistrationId.getText();
        String studentId = (String) cmbStudentId.getValue();
        String courseId = (String) cmbCourseId.getValue();
        double upfrontPayment = Double.parseDouble(txtUpfrontPayment.getText());

        String Date = lblDate.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(Date);

            StudentDTO studentDTO = studentBO.getStudentDetail(studentId);
            Student student = new Student(studentDTO.getStudentId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getAddress(), studentDTO.getEmail());

            CourseDTO courseDTO = courseBO.getCourseDetail(courseId);
            Course course = new Course(courseDTO.getCourseId(), courseDTO.getCourseName(), courseDTO.getDuration(), courseDTO.getProgramFee());

            RegistrationDTO registrationDTO = new RegistrationDTO(regId, date, upfrontPayment, student, course);

            boolean isSaved = registrationBO.saveStudentRegistration(registrationDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration is Successfully completed...!").show();
                clearFields();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void loadAllRegistration(){
        ObservableList<RegistrationTm> obList = FXCollections.observableArrayList();

        try {
            List<RegistrationDTO> registrationDTOS = registrationBO.getAllRegistrations();
            for (RegistrationDTO registration : registrationDTOS){
                RegistrationTm registrationTm = new RegistrationTm(registration.getRegistrationId(), registration.getStudent().getStudentId(), registration.getStudent().getName(), registration.getCourse().getCourseId(), registration.getCourse().getCourseName(), registration.getUpfrontPayment());
                obList.add(registrationTm);
            }
            tblRegistration.setItems(obList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void generateNextRegistrationId() {
        String nextId = registrationBO.generateNextRegistrationId();
        System.out.println(nextId);
        lblRegistrationId.setText(nextId);
    }

    private void setCellValueFactory(){
        colRegistrationId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colUpfrontPayment.setCellValueFactory(new PropertyValueFactory<>("upfrontPayment"));
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
        setStudentName();
    }

    private void setStudentName(){
        String studentId = (String) cmbStudentId.getValue();

        String studentName = studentBO.studentName(studentId);
        lblStudentName.setText(studentName);
    }

    public void cmdCourseIdOnAction(ActionEvent actionEvent) {
        setCourseName();
    }

    private void setCourseName(){
        String courseId = (String) cmbCourseId.getValue();

        String courseName = courseBO.courseName(courseId);
        lblCourseName.setText(courseName);
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        lblDate.setText("");
        lblStudentName.setText("");
        lblCourseName.setText("");
        txtUpfrontPayment.setText("");
    }

    @FXML
    public void txtUpfrontPaymentOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtUpfrontPayment);
    }
}
