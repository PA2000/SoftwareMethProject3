import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class is the representation of the PayrollProcessing that handles the proccessing for the numerous commands read in from the console. 
 * This is the user interface class that handles input and output. The features of this class use a company object 
 * @author Padmank Ambadipudi, Dimitri Victor
*/
public class PayrollProcessing{
	
	Company company = new Company();
	
	public static final int MANAGER = 1;
	public static final int DEPT_HEAD = 2;
	
	/**
	 * This is function  reads in the user input and proccess it into output that is then displayed on the terminal. 
	 */
	public void run(){
		System.out.println("Payroll Processing starts.");   
		Scanner myObj = new Scanner(System.in);
		
		while(myObj.hasNext()) {
	   		   
			String command = myObj.nextLine();
			String[] comParts = new String[6];		   
			   
			if(command.equals("")) {
				continue;
			}
			   
			if(command.equals("Q")) {
				System.out.println("Payroll Processing Completed.");				
				break;
			}
			   		   
			StringTokenizer st = new StringTokenizer(command, " ");
			
			int index = 0;
			   
			while (st.hasMoreTokens()) {
				comParts[index] = st.nextToken();
				index++;
			}
				
			String firstCommand = comParts[0];
			
			if(!isvalidCommand(firstCommand)) {//checks to see if the command is valid
				System.out.println("Command'" + firstCommand +"' not supported!");	
				continue;
			}
			
			if(firstCommand.equals("AP")) {
				doAPCommand(comParts);
			}else if(firstCommand.equals("AF")) {
				doAFCommand(comParts);
			}else if(firstCommand.equals("AM")) {
				doAMCommand(comParts);
			}else if(firstCommand.equals("R")) {
				doRCommand(comParts);
			}else if(firstCommand.equals("C")) {
				doCCommand();
			}else if(firstCommand.equals("S")) {
				doSCommand(comParts);
			}else if(firstCommand.equals("PA")) {
				doPACommand();
			}else if(firstCommand.equals("PH")) {
				doPHCommand();
			}else {
				doPDCommand();
			}			
		}
		myObj.close();
	}
	
	/**
	 * Method to determine whether or not a user input is a valid command in the program
	 * @param command that was entered by the user, represented as a String
	 * @return true if the command is valid, false otherwise
	 */
	private boolean isvalidCommand(String command) {
		
		   if(!command.equals(command.toUpperCase())) {
			   return false;
		   }	 
		   
		   if(command.equals("AP") || 
				   command.equals("AF") ||
				   command.equals("AM") ||
				   command.equals("R") || 
				   command.equals("C") || 
				   command.equals("S") || 
				   command.equals("PA") || 
				   command.equals("PH")|| 
				   command.equals("PD")) {
			   return true;
		   }	     
		   return false;
	}
	
	/**
	 * Method to proccess a request to add a partime employee to the database. 
	 * It does not return anything, but it prints out a message saying that the employee was added if it was successful, 
	 * or messages altering the user of an invalid date, invalid payrate, invalid department, or if the employee already exists within the database. 
	 * @param comParts string array containing the user input represented as an array containing: [command, lastname,firstname, department, date hired, salary]
	 */
	private void doAPCommand(String[] comParts) {
		
		Date date = new Date(comParts[3]);
		String department = comParts[2];
		float salary = Float.parseFloat(comParts[4]);
		
		if(!date.isValid()) {
			System.out.println( date.toString() + " is not a valid date!");
			return;
		}
		if(salary < 0) {
			System.out.println("Pay rate cannot be negative.");
			return;
		}
		if(!department.equals("CS") && !department.equals("ECE") && !department.equals("IT")){
			System.out.println("'" + department + "' " + "invalid department code.");
			return;
		}
		
		String name = comParts[1];
		
		Profile profile = new Profile(name,department,date);
		
		if(company.add(new Parttime(salary, profile))) {
			System.out.println("Employee added.");			
		}else {
			System.out.println("Employee is already in the list.");
		}
				
	}
	
	/**
	 * Method to proccess a request to add a fulltime employee to the database. 
	 * It does not return anything, but it prints out a message saying that the employee was added if it was successful, 
	 * or messages altering the user of an invalid date, invalid salary, invalid department, or if the employee already exists within the database. 
	 * @param comParts string array containing the user input represented as an array containing: [command, lastname,firstname, department, date hired, salary]
	 */
	private void doAFCommand(String[] comParts) {
		
		Date date = new Date(comParts[3]);
		String department = comParts[2];
		float salary = Float.parseFloat(comParts[4]);
		
		if(!date.isValid()) {
			System.out.println( date.toString() + " is not a valid date!");
			return;
		}
		if(salary < 0) {
			System.out.println("Salary cannot be negative.");
			return;
		}
		if(!department.equals("CS") && !department.equals("ECE") && !department.equals("IT")){
			System.out.println("'" + department + "' " + "invalid department code.");
			return;
		}
		
		String name = comParts[1];
		
		Profile profile = new Profile(name,department,date);
		
		if(company.add(new Fulltime(profile, salary))) {
			System.out.println("Employee added.");
			
		}else {
			System.out.println("Employee is already in the list.");
		}
			
	}
	
