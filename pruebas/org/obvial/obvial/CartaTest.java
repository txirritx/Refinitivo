package org.obvial.obvial;


import junit.framework.TestCase;

import org.obvial.obvial.Carta;

public class CartaTest extends TestCase{
	Carta c1;
	
	protected void setUp() throws Exception {
		c1 = new Carta(1,"¿Cuál es el primer deseo de Aladdin?","D","Ser un sultán","Ser el genio más poderoso del mundo","Jasmin se enamore de él","Convertirse en príncipe");
	}

	protected void tearDown() throws Exception {
		c1 = null;
	}
	
	public void testGetPregunta(){
		System.out.println(c1.getPregunta());
	}
	
	public void testGetResp1(){
		System.out.println(c1.getResp1());
	}
	
	public void testGetResp2(){
		System.out.println(c1.getResp2());
	}
	
	public void testGetResp3(){
		System.out.println(c1.getResp3());
	}
	
	public void testGetResp4(){
		System.out.println(c1.getResp4());
	}
	
	public void testEsCorrecta(){
		assertTrue(c1.esCorrecta("D"));
		assertFalse(c1.esCorrecta("A"));

	}
	
	public void testUsarCarta(){
		
		// aqui probamos tanto el metodo usarCarta() como haSidoUSada()
		assertFalse(c1.haSidoUsada());
		c1.usarCarta();
		assertTrue(c1.haSidoUsada());
		c1.reiniciarCarta();
		assertFalse(c1.haSidoUsada());
	}

	public void testEsEsaCarta(){
		assertTrue(c1.esEsaCarta(1));
		assertFalse(c1.esEsaCarta(2));
	}
	
	public void testReiniciarCarta(){
		c1.reiniciarCarta();
		assertFalse(c1.haSidoUsada());
	}

			
}