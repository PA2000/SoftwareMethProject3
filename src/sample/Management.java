/**
 * This class represents an employee who is in a management role and is a subclass of Fulltime. 
 * In addition to the attributes inherited from Fulltime, the class also contains management compensation, a payment, and a specific management role
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Management extends Fulltime {

	private float managementComp;
	private float payment;
	private String role;
	
	private static final float MANAGER_COMP = 5000;
	private static final float DEPT_HEAD_COMP = 9500;
	private static final float DIRECTOR_COMP = 12000;
	
	/**
	 * Default Constructor for the Management class that initializes the role of the employee, their profile, and their salary
	 * @param role the role of the management employee: Manager, Department Head, or Director
	 * @param profile that is to be assigned to the employee 
	 * @param salary of the employee 
	 */
	public Management(String role, Profile profile, float salary) {
		super(profile, salary);
		this.role = role;
		
		if(role.equals("Manager")) {
			this.managementComp = MANAGER_COMP;
		}else if(role.equals("Department Head")) {
			this.managementComp = DEPT_HEAD_COMP;
		}else {
			this.managementComp = DIRECTOR_COMP;
		}
		
		payment = managementComp/(float)26;
	}
	
	/**
	 * The toString method for a Mangement employee which takes into account the toString from the parent class Fulltime, 
	 * the payment attribute, and the management role
	 * @return the string representation of the Management class
	 */
	@Override
	public String toString() {		
		return super.toString() + "::" + role + " Compensation $" + customFormat("###,##0.00", this.payment);
	}

	/**
	 * The equals method for Management which deteremines if a given object is equal to this object by upcasting it as an Fulltime employee, 
	 * and using the super class' equals function
	 * @param obj to be compared to with this Management object. 
	 * @return true if they are equal, false if they are not
	 */
	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Management) {
			Fulltime fulltime = (Fulltime)obj;
			if(super.equals(fulltime)) {
				return true;
			}
		}
      return false;
	}
	
	/**
	 * This function calculates the payment alloted to the management employee based on their management compensation.
	 */
	@Override 
	public void calculatePayment() {
		super.calculatePayment();
		//need this variable some
		payment =  this.managementComp/(float)26;
		super.addCompensation(payment);
	}
}