	/**
	 * Method to proccess a request to add a management employee to the database. 
	 * It does not return anything, but it prints out a message saying that the employee was added if it was successful, 
	 * or messages altering the user of an invalid date, invalid salary, invalid department, or if the employee already exists within the database. 
	 * @param comParts string array containing the user input represented as an array containing: [command, lastname,firstname, department, date hired, salary, management type]
	 */
	private void doAMCommand(String[] comParts) {
		
		Date date = new Date(comParts[3]);
		String department = comParts[2];
		float salary = Float.parseFloat(comParts[4]);
		int management = Integer.parseInt(comParts[5]);
		
		if(!date.isValid()) {
			System.out.println( date.toString() + " is not a valid date!");
			return;
		}
		if(salary < 0) {
			System.out.println("Salary cannot be negative.");
			return;
		}
		if(!department.equals("CS") && !department.equals("ECE") && !department.equals("IT")){
			System.out.println("'" + department + "' " +"invalid department code.");
			return;
		}
		if(management < 1 || management > 3) {
			System.out.println("invalid management code.");
			return;
		}
				
		String role = "";
		
		if(management == MANAGER) {
			role = "Manager";
		}else if(management == DEPT_HEAD) {
			role = "Department Head";
		}else {
			role = "Director";
		}
		
		String name = comParts[1];
		
		Profile profile = new Profile(name,department,date);
		
		if(company.add(new Management(role, profile, salary))) {
			System.out.println("Employee added.");
			
		}else {
			System.out.println("Employee is already in the list.");
		}
		
	}
	
	/**
	 * Method to proccess a request to remove an emploee from the dataase. 
	 * It does not return anything, but it prints out a message saying that the employee was removed if it was successful, 
	 * or a message saying the employee cannot removed if they do not exist within the database, or that the database is empty.
	 * @param comParts string array containing the user input represented as an array containing: [name, department, date]
	 */
	private void doRCommand(String[] comParts) {
		
		if(!company.hasEmployee()) {
			System.out.println("Employee database is empty.");
			return;
		}
		
		String name = comParts[1];
		String department = comParts[2];
		Date date = new Date(comParts[3]);
		
		Profile profile = new Profile(name,department,date);
		
		if(company.remove(new Employee(profile))) {
			System.out.println("Employee removed.");
		}else {
			System.out.println("Employee does not exist.");
		}
		
	}
	
	/**
	 * This function sets the working hours for a parttime employee. 
	 * It does not return anything, but it prints out a message saying that the working hours were set successfully, or that they were not set due to
	 * the employee not existing, or invalid hours being entered.
	 * @param comParts string array containing the user input represented as an array containing: [name, department, date hired, hours]
	 */
	private void doSCommand(String[] comParts) {
		
		if(!company.hasEmployee()) {
			System.out.println("Employee database is empty.");
			return;
		}
		
		Date date = new Date(comParts[3]);
		String department = comParts[2];
		int hours = Integer.parseInt(comParts[4]);
		
		if(hours > 100) {			
			System.out.println("Invalid Hours: over 100.");
			return;
		}else if(hours < 0) {			
			System.out.println("Working hours cannot be negative.");	
			return;
		}else {
			
			String name = comParts[1];
			
			Profile profile = new Profile(name,department,date);

			if(company.setHours(new Parttime(profile, hours))) {
				System.out.println("Working hours set.");
			}else {
				System.out.println("Not in the employee database.");
			}
						
		}
				
	}
	
	/**
	 * This function calculates the payments alloted to each employee in the database. It prints a message of success if the 
	 * command was successful, and a message indicating if there are no employees in the datase otherwise.
	 */
	private void doCCommand() {
		
		if(!company.hasEmployee()) {
			System.out.println("Employee database is empty.");
			return;
		}
		
		if(company.hasEmployee()) {		
			company.processPayments();
			System.out.println("Calculation of employee payments is done.");
		}else {		
			System.out.println("Employee database is empty.");		
		}
		
	}
	
	/**
	 * Method to print out the employees in the database in the order they appear within the databse.
	 */
	private void doPACommand() {
		
		if(company.hasEmployee()) {		
			company.print();			
		}else {			
			System.out.println("Employee database is empty.");			
		}
		
	}
	
	/**
	 * Method to print out the employees in the database in ascending order of the date hired.
	 */
	private void doPHCommand() {
		
		if(company.hasEmployee()) {		
			company.printByDate();			
		}else {			
			System.out.println("Employee database is empty.");			
		}
		
	}
	
	/**
	 * Method to print out the employees in the database in ascending order of their department.
	 */
	private void doPDCommand() {
		
		if(company.hasEmployee()) {			
			company.printByDepartment();				
		}else {			
			System.out.println("Employee database is empty.");			
		}
		
	}
	
}