package sample;

/**
 * This class represents the Parttime employee and is a subclass of Employee. 
 * In addition to the attributes inherited from Employee, the class also contains an hourly wage, the number of hours worked, and payment.
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Parttime extends Employee {
   	
	private float hourly_wage;
	private float payment;
	private int hoursWorked;
	
	/**
	 * Constructor for Parrtime which initializes the profile using Employee, the super class, and the hours worked
	 * @param profile that is to be assigned to the Fulltime employee
	 * @param hours that represent and are assigned to the hours worked
	 */
	public Parttime(Profile profile, int hours) {
		super(profile);
		this.hourly_wage = 0;		
		hoursWorked = hours;
		calculatePayment();
	}
	
	/**
	 * Alternate constructor for Parrtime which initializes the profile using Employee, the super class, and the wage
	 * @param wage that represents and is assinged to the hourly_wage attribute 
	 * @param profile that is to be assigned to the Fulltime employee
	 */
	public Parttime(float wage, Profile profile) {
		super(profile);
		this.hourly_wage = wage;
		
	}
	
	/**
	 * The toString method for a Parttime employee which takes into account the toString from the parent class Employee, 
	 * the payment attribute, the hourly_wage attribute and the hoursWorked attribute.
	 * @return the string representation of the Parttime class
	 */
	@Override
	public String toString() {
		return super.toString() + "Payment $" + customFormat("###,##0.00", this.payment) + "::PART TIME::"
				+ "Hourly Rate $" + customFormat("###,###.00", this.hourly_wage) + "::Hours worked this period: " + hoursWorked;
	}

	/**
	 * The equals method for Fulltime which deteremines if a given object is equal to this object by upcasting it as an Employee, 
	 * and using the super class' equals function
	 * @param obj to be compared to with this Parttime object. 
	 * @return true if they are equal, false if they are not
	 */
	@Override 
	public boolean equals(Object obj) {		
		if(obj instanceof Parttime) {
			Employee employee = (Employee)obj;
			if(super.equals(employee)) {
				return true;
			}
		}
      return false;
	}
	
	/**
	 * This function calculates the payment alloted to the parttime employee based on their hourly wage, overtime hours, and normal working hours.
	 */
	@Override 
	public void calculatePayment() { 
		int normHours = 0;
		int overtime = 0;
		if(hoursWorked > 80) {
			overtime = hoursWorked-80;
			normHours = 80;
		}else {
			normHours = hoursWorked;
		}
		this.payment = normHours*hourly_wage + overtime*((float)1.5*hourly_wage);
	}
	
	/**
	 * This function is a getter function to return the hoursWorked attribute
	 * @return hours worked by a parttime employee
	 */
	public int getHoursWorked() {
		return hoursWorked;
	}
	
	/**
	 * This function is a setter function that sets the hoursWorked attribute to the amount passed in to the function.
	 * @param hours amount of hours worked by the Parrtime employee
	 */
	public void setHours(int hours) {
		hoursWorked = hours;
	}
	
	
}