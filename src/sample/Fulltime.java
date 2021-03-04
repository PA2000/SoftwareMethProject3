/**
 * This class represents the Fulltime employee and is a subclass of Employee. 
 * In addition to the attributes inherited from Employee, the class also contains a yearly salary and payment
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Fulltime extends Employee {
   
	private float yearly_salary;
	private float payment;
	
	/**
	 * Constructor for Fulltime which initializes the profile using Employee, the super class, and salary
	 * @param profile that is to be assigned to the Fulltime employee
	 * @param salary that is to be assigned to the Full time employee
	 */
	public Fulltime(Profile profile, float salary) {
		super(profile);
		yearly_salary = salary;		
	}
	
	/**
	 * The toString method for a Fulltime employee which takes into account the toString from the parent class Employee, 
	 * the payment attribute, and the yearly_salary attribute
	 * @return the string representation of the Fulltime class
	 */
   @Override
   public String toString() {
	   return super.toString() + "Payment $" + customFormat("###,##0.00", this.payment) + "::" + "FULL TIME" + "::" + 
			   "Annual Salary $" + customFormat("###,###.00", this.yearly_salary);
   }

	/**
	 * The equals method for Fulltime which deteremines if a given object is equal to this object by upcasting it as an Employee, 
	 * and using the super class' equals function
	 * @param obj to be compared to with this Fulltime object. 
	 * @return true if they are equal, false if they are not
	 */
   @Override 
   public boolean equals(Object obj) {
	   
	   if(obj instanceof Fulltime) {
		   Employee employee = (Employee)obj;
		   if(super.equals(employee)) {
			   return true;
		   }
	   }
	   return false;
   }
   
	/**
	 * This function calculates the payment that is alloted to the fulltime employee.
	 */
   @Override 
   public void calculatePayment() { 
	   payment = yearly_salary/(float)26;
   }
   
	/**
	 * This function is a getter function that returns the payment attribute.
	 * @return payment amount
	 */
   public float getPayment() {
	   return payment;
   }
   
	/**
	 * This function updates the payment attribute by adding the compensation given.
	 * @param compensation to be given to the fulltime employee
	 */
   public void addCompensation(float compensation) {
	   this.payment += compensation;
   }
   
}