package org.obvial.obvial;

import org.obvial.obvial.Jugador;

import junit.framework.TestCase;


public class JugadorTest extends TestCase {
	
	private Jugador j1;
	private Casilla c1;
	private Casilla c2;
	private Casilla c3;
	private CasillaRetencion c4;
	private CasillaRetencion c5;
	private CasillaTraslado c6;
	private CasillaTraslado c7;

	protected void setUp() throws Exception {
		j1 = new Jugador ("Rico", "Azul", 1);
		c1 = new Casilla (1, 1, 1, 1);
		c2 = new Casilla (2, 1, 1, 1);
		c3 = new Casilla (17, 1, 1, 1);
		c4 = new CasillaRetencion(6, 2, 2, 1, 1);
		c5 = new CasillaRetencion(23, 1, 2, 1, 1);
		c6 = new CasillaTraslado(34, 6, 3, 1, 1);
		c7 = new CasillaTraslado(45, 17, 3, 1, 1);
		ListaCasillas.getListaCasillas().anadirCasilla(c1);
		ListaCasillas.getListaCasillas().anadirCasilla(c2);
		ListaCasillas.getListaCasillas().anadirCasilla(c3);
		ListaCasillas.getListaCasillas().anadirCasillaRetencion(c4);
		ListaCasillas.getListaCasillas().anadirCasillaRetencion(c5);
		ListaCasillas.getListaCasillas().anadirCasillaTraslado(c6);
		ListaCasillas.getListaCasillas().anadirCasillaTraslado(c7);	
	}

	protected void tearDown() throws Exception {
		j1 = null;
	}

	public void testCompararId() {
		assertTrue(j1.compararId(1));
		assertFalse(j1.compararId(2));
	}
	
	public void testSimularRetencion(){
		j1.retener(2);
		assertTrue(j1.getRetencion() == 2);
		j1.actualizarRet();
		assertTrue(j1.getRetencion() == 1);
		j1.actualizarRet();
		assertTrue(j1.getRetencion() == 0);
		j1.actualizarRet();
		assertTrue(j1.getRetencion() == 0);
	}
	
	public void testGetters(){
		assertEquals(2, j1.getColor());
		assertTrue(j1.getPosicion() == 1);
		assertFalse(j1.haFinalizado());
		assertEquals("Rico", j1.getNombre());
	}
	
	public void testAvanzarRetroceder(){
		j1.avanzar(4);
		assertTrue(j1.getPosicion() == 5);
		j1.avanzar(64);
		assertTrue(j1.getPosicion() == 57);
	}
	
	public void testMoverACasilla(){
		ListaCasillas.getListaCasillas().anadirJugadorACasilla(j1, 1);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasilla(1).tamano() == 1);
		j1.moverACasilla(2);
		assertTrue(j1.getPosicion() == 2);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasilla(1).tamano() == 0);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasilla(2).tamano()== 1);
		j1.moverACasilla(6);
		assertTrue(j1.getPosicion() == 6);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasilla(2).tamano() == 0);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaRetencion(6).tamano()== 1);
		j1.moverACasilla(34);
		assertTrue(j1.getPosicion() == 34);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaRetencion(6).tamano()== 0);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaTraslado(34).tamano() == 1);
		j1.moverACasilla(6);
		assertTrue(j1.getPosicion() == 6);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaTraslado(34).tamano() == 0);
		assertTrue(ListaCasillas.getListaCasillas().buscarCasillaRetencion(6).tamano()== 1);
	}
	
	

}
