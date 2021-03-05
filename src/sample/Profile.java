package sample;

/**
 * This class represents the profile of an indidivdual employee. The profile contains the employee's name, the department they work in,
 * and the date they were hired.
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Profile {
   private String name; //
   private String department; 
   private Date dateHired;

   /**
    * Default constructor for the Profile which initializes the name, department, and date hired
    * @param name of the employee
    * @param department that the employee belongs do(CS, ECE, IT)
    * @param dateHired date that the employee was hired
    */
   public Profile(String name, String department, Date dateHired) {
	   this.name = name;
	   this.department = department;
	   this.dateHired = dateHired;
   }
   
   /**
    * toString method which returns a String representation of the profile object
    * @return String representation of Date
    */
   @Override
   public String toString() {
	   return name + "::" + department + "::" + dateHired.toString() + "::";
   }

   /**
    * equals function which checks to see if an employee equals this employee. 
    * Employees are considered equal if all the attributes of their profile (name, department, and dateHired) are the same.
    * @param obj the object that is being passed in for comparison to this profile.
    * @return true if the employee objects are equal, false if they are not
    */
   @Override
   public boolean equals(Object obj) { //compare name, department and dateHired
      if(obj instanceof Profile) {
		   Profile profile = (Profile)obj;
		   if(this.name.equals(profile.name) && this.department.equals(profile.department) && this.dateHired.compareTo(profile.dateHired) == 0) {
			   return true;
		   }
	   }
      return false;
   } 
   
   /**
    * This function is a getter function that returns the department attribute of the profile
    * @return department that the employee belongs to
    */
   public String getDepartment(){
	   return this.department;
   }

   /**
    * This function is a getter function that returns the dateHired attribute of the profile
    * @return date the employee was hired
    */
   public Date getDateHired(){
	   return this.dateHired;
   }
}