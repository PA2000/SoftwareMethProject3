package sample;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class defines the properties of a Date object. Its attributes are integers representing the year, month, and day.
 * @author Padmank Ambadipudi, Dimitri Victor
*/
public class Date implements Comparable<Date>{
   private int year;
   private int month;
   private int day;
   
   public static final int YEAR_1900 = 1900;
   public static final int QUADRENNIAL = 4;
   public static final int CENTENNIAL = 100;
   public static final int QUATERCENTENNIAL = 400;
   public static final int DAY_ZERO = 0;
   public static final int DAY_30 = 30;
   public static final int DAY_31 = 31;
   public static final int DAY_29 = 29;
   public static final int DAY_28 = 28;

   Calendar rightNow = Calendar.getInstance();

	/**
	 * Constructor that takes in a String parameter to create a Date object.
	 * @param date a string in the form of mm/dd/yyyy used to create a Date object.
	*/
   public Date(String date){
	   
 	   StringTokenizer st = new StringTokenizer(date, "/");
 	   
	   while(st.hasMoreTokens()) {
		   
		   if(st.countTokens() == 3) {
			   month = Integer.parseInt(st.nextToken()) - 1;
		   }
		   if(st.countTokens() == 2) {
			   day = Integer.parseInt(st.nextToken());
		   }
		   if(st.countTokens() == 1) {
			   year = Integer.parseInt(st.nextToken());
		   }	   
		   	   
	   }
	   
   }

	/**
	 * Default constructor to create the date object 
	*/
   public Date(){ //Need to import Calendar class
	  Calendar.getInstance();
	  year = rightNow.get(Calendar.YEAR);
	  day = rightNow.get(Calendar.DAY_OF_MONTH);
	  month = rightNow.get(Calendar.MONTH);
   }

	/**
	 * This function deteremines whether a given date is equal to this date.
	 * @param date to be compared to this date
	 * @return true if the dates are equal, false if they are not equal
	 */
	@Override
	public int compareTo(Date date) {
		if(date.year < this.year) {
			return -1;
		}else if(date.year > this.year ) {
			return 1;
		}else {
			if(date.month < this.month ) {
				return -1;
			}else if(date.month > this.month) {
				return 1;
			}else {
				if(date.day < this.day) {
					return -1;
				}else if(date.day > this.day) {
					return 1;
				}else {
					return 0;
				}
			}
		}
		
	}
	
	/**
	 * Method to output the date object in a string format that is convenient to parseInt
	 * @return string representation of the date object
	*/
	@Override
	public String toString() {
	   return Integer.toString(month+1) +"/"+ Integer.toString(day) +"/"+ Integer.toString(year);
	}

	/**
	 * This method checks to see whether the date is a valid one
	 * @return false if the year is below 1900 or greater than the current year(2021); false if the month is outside the range of of January to December; 
	 * false if the day is outside the range of 1 to 31; false if the date contains a date that cannot exist in a leap year; true if all the checks are passed.
	*/
   public boolean isValid(){
	   Calendar cal = Calendar.getInstance();
	   if(year < YEAR_1900 || year > rightNow.get(Calendar.YEAR)) {		   
		   return false;
	   }
	   	   
	   if(month < Calendar.JANUARY || month > Calendar.DECEMBER) {
		   return false;
	   }
	   
	   if(cal.get(Calendar.YEAR) == year) {
		   if(cal.get(Calendar.MONTH) < month) {
			   return false;
		   }
		   
		   if(cal.get(Calendar.MONTH) == month) {
			   if(cal.get(Calendar.DAY_OF_MONTH) < day) { 
				   return false;
			   }
		   }
	   }	   
	   
	   if(day <= DAY_ZERO || day > DAY_31) {
		   return false;
	   }
	   
	   if(day == DAY_31) {
		   if(month == Calendar.APRIL || month == Calendar.JUNE || month == Calendar.SEPTEMBER ||month == Calendar.NOVEMBER) {
			   return false;
		   }	
	   }
	   
	   if( month == Calendar.FEBRUARY) {
		   if(day > DAY_29) {
			   return false;
		   }
		   if(!isLeapYear()) {
			   if(day == DAY_29) {
				   return false;		   
			   }
		   }
	   }	   	   
	   return true;
   }

	/**
	 * This function determines whether the year is a leap year and is used in the above isValid() function.
	 * @return false if the given year is not a leap year, true is it is a leap year
	*/
   public boolean isLeapYear() {
	   
	   if(year%QUADRENNIAL == 0) {
		   
		   if(year%CENTENNIAL == 0) {
			   
			   if(year%QUATERCENTENNIAL == 0) {				   
				   return true;
			   }else {
				   return false;
			   }			   
		   }else {
			   return true;
		   }		   
	   }else {
		   return false;
	   }	   
   }

   
	/**
	 * Getter function for the year variable
	 * @return the year(yyyy) attribute of the date
	*/
   public int getYear(){
	   return year;
   }

	/**
	 * Getter function for the month variable
	 * @return the month(mm) attribute of the date
	*/
   public int getMonth(){
	   return month;
   }

	/**
	 * Getter function for the day variable
	 * @return the day(dd) attribute of the date
	*/
   public int getDay(){
	   return day;
   }

   
}