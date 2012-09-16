package org.obvial.obvial;

import junit.framework.TestCase;

public class ListaJugadoresTest extends TestCase {
	
	protected void setUp() throws Exception {
		
	}

	protected void tearDown() throws Exception {
		
	}

	public void testAnadirJugador() {
		ListaJugadores.getListaJugadores().anadirJugador("Rico", "Azul");
		assertTrue(1 == ListaJugadores.getListaJugadores().getListaJ().size());
		ListaJugadores.getListaJugadores().anadirJugador("Pobre", "Verde");
		assertTrue(2 == ListaJugadores.getListaJugadores().getListaJ().size());
		ListaJugadores.getListaJugadores().anadirJugador("Famoso", "Rojo");
		assertTrue(3 == ListaJugadores.getListaJugadores().getListaJ().size());
		ListaJugadores.getListaJugadores().anadirJugador("Desconocido", "Amarillo");
		assertTrue(4 == ListaJugadores.getListaJugadores().getListaJ().size());
		ListaJugadores.getListaJugadores().anadirJugador("Alto", "Rosa");
		assertTrue(5 == ListaJugadores.getListaJugadores().getListaJ().size());
		ListaJugadores.getListaJugadores().anadirJugador("Bajo", "Naranja");
		assertTrue(6 == ListaJugadores.getListaJugadores().getListaJ().size());
		//Comprobamos que no se pueden anadir mas de 6 jugadores
		ListaJugadores.getListaJugadores().anadirJugador("Mediano", "Morado");
		assertTrue(6 == ListaJugadores.getListaJugadores().getListaJ().size());
	}

	public void testBuscarJugador() {
		assertTrue(ListaJugadores.getListaJugadores().buscarJugador(3).compararId(3));
		assertFalse(ListaJugadores.getListaJugadores().buscarJugador(2).compararId(4));
	}
	
	public void testAlgunoHaTerminado(){
		assertFalse(ListaJugadores.getListaJugadores().algunoHaTerminado());
		ListaJugadores.getListaJugadores().buscarJugador(3).ganar();
		assertTrue(ListaJugadores.getListaJugadores().algunoHaTerminado());
	}

}
