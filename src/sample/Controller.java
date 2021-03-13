package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is the GUI representation of the PayrollProcessing that handles the proccessing for the numerous commands read in from the console.
 * This is the user interface class that handles input and output. The features of this class use a company object
 * @author Padmank Ambadipudi, Dimitri Victor
 */
public class Controller {
    public static final int MANAGER = 1;
    public static final int DEPT_HEAD = 2;

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

    /**
     * This function handles the event where the "Add Employee" button is clicked. Depending on the type of employee, different functions are called
     * @param event the clicking of the button "Add Employee"
     */
    @FXML
    void addEmployee(ActionEvent event) {
        if(checkNameValid()){
            return;
        }
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

            if(date.isValid() == false) {
                output += "\nPlease enter a valid date! (mm/dd/yyyy)";
                messageArea.setText(output);
            }
            else if(department.equals("")){
                output += "\nPlease select a department!";
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
    }

    /**
     * This function checks to make sure the name fields are not empty
     * @return true if one or both of the name fields are empty, false otherwise
     */
    private boolean checkNameValid() {
        if(firstNameField.getText().equals("") && lastNameField.getText().equals("")){
            output += "\nDon't leave first name and last name empty!";
            messageArea.setText(output);
            return true;
        }
        if(firstNameField.getText().equals("")){
            output += "\nDon't leave first name empty!";
            messageArea.setText(output);
            return true;
        }
        if(lastNameField.getText().equals("")){
            output += "\nDon't leave last name empty!";
            messageArea.setText(output);
            return true;
        }
        return false;
    }

    /**
     * This function adds a full time employee to the employee database. It checks to make sure that all input is present and is valid,
     * returning an error message if it is not
     * @param name of the employee to be added
     * @param date that the employee was hired
     * @param department that the employee works in
     */
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

    /**
     * This function adds a part time employee to the employee database. It checks to make sure that all input is present and is valid,
     * returning an error message if it is not
     * @param name of the employee to be added
     * @param date that the employee was hired
     * @param department that the employee works in
     */
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

    /**
     * This function adds a management type employee to the employee database. It checks to make sure that all input is present and is valid,
     * returning an error message if it is not
     * @param name of the employee to be added
     * @param date that the employee was hired
     * @param department that the employee works in
     */
    private void addManagement(String name, Date date, String department) {
        if(role.equals("")){
            output += "\nYou must select a management role";
            messageArea.setText(output);
            return;
        }
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

    /**
     * This function clears all of the text fields as well as the TextArea that displays the output
     * @param event the clicking of the button "Clear"
     */
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

    /**
     * This function calculates the payments allotted to each employee in the database. It outputs a message of success if the
     * command was successful, and a message indicating if there are no employees in the datbase otherwise.
     * @param event the clicking of the button "Calculate Payment"
     */
    @FXML
    void doPaymentCalculation(ActionEvent event) {
        if(!company.hasEmployee()) {
            output += "\nEmployee Database is Empty!";
            messageArea.setText(output);
            return;
        }
        company.processPayments();
        output += "\nPayment Calculation Completed!";
        messageArea.setText(output);
    }

    /**
     * This function exports the current Employee database represented as printed output into a text file.
     * @param event the export file button is clicked
     */
    @FXML
    void exportFile(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targeFile = chooser.showSaveDialog(stage); //get the reference of the target file
            //write code to write to the file.

            FileWriter fw = new FileWriter(targeFile);
            String employees = company.print();

            Scanner sc = new Scanner(employees);
            while (sc.hasNext()){
                String line = sc.nextLine();
                fw.write(line);
                fw.write("\n");
            }

            output += "\nFile Successfully Exported";
            messageArea.setText(output);
            fw.close();

        } catch (Exception e) {
            output += "\nCan't Create File";
            messageArea.setText(output);
        }
    }

    /**
     * This function imports a file that contains commands and processes them.
     * @param event the export file button is clicked
     */
    @FXML
    void importFile(ActionEvent event) {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Source File for the Import");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File sourceFile = chooser.showOpenDialog(stage);

            Scanner sc = new Scanner(sourceFile);

            while(sc.hasNext()){
                String line = sc.nextLine();
                String[] lineParts = new String[6];
                StringTokenizer st = new StringTokenizer(line, ",");

                int index = 0;

                while (st.hasMoreTokens()) {
                    lineParts[index] = st.nextToken();
                    index++;
                }

                Date date = new Date(lineParts[3]);
                String department = lineParts[2];
                String name = lineParts[1];

                if(lineParts[0].equals("F")){

                    String salary = lineParts[4];
                    annualSalaryField.setText(salary);

                    addFullTime(name,date,department);

                    annualSalaryField.setText("");

                }else if(lineParts[0].equals("P")){

                    String rate = lineParts[4];

                    hoursWorkedField.setText("0");
                    rateField.setText(rate);

                    addParttime(name,date,department);

                    hoursWorkedField.setText("");
                    rateField.setText("");

                }else if(lineParts[0].equals("M")){

                    String salary = lineParts[4];
                    annualSalaryField.setText(salary);

                    int management = Integer.parseInt(lineParts[5]);

                    if(management == MANAGER) {
                        role = "Manager";
                    }else if(management == DEPT_HEAD) {
                        role = "Department Head";
                    }else {
                        role = "Director";
                    }

                    addManagement(name,date,department);
                    annualSalaryField.setText("");
                }
            }
            output += "\nFile Imported";
            messageArea.setText(output);
            sc.close();
        } catch (Exception e) {
            output += "\ninvalid File";
            messageArea.setText(output);
        }
    }

