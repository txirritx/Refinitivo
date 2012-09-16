package org.obvial.obvial;

import junit.framework.TestCase;

public class CasillaTrasladoTest extends TestCase {
	
	private CasillaTraslado c1;

	protected void setUp() throws Exception {
		c1 = new CasillaTraslado(1, 2, 3, 1, 1);
	}

	protected void tearDown() throws Exception {
		c1 = null;
	}
	
	public void testGetCasillaDesp(){
		assertTrue (c1.getCasillaDesp() == 2);
		assertFalse (c1.getCasillaDesp() == 1);
	}

	

}
