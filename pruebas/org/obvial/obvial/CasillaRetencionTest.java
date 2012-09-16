package org.obvial.obvial;

import junit.framework.TestCase;

public class CasillaRetencionTest extends TestCase {
	
	private CasillaRetencion c1;

	protected void setUp() throws Exception {
		c1 = new CasillaRetencion (1, 2, 3, 1, 1);
	}

	protected void tearDown() throws Exception {
		c1 = null;
	}

	public void testGetTurnosRetencion() {
		assertTrue (c1.getTurnosRetencion() == 2);
		assertFalse (c1.getTurnosRetencion() == 1);
	}

}
