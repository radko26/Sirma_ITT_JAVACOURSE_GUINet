package com.sirma.itt.javacourse.guinetwork.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sirma.itt.javacourse.guinetwork.calculator.Compute;

/**
 * Test class for {@link Compute}.
 * 
 * @author radoslav
 */
public class ComputeTest {

	/**
	 * Asserts if the answer is correct.
	 */
	@Test
	public void testCompute() {
		assertEquals("55",
				Compute.compute("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10"));
		assertEquals("7", Compute.compute("1 + 3 + ( 15 / 5 )"));
		assertEquals("5", Compute.compute("1 + 3 + ( 15 / ( 5 * 3 ) )"));
	}

}
