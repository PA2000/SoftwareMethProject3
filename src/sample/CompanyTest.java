import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 */

/**
 * @author dimitrivictor
 *
 */
public class CompanyTest {

	/**
	 * Test method for {@link Company#add(Employee)}.
	 */
	@Test
	public void testAdd() {
		
		Company company = new Company();
		
		//do we need to add cases for all the AM AF AP
		
		Date d1 = new Date("1/1/2020");
		Profile p1 = new Profile("Victor,Dimitri", "CS", d1);
		Employee e1 = new Employee(p1);
		
		assertTrue(company.add(e1)); //Test case #1 adding a true person
		assertFalse(company.add(e1)); //Test case #2 adding a duplicate person
		
		Date d2 = new Date("1/2/2020");
		Profile p2 = new Profile("Victor,Dimitri", "CS", d2);
		Employee e2 = new Employee(p2);
		
		assertTrue(company.add(e2));
		
		Date d3 = new Date("1/3/2020");
		Profile p3 = new Profile("Victor,Dimitri", "CS", d3);
		Employee e3 = new Employee(p3);
		
		assertTrue(company.add(e3));
		
		Date d4 = new Date("1/4/2020");
		Profile p4 = new Profile("Victor,Dimitri", "CS", d4);
		Employee e4 = new Employee(p4);
		
		assertTrue(company.add(e4));
		
		Date d5 = new Date("1/5/2020");
		Profile p5 = new Profile("Victor,Dimitri", "CS", d5);
		Employee e5 = new Employee(p5);
		
		assertTrue(company.add(e5)); //Test case #3 testing the grow() method
				
	}

	/**
	 * Test method for {@link Company#remove(Employee)}.
	 */
	@Test
	public void testRemove() {
		
		Company company = new Company();
		
		Date d1 = new Date("1/1/2020");
		Profile p1 = new Profile("Victor,Dimitri", "CS", d1);
		Employee e1 = new Employee(p1);
		assertTrue(company.add(e1));
		
		assertTrue(company.remove(e1)); //Test case #4 remove function works if employee is there
		
		assertFalse(company.remove(e1)); //Test case #5 remove function does not work if employee is not there
		
		
		
	}

	/**
	 * Test method for {@link Company#setHours(Employee)}.
	 */
	@Test
	public void testSetHours() {
		
		Company company = new Company();

		Date d1 = new Date("1/1/2020");
		Profile p1 = new Profile("Victor,Dimitri", "CS", d1);
		Parttime part1 = new Parttime(45, p1);
		
		assertTrue(company.add(part1)); 
		
		Parttime part_1a = new Parttime(p1,100); 
		
		assertTrue(company.setHours(part_1a)); //Test case #6, set hours within range for employee in database;
		
		Profile p2 = new Profile("Nonexistent,User", "CS", d1);
		Parttime part_2a = new Parttime(p2, 100);
		
		assertFalse(company.setHours(part_2a)); //Test case #7, set hours for employee NOT in database
		

		
	}
	

}