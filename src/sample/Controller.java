package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {
    Company company = new Company();

    @FXML
    private TextArea messageArea;

    @FXML
    private TextField lastNameField;

    @FXML
    private ToggleGroup employeeRole;

    @FXML
    private ToggleGroup departmentGroup;

    @FXML
    private ToggleGroup adminGroup;

    @FXML
    private RadioButton csButton;

    @FXML
    private RadioButton itButton;

    @FXML
    private RadioButton eceButton;

    @FXML
    private RadioButton partTimeButton;

    @FXML
    private RadioButton managementButton;

    @FXML
    private TextField annualSalaryField;

    @FXML
    private TextField hoursWorkedField;

    @FXML
    private TextField rateField;

    @FXML
    private TextField firstNameField;

    @FXML
    private Button clearButton;

    @FXML
    private RadioButton fullTimeButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private RadioButton managerButton;

    @FXML
    private RadioButton deptHeadButton;

    @FXML
    private RadioButton directorButton;

    @FXML
    private Button addEmployeeButton;

    @FXML
    private Button removeEmployeeButton;

    @FXML
    private Button setHoursButton;

    private String output = "";
    private String department = "";
    private String employeeType = "";

    @FXML
    void addEmployee(ActionEvent event) {
        String name = lastNameField.getText() + "," + firstNameField.getText();

        String pattern = "M/dd/YYYY";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        String d = dateFormatter.format(dateField.getValue());
        Date date = new Date(d);

        if(employeeType == "F"){
            addFullTime(name, date, department);
        }

        //System.out.println("----------------");
        //messageArea.setText(company.print());
        //System.out.println("----------------");
    }

    public void addFullTime(String name, Date date, String department){
        Float salary = Float.parseFloat(annualSalaryField.getText());

        Profile profile = new Profile(name,department,date);
        if(company.add(new Fulltime(profile, salary))) {
            output += "\nEmployee Added";
            messageArea.setText(output);

        }else {
            output += "\nEmployee is already in the list.";
            messageArea.setText(output);
        }
    }

    @FXML
    void clearScreen(ActionEvent event) {

    }

    @FXML
    void removeEmployee(ActionEvent event) {

    }

    @FXML
    void selectCS(ActionEvent event) {
        department = "CS";
    }

    @FXML
    void selectDeptHead(ActionEvent event) {

    }

    @FXML
    void selectDirector(ActionEvent event) {

    }

    @FXML
    void selectECE(ActionEvent event) {
        department = "ECE";
    }

    @FXML
    void selectFullTime(ActionEvent event) {
        employeeType = "F";
        disablePayFields();
        disableManagementButtons();
    }

    @FXML
    void selectIT(ActionEvent event) {
        department = "IT";
    }

    @FXML
    void selectManager(ActionEvent event) {

    }

    @FXML
    void selectMangement(ActionEvent event) {
        employeeType = "M";
        disablePayFields();
        annualSalaryField.setDisable(false);
        enableManagementButtons();
    }

    @FXML
    void selectPartTime(ActionEvent event) {
        employeeType = "P";
        enablePayFields();
        disableManagementButtons();
    }

    @FXML
    void setHours(ActionEvent event) {

    }

    public void disablePayFields(){
        hoursWorkedField.setDisable(true);
        rateField.setDisable(true);
        annualSalaryField.setDisable(false);
    }

    public void enablePayFields(){
        hoursWorkedField.setDisable(false);
        rateField.setDisable(false);
        annualSalaryField.setDisable(true);
    }

    public void disableManagementButtons(){
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
    }

    public void enableManagementButtons(){
        managerButton.setDisable(false);
        deptHeadButton.setDisable(false);
        directorButton.setDisable(false);
    }

}
