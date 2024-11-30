package org.example.ormcourseworkfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.CourseBO;
import org.example.ormcourseworkfinal.bo.StudentBO;
import org.example.ormcourseworkfinal.dto.CourseDTO;
import org.example.ormcourseworkfinal.dto.StudentDTO;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.tm.CourseTm;
import org.example.ormcourseworkfinal.tm.StudentTm;
import org.example.ormcourseworkfinal.util.Regex;
import org.example.ormcourseworkfinal.util.TextFeildRegex;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CourseFormController {
    @FXML
    public Label lblCourseId;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colProgramFee;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private AnchorPane rootProgram;

    @FXML
    private TableView<CourseTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtProgramName;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize() {
        generateNextCourseId();
        setCellValueFactory();
        loadAllCourse();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            String courseId = lblCourseId.getText();

            boolean isDelete = courseBO.deleteCourse(courseId);
            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Course is Deleted...!").show();
                refreshForm();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String courseId = lblCourseId.getText();
        String courseName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double programFee = Double.parseDouble(txtProgramFee.getText());

        CourseDTO courseDTO = new CourseDTO(courseId, courseName, duration, programFee);

        boolean isSaved = courseBO.saveCourse(courseDTO);

        if (isSaved){
            new Alert(Alert.AlertType.INFORMATION, "New Course is Saved...!").show();
            refreshForm();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String courseId = lblCourseId.getText();
        String courseName = txtProgramName.getText();
        String duration = txtDuration.getText();
        double programFee = Double.parseDouble(txtProgramFee.getText());

        CourseDTO courseDTO = new CourseDTO(courseId, courseName, duration, programFee);

        boolean isUpdated = courseBO.updateCourse(courseDTO);

        if (isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION, "Course is Updated...!").show();
            refreshForm();
        }
    }

    private void refreshForm() {
        clearFields();
        loadAllCourse();
        generateNextCourseId();
    }

    private void clearFields() {
        txtProgramName.setText("");
        txtDuration.setText("");
        txtProgramFee.setText("");
    }

    private void setCellValueFactory(){
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("CourseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        colProgramFee.setCellValueFactory(new PropertyValueFactory<>("programFee"));
    }

    private void loadAllCourse(){
        ObservableList<CourseTm> obList = FXCollections.observableArrayList();

        try {
            List<CourseDTO> courseList = courseBO.getAllCourse();
            for (CourseDTO course : courseList){
                CourseTm courseTm = new CourseTm(course.getCourseId(), course.getCourseName(), course.getDuration(), course.getProgramFee());
                obList.add(courseTm);
            }
            tblProgram.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextCourseId() {
        String nextId = courseBO.generateNextCourseId();
        System.out.println("curse id : " + nextId);
        lblCourseId.setText(nextId);
    }

    public void tblProgramOnAction(MouseEvent mouseEvent) {
        int index = tblProgram.getSelectionModel().getSelectedIndex();
        CourseTm courseTm = tblProgram.getItems().get(index);

        lblCourseId.setText(courseTm.getCourseId());
        txtProgramName.setText(courseTm.getCourseName());
        txtDuration.setText(courseTm.getDuration());
        txtProgramFee.setText(String.valueOf(courseTm.getProgramFee()));
    }

    @FXML
    public void txtProgramNameOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.NAME,txtProgramName);
    }

    @FXML
    public void txtProgramFeeOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtProgramFee);
    }
}
