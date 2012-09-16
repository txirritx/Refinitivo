package org.obvial.obvial;

import junit.framework.TestCase;

public class CasillaTest extends TestCase {
	
	private Casilla c1;
	private Jugador j1;
	private Jugador j2;
	

	protected void setUp() throws Exception {
		c1 = new Casilla(1, 1, 1, 1);
		j1 = new Jugador("Pepe", "Rojo", 1);
		j2 = new Jugador("Manolo", "Azul", 2);
		c1.anadirJugadorACasilla(j1);
	}

	protected void tearDown() throws Exception {
		c1 = null;
	}

	public void testGetClase(){
		assertTrue(c1.getClase() == 1);
	}
	
	public void testGetNumero() {
		assertTrue(c1.getNumero() == 1);
	}

	public void testAnadirFicha() {
		assertTrue (c1.tamano() == 1);
		c1.anadirJugadorACasilla(j2);
		assertTrue (c1.tamano() == 2);
		c1.anadirJugadorACasilla(j2);
		assertTrue (c1.tamano() == 2);
	}

	public void testEsta() {
		assertTrue (c1.esta(j1));
		assertFalse(c1.esta(j2));
	}

	public void testEliminarJugadorDeCasilla() {
		c1.eliminarJugadorDeCasilla(j1);
		assertTrue(c1.tamano() == 0);
	}

}
