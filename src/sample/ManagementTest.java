import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 */

/**
 * @author dimitrivictor
 *
 */
public class ManagementTest {

	/**
	 * Test method for {@link Management#calculatePayment()}.
	 */
	@Test
	public void testCalculatePayment() {
		
		Company company = new Company();
		Date d1 = new Date("1/1/2020");
		Profile p1 = new Profile("Victor,Dimitri", "CS", d1);
	
		Management management1 = new Management("Manager", p1, 26000);
		company.add(management1);
		management1.calculatePayment();
			
		assertTrue(management1.toString().equals("Victor,Dimitri::CS::1/1/2020::Payment $1,192.31::"
				+ "FULL TIME::Annual Salary $26,000.00::Manager Compensation $192.31")); // Test case 1 "Manager" calculatePayment()
		
		Management management2 = new Management("Department Head", p1, 26000);
		company.add(management2);
		management2.calculatePayment();
		
		assertTrue(management2.toString().equals("Victor,Dimitri::CS::1/1/2020::Payment $1,365.38::"
				+ "FULL TIME::Annual Salary $26,000.00::Department Head Compensation $365.38"));// Test case 2 "Department Head" calculatePayment()
		
		Management management3 = new Management("Director", p1, 26000);
		company.add(management3);
		management3.calculatePayment();
		
		assertTrue(management3.toString().equals("Victor,Dimitri::CS::1/1/2020::Payment $1,461.54::"
				+ "FULL TIME::Annual Salary $26,000.00::Director Compensation $461.54"));// Test case 3 "Director" calculatePayment()
		
	}

}