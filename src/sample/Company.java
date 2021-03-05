package sample;

/**
 * This class represents the Company that has the employees. It is an array-based container class that
 * implements the employee database. It stores a list of employees as well as the number of employees
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Company {
	private Employee[] emplist;
	private int numEmployee;

	/**
	 * Default constructor for the Company class. It initializes the employee database to a starting capacity of four,
	 * and it initializes the number of employees to zero.
	 */
	public Company() {
		this.emplist = new Employee[4];
		this.numEmployee = 0;
	}
	
	
	/**
	 * Method to find a certain employee in the list of employees
	 * @param employee the employee that needs to be found in the list
	 * @return index in the emplist that contains the employee in question, -1 if
	 * employee does not exist
	 */
	private int find(Employee employee) {
		int location = -1;

		for (int i = 0; i < numEmployee; i++) {
			Employee emp = emplist[i];
			if (employee.equals(emp)) {
				location = i;
				return location;
			}
		}

		return location;
	}

	/**
	 * Increases the length of the employee databse array by four in order to accomodate new
	 * entries into the library. The function adds a capacity of four to the list.
	 */
	private void grow() {
		Employee[] newArray = new Employee[emplist.length + 4];
		for (int i = 0; i < emplist.length; i++) {
			newArray[i] = emplist[i];
		}
		emplist = newArray;
	}

	/**
	 * Seeks to add an employee to the company. In the event where the employee list
	 * is full, the capacity of the list is increased by four
	 * @param employee that is to be added to the company
	 * @return true if the employee was added successfully, false if it was not; the
	 *         employee already exists
	 */
	public boolean add(Employee employee) {
		if (find(employee) != -1) { // only add if the employee doesn't already exist
			return false;
		}
		
		if (numEmployee + 1 > emplist.length) {
			grow(); // calls the grow function
		}
		
		emplist[numEmployee] = employee;
		numEmployee++;
		return true;
	}

	/**
	 * Seeks to remove an employee from the employee list
	 * @param employee that is to be removed from the list
	 * @return true if the employee was successfully removed, false if it was not (doesn't exist in the library)
	 */
	public boolean remove(Employee employee) {
		int index = find(employee);
		if (index == -1) {
			return false;
		}
		if (index == emplist.length - 1) {
			emplist[emplist.length - 1] = null;
		} else if (numEmployee == 1) {
			emplist[index] = null;
		} else {
			for (int j = index; j < emplist.length - 1; j++) {
				emplist[j] = emplist[j + 1];
			}
		}
		this.numEmployee--;
		return true;
	}

	/**
	 * Set working hours for a part time
	 * @param employee employee to set the hours for
	 * @return true if the hours were added successfully, false if they were not
	 */
	public boolean setHours(Employee employee) {
		int index = find(employee);

		if (index == -1) {
			return false;
		}

		int hours = 0;

		Parttime parttime_for_data = (Parttime) employee;
		hours = parttime_for_data.getHoursWorked();

		Parttime parttime = (Parttime) emplist[index];
		parttime.setHours(hours);

		return true;
	}

	/**
	 * This function processes payments for all the employees in the database with their respective payment formulas.
	 */
	public void processPayments() {
		for (Employee emp : emplist) {
			if (emp instanceof Parttime) {
				Parttime parttime = (Parttime) emp;
				parttime.calculatePayment();
			}

			if (emp instanceof Fulltime) {
				Fulltime fulltime = (Fulltime) emp;
				fulltime.calculatePayment();

				if (fulltime instanceof Management) {
					Management management = (Management) fulltime;
					management.calculatePayment();
				}
			}
		}
	}

	/**
	 * This function prints earning statements for all employees based on the existing order in the employee database
	 */
	public String print() {
		//System.out.println("--Printing earning statements for all employees--");
		String result = "";
		for (int i = 0; i < numEmployee; i++) {
			String output = emplist[i].toString();
			result += output;
		}
		return result;
	}

	/**
	 * This function prints earning statements by department, with CS coming first followed by ECE, and then ending with IT
	 */
	public void printByDepartment() {
		System.out.println("--Printing earning statements by department--");
		Employee[] sortedList = new Employee[numEmployee];
		int sortedIndex = 0;

		for (int i = 0; i < numEmployee; i++) {
			if (emplist[i].getDepartment().equals("CS")) {
				sortedList[sortedIndex] = emplist[i];
				sortedIndex++;
			}
		}
		
		for (int i = 0; i < numEmployee; i++) {
			if (emplist[i].getDepartment().equals("ECE")) {
				sortedList[sortedIndex] = emplist[i];
				sortedIndex++;
			}
		}
		
		for (int i = 0; i < numEmployee; i++) {
			if (emplist[i].getDepartment().equals("IT") ) {
				sortedList[sortedIndex] = emplist[i];
				sortedIndex++;
			}
		}
		for (int i = 0; i < sortedList.length; i++) {
			System.out.println(sortedList[i].toString());
		}
	}

	/**
	 * This function prints earning statements by date hired in ascending order
	 */
	public void printByDate() {
		System.out.println("--Printing earning statements by date hired--");
		Employee sortedList[] = new Employee[numEmployee];
		for (int i = 0; i < numEmployee; i++) {
			sortedList[i] = emplist[i];
		}

		//INSERTION SORT
		for (int i = 1; i < numEmployee; i++) {
			Employee key = sortedList[i];
			int j = i - 1;
			while ((j >= 0) && (key.getDate().compareTo(sortedList[j].getDate()) == 1)) {
				sortedList[j + 1] = sortedList[j];
				j = j - 1;
			}
			sortedList[j + 1] = key;
		}

		for (int i = 0; i < sortedList.length; i++) {
			System.out.println(sortedList[i].toString());
		}
	}

	/**
	 * This function checks to see whether the employee databse is empty or if it contains employees
	 * @return true if the database contains employees, false if it is emplty
	 */
	public boolean hasEmployee() {
		return numEmployee > 0;
	}
   
}