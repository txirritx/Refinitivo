package org.obvial.obvial;

import junit.framework.TestCase;

public class DadoTest extends TestCase{

public void testDado(){
	int t= Dado.getDado().getTirada();
	assertTrue(t>0 && t<=6 );
	System.out.println(t);
}	
}
