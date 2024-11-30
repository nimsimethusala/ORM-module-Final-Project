package org.example.ormcourseworkfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.ormcourseworkfinal.bo.BOFactory;
import org.example.ormcourseworkfinal.bo.PaymentBO;
import org.example.ormcourseworkfinal.bo.RegistrationBO;
import org.example.ormcourseworkfinal.bo.StudentBO;
import org.example.ormcourseworkfinal.dto.PaymentDTO;
import org.example.ormcourseworkfinal.dto.RegistrationDTO;
import org.example.ormcourseworkfinal.entity.Registration;
import org.example.ormcourseworkfinal.tm.PaymentTm;
import org.example.ormcourseworkfinal.util.Regex;
import org.example.ormcourseworkfinal.util.TextFeildRegex;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaymentFormController {
    @FXML
    public TextField txtPayment;

    @FXML
    private ComboBox<String> cmbRegistrationId;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colProgramName;

    @FXML
    private TableColumn<?, ?> colRegistrationId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblNewBalance;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblProgramFee;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblStudentId;

    @FXML
    private Label lblStudentName;

    @FXML
    private Label lblUpfrontPayment;

    @FXML
    private AnchorPane rootPayment;

    @FXML
    private TableView<PaymentTm> tblPayment;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);
        lblDate.setText(formattedDate);

        generateNextPaymentId();
        getAllRegistrationId();
        setCellValueFactory();
        loadAllPayments();
    }

    private void getAllRegistrationId() {
        ArrayList<RegistrationDTO> registrationDTOS = (ArrayList<RegistrationDTO>) registrationBO.getAllRegistrations();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (RegistrationDTO registrationDTO : registrationDTOS){
            obList.add(registrationDTO.getRegistrationId());
        }

        cmbRegistrationId.setItems(obList);
    }

    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDTO> paymentDTOS = paymentBO.getAllPayments();
            for (PaymentDTO payment : paymentDTOS){
                PaymentTm paymentTm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getRegistration().getStudent().getStudentId(),
                        payment.getRegistration().getCourse().getCourseName(),
                        payment.getAmount(),
                        payment.getBalance(),
                        payment.getStatus()
                );
                obList.add(paymentTm);
            }
            tblPayment.setItems(obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colRegistrationId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("programName"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void generateNextPaymentId() {
        String nextId = paymentBO.generateNextPaymentId();
        lblPaymentId.setText(nextId);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        String registrationId = (String) cmbRegistrationId.getValue();
        double programFee = Double.parseDouble(lblProgramFee.getText());
        double newBalance = Double.parseDouble(lblNewBalance.getText());
        double payment = Double.parseDouble(txtPayment.getText());
        String date = lblDate.getText();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date Date = dateFormat.parse(date);

            RegistrationDTO registrationDTO = registrationBO.getRegistrationDetail(registrationId);
            Registration registration = new Registration(registrationDTO.getRegistrationId(), registrationDTO.getDate(), registrationDTO.getUpfrontPayment(), registrationDTO.getStudent(), registrationDTO.getCourse());
            String status = paymentBO.getStatus(newBalance, programFee);

            PaymentDTO paymentDTO = new PaymentDTO(paymentId, payment, newBalance, Date, status, registration);

            boolean isSaved = paymentBO.savePayment(paymentDTO);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Payment is Saved...!").show();
                loadAllPayments();
                generateNextPaymentId();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void cmbRegistrationIdOnAction(ActionEvent actionEvent) {
        setStudentId();
        setStudentName();
        setProgramName();
        setProgramFee();
        setUpfrontPayment();
        getPreviousInstallments();
    }

    private void getPreviousInstallments() {
        String registrationId = (String) cmbRegistrationId.getValue();

        double balance = paymentBO.getPreviousInstallments(registrationId);
        lblBalance.setText(String.valueOf(balance));
    }

    private void setUpfrontPayment() {
        String registrationId = (String) cmbRegistrationId.getValue();

        double upfrontPayment = registrationBO.getUpfrontPayment(registrationId);
        lblUpfrontPayment.setText(String.valueOf(upfrontPayment));
    }

    private void setProgramFee() {
        String registrationId = (String) cmbRegistrationId.getValue();

        double programFee = registrationBO.getProgramFee(registrationId);
        lblProgramFee.setText(String.valueOf(programFee));
    }

    private void setProgramName() {
        String registrationId = (String) cmbRegistrationId.getValue();

        String programName = registrationBO.getProgramName(registrationId);
        lblProgramName.setText(programName);
    }

    private void setStudentName() {
        String studentId = lblStudentId.getText();

        String studentName = studentBO.getStudentName(studentId);
        lblStudentName.setText(studentName);
    }

    private void setStudentId() {
        String registrationId = (String) cmbRegistrationId.getValue();

        String studentId = registrationBO.getStudentId(registrationId);
        lblStudentId.setText(studentId);
    }

    @FXML
    public void txtPaymentOnAction(ActionEvent actionEvent) {
        Regex.setTextColor(TextFeildRegex.PRICE,txtPayment);
        calculateNewBalance();
    }

    private void calculateNewBalance() {
        double balance = Double.parseDouble(lblBalance.getText());
        double amount = Double.parseDouble(txtPayment.getText());

        double newBalance = paymentBO.getNewBalance(balance, amount);
        lblNewBalance.setText(String.valueOf(newBalance));
    }

    @FXML
    public void btnBackOnAction(ActionEvent actionEvent) {
        try {
            AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/org/example/ormcourseworkfinal/AdminDashboardForm.fxml"));
            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.rootPayment.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Main Form");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
