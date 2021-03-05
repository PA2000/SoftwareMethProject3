package sample;

import java.text.DecimalFormat;

/**
 * This class represents an employee and is the super class of Fulltime, Parttime, and Management. 
 * It has one attribute which is the profile that uniquey identifies the employee
 * @author Padmank Ambadipudi, Dimitri Victor, Steve Ruda
 */
public class Employee {
   private Profile profile;
   
   /**
    * Constructor for the employee class that initializes the profile attribute.
    * @param profile that belongs to this employee
    */
   public Employee(Profile profile) {
	   this.profile = profile;
   }
      
   /**
    * This is the toString method that returns a String representation of the employee class.
    * @return String containing the representation of the employee
    */
   @Override
   public String toString() {
	   return profile.toString();
   }
   
   /**
    * This is the equals method that determines whether an object passed in is equal to this employee.
    * @param obj that needs to be compared to this employee
    * @return true if the objects are equal, meaning their profiles match, false if they are not equal
    */
   @Override
   public boolean equals(Object obj) {
	   if(obj instanceof Employee) {
		   Employee emp = (Employee) obj;
		   if(this.profile.equals(emp.profile)){
		         return true;
		      }
	   }

      return false;
   }
     
   /**
    * This function calculates the payment that is required for an employee object. 
    * Since an employee by itself does not earn anything, they must be a Fulltime Employee, Parrtime Employee, or a Management Employee, 
    * the function does not do anything.
    */
   public void calculatePayment() {
	   
   }
   
   /**
    * This function handles the formatting of floating point numbers into the desired output
    * @param pattern to be followed 
    * @param value to be change into the given pattern
    * @return the reformatted version of the number
    */
   public static String customFormat(String pattern, float value ) {
      DecimalFormat myFormatter = new DecimalFormat(pattern);
      String output = myFormatter.format(value);
      return output;
   }

   
   /**
    * This function is a getter function that returns the department the employee works for
    * @return the department attribute of the employee's profile
    */
   public String getDepartment(){
	   return this.profile.getDepartment();
   }

   /**
    * This function is a getter function that returns the date the employee was hired
    * @return the dateHired attribute of the employee's profile
    */
   public Date getDate(){
	   return profile.getDateHired();
   }
}