    /**
     * This function prints out the employees in the database in the order they appear within the database.
     * @param event the print button is clicked
     */
    @FXML
    void printAllEmployees(ActionEvent event) {
        if(company.hasEmployee()){
            output += "\n--Printing earning statements for all employees--" + company.print();
        } else{
            output += "\nEmployee Database is Empty!";
        }
        messageArea.setText(output);
    }

    /**
     * This function prints out the employees in the database in ascending order of the date they were hired.
     * @param event the print button is clicked
     */
    @FXML
    void printByDateHired(ActionEvent event) {
        if(company.hasEmployee()){
            output +="\n--Printing earning statements by date hired--" + company.printByDate();
        } else{
            output += "\nEmployee Database is Empty!";
        }
        messageArea.setText(output);
    }

    /**
     * This function prints out the employees in the database in ascending order of their department.
     * @param event the print button is clicked
     */
    @FXML
    void printByDept(ActionEvent event) {
        if(company.hasEmployee()){
            output += "\n--Printing earning statements by department--" + company.printByDepartment();
        } else{
            output += "\nEmployee Database is Empty!";
        }
        messageArea.setText(output);
    }

    /**
     * This function removes a specififed employee from the databse. It outputs an error message if the employee does not
     * exist or input is incorrect/missing, or a success message if the employee was removed
     * @param event
     */
    @FXML
    void removeEmployee(ActionEvent event) {
        if(!company.hasEmployee()) {
            output += "\nEmployee database is empty.";
            messageArea.setText(output);
            return;
        }
        if(checkNameValid()){
            return;
        }
        String name = lastNameField.getText() + "," + firstNameField.getText();
        String pattern = "M/dd/YYYY";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

        if(dateField.getValue() == null){
            output += "\nPlease don't leave the date field blank!";
            messageArea.setText(output);
        } else {
            String d = dateFormatter.format(dateField.getValue());
            Date date = new Date(d);

            if (date.isValid() == false) {
                output += "\nPlease enter a valid date! (mm/dd/yyyy)";
                messageArea.setText(output);
                return;
            }
            else if(department.equals("")){
                output += "\nPlease select a department!";
                messageArea.setText(output);
                return;
            }
            else{
                Profile profile = new Profile(name, department, date);
                if(company.remove(new Employee(profile))) {
                    output += "\nEmployee removed.";
                }else {
                    output += "\n\"Employee does not exist in database!";
                }
                messageArea.setText(output);
            }
        }
    }

