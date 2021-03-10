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
    private String role = "";

    @FXML
    void addEmployee(ActionEvent event) {
        String name = lastNameField.getText() + "," + firstNameField.getText();
        String pattern = "M/dd/YYYY";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        if(dateField.getValue() == null){
            output += "\nPlease don't leave the date field blank!";
            messageArea.setText(output);
            //return;
        } else {
            String d = dateFormatter.format(dateField.getValue());
            Date date = new Date(d);
            System.out.println(date.toString());

            if(date.isValid() == false) {
                output += "\nPlease enter a valid date! (mm/dd/yyyy)";
                messageArea.setText(output);
            }
            else if(employeeType.equals("F")){ //NEED TO CHECK NEGATIVE VALUES FOR SALARIES
                addFullTime(name, date, department);
            }
            else if(employeeType.equals("P")){
                addParttime(name, date, department);
            }
            else if(employeeType.equals("M")){
                addManagement(name, date, department);
            }
        }
        //dateField.setValue(null);
        //System.out.println("----------------");
        //messageArea.setText(company.print());
        //System.out.println("----------------");
    }

    public void addFullTime(String name, Date date, String department){
        try{
            Float salary = Float.parseFloat(annualSalaryField.getText());
            if(salary < 0) {
                output += "\nSalary cannot be negative.";
                messageArea.setText(output);
                return;
            }
            Profile profile = new Profile(name,department,date);
            if(company.add(new Fulltime(profile, salary)) != false) {
                output += "\nEmployee Added";

            }else {
                output += "\nEmployee is already in the list.";
            }
            messageArea.setText(output);
        }
        catch(Exception e){
            output += "\nPlease enter a number for Annual Salary!";
            messageArea.setText(output);
        }
        dateField.setValue(null);
    }

    public void addParttime(String name, Date date, String department){
        int hours = 0;
        float wage = 0;
        try{
            hours = Integer.parseInt(hoursWorkedField.getText());
            if(hours < 0) {
                output += "\nHours Worked cannot be negative.";
                messageArea.setText(output);
                return;
            }
        }
        catch(Exception e){
            output += "\nPlease enter an integer value for Hours Worked!";
            messageArea.setText(output);
            return;
        }
        try{
            wage = Float.parseFloat(rateField.getText());
            if(wage < 0) {
                output += "\nRate cannot be negative.";
                messageArea.setText(output);
                return;
            }
        }
        catch(Exception e){
            output += "\nPlease enter a float value for Rate!";
            messageArea.setText(output);
            return;
        }
        Profile profile = new Profile(name,department,date);
        if(company.add(new Parttime(profile, wage, hours))) {
            output += "\nEmployee Added";

        }else {
            output += "\nEmployee is already in the list.";
        }
        messageArea.setText(output);
        dateField.setValue(null);
    }

    private void addManagement(String name, Date date, String department) {
        try{
            Float salary = Float.parseFloat(annualSalaryField.getText());
            if(salary < 0) {
                output += "\nSalary cannot be negative.";
                messageArea.setText(output);
                return;
            }
            Profile profile = new Profile(name,department,date);
            if(company.add(new Management(role, profile, salary)) != false) {
                output += "\nEmployee Added";

            }else {
                output += "\nEmployee is already in the list.";
            }
            messageArea.setText(output);
        }
        catch(Exception e){
            output += "\nPlease enter a number for Annual Salary!";
            messageArea.setText(output);
        }
        dateField.setValue(null);
    }

    @FXML
    void clearScreen(ActionEvent event) {
        firstNameField.setText("");
        lastNameField.setText("");
        dateField.setValue(null);
        annualSalaryField.setText("");
        hoursWorkedField.setText("");
        rateField.setText("");
        output = "";
        messageArea.setText(output);
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
        role = "Department Head";
    }

    @FXML
    void selectDirector(ActionEvent event) {
        role = "Director";
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
        role = "Manager";
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
        int hours = 0;
        if(!company.hasEmployee()) {
            output += "\nEmployee database is empty.";
            messageArea.setText(output);
            return;
        }
        try{
            hours = Integer.parseInt(hoursWorkedField.getText());
        }
        catch (Exception e){
            output += "\nPlease enter an integer value for Hours Worked!";
            messageArea.setText(output);
            return;
        }
        if(hours > 100) {
            output += "\nHours Worked cannot be greater than 100 hours!";
            messageArea.setText(output);
            return;
        } else if(hours < 0) {
            output += "\nHours Worked cannot be less than 0 hours!";
            messageArea.setText(output);
            return;
        } else{
            String name = lastNameField.getText() + "," + firstNameField.getText();
            String pattern = "M/dd/YYYY";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            if(dateField.getValue() == null){
                output += "\nPlease don't leave the date field blank!";
                messageArea.setText(output);
                //return;
            } else {
                String d = dateFormatter.format(dateField.getValue());
                Date date = new Date(d);
                System.out.println(date.toString());

                if(date.isValid() == false) {
                    output += "\nPlease enter a valid date! (mm/dd/yyyy)";
                    messageArea.setText(output);
                    return;
                }
                Profile profile = new Profile(name,department,date);
                if(company.setHours(new Parttime(profile, hours))) {
                    output += "\nWorking hours for " + firstNameField.getText() + " " + lastNameField.getText() + " set!";
                }else {
                    output += "\nYou can only set hours for a parttime employee that exists in the database!";
                }
                messageArea.setText(output);
            }
        }
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
