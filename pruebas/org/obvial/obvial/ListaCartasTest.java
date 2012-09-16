package org.obvial.obvial;

import junit.framework.TestCase;

public class ListaCartasTest extends TestCase {
	
	ListaCartas l1;
	Carta c1,c2,c3;
	
	protected void setUp() throws Exception {
		c1 = new Carta(1,"¿Cual es el primer deseo de Aladdin?","D","Ser un sultan","Ser el genio mas poderoso del mundo","Jasmin se enamore de el","Convertirle en principe");
		c2 = new Carta(2,"La máxima puntuación que se puede conseguir en un juego de Pac-Man es ..","A","3.333","4.55","5","10");
		c3 = new Carta(3,"¿Cuántos atardeceres ven cada día los astronautas de la Estación Espacial Internacional?","C","7","24","15","4");

		l1=new ListaCartas();
		
		l1.anadirCarta(c1);
		l1.anadirCarta(c2);

		
		
	}

	protected void tearDown() throws Exception {
		l1 = null;
	}
	
	public void testAnadirCarta(){
		assertFalse(l1.esta(c3));
		l1.anadirCarta(c3);
		assertTrue(l1.esta(c3));
	
	}
	
	
	public void testEliminarCarta(){
		assertTrue(l1.esta(c1));
		assertTrue(l1.esta(c2));
		l1.eliminarCarta(1);
		l1.eliminarCarta(2);
		assertFalse(l1.esta(c1));
		assertFalse(l1.esta(c1));
	}
	
	public void testBuscarCarta(){
		assertTrue(c1==l1.buscarCarta(1));
		assertTrue(c2==l1.buscarCarta(2));
		assertFalse(c3==l1.buscarCarta(3));
		assertFalse(c1==l1.buscarCarta(3));
		assertFalse(c1==l1.buscarCarta(3));
	}
	
	
	public void testEsta(){
		assertFalse(l1.esta(c3));
		l1.anadirCarta(c3);
		assertTrue(l1.esta(c3));
	}
	
	
	public void testReiniciarLista(){
		l1.buscarCarta(1).usarCarta();
		assertTrue(l1.buscarCarta(1).haSidoUsada());
		l1.buscarCarta(2).usarCarta();
		assertTrue(l1.buscarCarta(2).haSidoUsada());
		
		l1.reiniciarLista();
	
		assertFalse(l1.buscarCarta(1).haSidoUsada());
		assertFalse(l1.buscarCarta(2).haSidoUsada());
		
		
	}
	

}