    /**
     * This function updates the variable that keeps track of what department the user has currently selected and sets it to CS
     * @param event the CS radio button is selected
     */
    @FXML
    void selectCS(ActionEvent event) {
        department = "CS";
    }

    /**
     * This function updates the variable that keeps track of what management role the user has currently selected and sets it to Department Head
     * @param event the department head radio button was selected
     */
    @FXML
    void selectDeptHead(ActionEvent event) {
        role = "Department Head";
    }

    /**
     * This function updates the variable that keeps track of what management role the user has currently selected and sets it to Director
     * @param event Director radio button was selected
     */
    @FXML
    void selectDirector(ActionEvent event) {
        role = "Director";
    }

    /**
     * This function updates the variable that keeps track of what department the user has currently selected and sets it to ECE
     * @param event the ECE radio button is selected
     */
    @FXML
    void selectECE(ActionEvent event) {
        department = "ECE";
    }

    /**
     * This function updates the variable keeping track of the employee type and disables all options that do not belong to a Fulltime Employee
     * @param event fulltime radio button selected
     */
    @FXML
    void selectFullTime(ActionEvent event) {
        employeeType = "F";
        disablePayFields();
        disableManagementButtons();
    }

    /**
     * This function updates the variable that keeps track of what department the user has currently selected and sets it to IT
     * @param event the IT radio button is selected
     */
    @FXML
    void selectIT(ActionEvent event) {
        department = "IT";
    }

    /**
     * This function updates the variable that keeps track of what management role the user has currently selected and sets it to Manager
     * @param event Manager radio button was selected
     */
    @FXML
    void selectManager(ActionEvent event) {
        role = "Manager";
    }

    /**
     * This function updates the variable keeping track of the employee type and disables all options that do not belong to a Management Employee
     * @param event management radio button selected
     */
    @FXML
    void selectMangement(ActionEvent event) {
        employeeType = "M";
        disablePayFields();
        annualSalaryField.setDisable(false);
        enableManagementButtons();
    }

    /**
     * This function updates the variable keeping track of the employee type and disables all options that do not belong to a parttime Employee
     * @param event parttime radio button selected
     */
    @FXML
    void selectPartTime(ActionEvent event) {
        employeeType = "P";
        enablePayFields();
        disableManagementButtons();
    }

    /**
     * This function sets the working hours for a specified employee in the database. An error message is displayed if the databse is empty,
     * if the employee isn't parttime, or if the necessary inputs are empty. It returns a success message otherwise
     * @param event
     */
    @FXML
    void setHours(ActionEvent event) {
        if(!employeeType.equals("P")){
            output += "\nCan only set hours for parttime employee!";
            messageArea.setText(output);
            return;
        }
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

    /**
     * This function disables all the payfields that are meant ONLY for parttime employees
     */
    public void disablePayFields(){
        hoursWorkedField.setDisable(true);
        rateField.setDisable(true);
        annualSalaryField.setDisable(false);
    }

    /**
     * This function enables, or re-enables the payfield buttons that are meant ONLY for parttime employees
     */
    public void enablePayFields(){
        hoursWorkedField.setDisable(false);
        rateField.setDisable(false);
        annualSalaryField.setDisable(true);
    }

    /**
     * This function disables all the management buttons that are meant ONLY for managment employees
     */
    public void disableManagementButtons(){
        managerButton.setDisable(true);
        deptHeadButton.setDisable(true);
        directorButton.setDisable(true);
    }

    /**
     * This function enables, or re-enables the management buttons
     */
    public void enableManagementButtons(){
        managerButton.setDisable(false);
        deptHeadButton.setDisable(false);
        directorButton.setDisable(false);
    }

}
